package com.ssy.pink.manager;

import android.text.TextUtils;

import com.ssy.greendao.helper.HelperFactory;
import com.ssy.greendao.helper.LoopLogInfoDbHelper;
import com.ssy.greendao.helper.SmallStatusDbHelper;
import com.ssy.greendao.helper.WeiboLoginDbHelper;
import com.ssy.pink.MyApplication;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.weibo.EmotionInfo;
import com.ssy.pink.bean.weibo.LoopLogInfo;
import com.ssy.pink.bean.weibo.RepostResult;
import com.ssy.pink.bean.weibo.WeiboLoginInfo;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.network.api.sina.RepostInfo;
import com.ssy.pink.network.api.sina.SinaSSO;
import com.ssy.pink.service.WorkService;
import com.ssy.pink.utils.ListUtils;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 抡博管理器
 */
public class LoopManager {
    private final String divider = "\n----------------------------------------------\n";
    private final String historyStr = "\n================== 历史操作 ================\n";//历史操作
    private static LoopManager instance;
    private List<EmotionInfo> emotionInfoList = new ArrayList<>();
    public List<RepostInfo> totalRepostList = new ArrayList<>();//抡博的小号集合
    private LinkedList<RepostInfo> repostQueue = new LinkedList();
    private int finishedCount;//本次抡博已完成了几轮
    public StringBuilder logSb = new StringBuilder();
    public long acountWait = 7 * 1000l;//账号之间间隔时间
    public long roundWait = 300 * 1000l;//每轮之间间隔时间
    boolean looping;
    //    int logCount;//日志条数，保留一定数量
    LoopLogInfoDbHelper logInfoDbHelper;
    WeiboLoginDbHelper weiboDbHelper;
    SmallStatusDbHelper statusDbHelper;

    //配置项
    public boolean customOn;//开启自定义
    public String customContent;//自定义内容
    public boolean randomOn;//开启随机表情
    public boolean keepOthers;//其他人转发内容保留
    public int speed;// 0慢速  1稳定 2快速
    public int count;//数量设置
    //    public String url;//要抡博的链接
    public String weiboId;//要抡博的微博id
    private final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    private LoopManager() {
        logInfoDbHelper = HelperFactory.getLoopLogInfoDbHelper();
        weiboDbHelper = HelperFactory.getWeiboLoginDbHelper();
        statusDbHelper = HelperFactory.getSmallStatusDbHelper();
    }

    public static LoopManager getInstance() {
        if (instance == null) {
            synchronized (LoopManager.class) {
                if (instance == null) {
                    instance = new LoopManager();
                }
            }
        }
        return instance;
    }

    /**
     * 显示历史操作日志
     */
    public void setHistoryLog() {
        logSb.delete(0, logSb.length());
        List<LoopLogInfo> logs = logInfoDbHelper.queryAllLog(UserManager.getInstance().userInfo.getWeiboid());
        if (!ListUtils.isEmpty(logs)) {
            for (LoopLogInfo log : logs) {
                logSb.append(log.getTime()).append(" ").append(log.getLog()).append(divider);
            }
            logSb.insert(0, historyStr);
        }
        EventBus.getDefault().post(EventCode.WORK_UPDATE_LOG);
    }

    public void startWork() {
        WorkService.startService(MyApplication.getInstance(), acountWait, roundWait);
        setHistoryLog();
//        sendHistory();
        sendLogWithoutTime("初始化成功，开始抡博");
        looping = true;
    }

    public void stopWork() {
        looping = false;
        WorkService.stopService(MyApplication.getInstance());
        sendLogWithoutTime("抡博结束");
    }

    /**
     * 设置抡博的小号集合
     *
     * @param workSmalls
     */
    public void setSmalls(List<SmallInfo> workSmalls) {
        totalRepostList.clear();
        repostQueue.clear();
        for (SmallInfo smallInfo : workSmalls) {
            RepostInfo info = new RepostInfo();
            info.setSmallInfo(smallInfo);
            WeiboLoginInfo weiboLoginInfo = weiboDbHelper.uniqueQuery(smallInfo.getWeibosmallNumId());
            info.setWeiboLoginInfo(weiboLoginInfo);
            totalRepostList.add(info);
        }
        repostQueue.addAll(totalRepostList);
        finishedCount = 0;
    }

    /**
     * 设置转发速度
     *
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
        if (speed == 0) {
            acountWait = 7 * 1000L;
            roundWait = 300 * 1000L;
        } else if (speed == 1) {
            acountWait = 1000L;
            roundWait = 20 * 1000L;
        } else {
            acountWait = 100L;
            roundWait = 20 * 1000L;
        }
    }

    private void sendLog(RepostInfo repostInfo, String msg, boolean success) {
        StringBuilder sb = new StringBuilder();
        sb.append(repostInfo.getSmallInfo().getSmallWeiboNum());
        if (success) {
            sb.append("转发成功");
        } else {
            sb.append("转发失败");
            statusDbHelper.insertOrReplace(repostInfo.getSmallInfo().getWeibosmallNumId(), false);
        }
        if (!TextUtils.isEmpty(msg)) {
            sb.append(": msg");
        }

        String log = sb.toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String formatStr = formatter.format(new Date());
        logSb.insert(0, formatStr + " " + log + divider);
        EventBus.getDefault().post(EventCode.WORK_UPDATE_LOG);

        LoopLogInfo loopLogInfo = new LoopLogInfo();
        loopLogInfo.setWeiboUid(UserManager.getInstance().userInfo.getWeiboid());
        loopLogInfo.setTime(formatStr);
        loopLogInfo.setLog(log);
        logInfoDbHelper.insert(loopLogInfo);
        List<LoopLogInfo> logInfos = logInfoDbHelper.queryAllLog(UserManager.getInstance().userInfo.getWeiboid());
        if (logInfos.size() >= 200) {  //超出的日志删除
            logInfoDbHelper.delete(logInfos.get(logInfos.size() - 1));
            logInfoDbHelper.delete(logInfos.get(logInfos.size() - 2));
            logInfoDbHelper.delete(logInfos.get(logInfos.size() - 3));
            logInfoDbHelper.delete(logInfos.get(logInfos.size() - 4));
            logInfoDbHelper.delete(logInfos.get(logInfos.size() - 5));
        }
    }

    private void sendLogWithoutTime(String log) {
        logSb.insert(0, log + divider);
        EventBus.getDefault().post(EventCode.WORK_UPDATE_LOG);
    }

    private void sendHistory() {
        if (logSb.length() != 0) {
            int position = logSb.indexOf(historyStr);
            if (position != -1) {
                logSb.delete(position, position + historyStr.length());
            }
            logSb.insert(0, historyStr);
            EventBus.getDefault().post(EventCode.WORK_UPDATE_LOG);
        }
    }

    private void removeSmall(RepostInfo repostInfo) {
        totalRepostList.remove(repostInfo);
    }

    private String getEmotion() {
        try {
            emotionInfoList = HelperFactory.getEmotionDbHelper().queryAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ListUtils.isEmpty(emotionInfoList)) {
            UserManager.getInstance().getWeiboEmotions();
            return "[吃瓜]";
        } else {
            //获取随机表情
            int random = new Random().nextInt(emotionInfoList.size());
            return emotionInfoList.get(random).getValue();
        }
    }

    private String getWeiboReason() {
        StringBuilder sbWeibo = new StringBuilder();
        if (customOn) {
            sbWeibo.append(customContent);
        }
        if (randomOn) {
            sbWeibo.append(getEmotion());
        }
        return sbWeibo.toString();
    }

    private RepostInfo getCurrentRepost() {
        if (ListUtils.isEmpty(repostQueue)) {
            finishedCount++;
//            sendLog("第" + finishedCount + "轮微博抡博完成", false);
            if (finishedCount >= count) {
//                stopWork();
                looping = false;
                EventBus.getDefault().post(EventCode.WORK_FINISH);
                return null;
            } else {
                if (ListUtils.isEmpty(totalRepostList)) {
                    looping = false;
                    EventBus.getDefault().post(EventCode.WORK_FINISH);
                    return null;
                }
                repostQueue.addAll(totalRepostList);
                EventBus.getDefault().post(EventCode.WORK_WAITING);
                return null;
            }
        }
        RepostInfo smallInfo = repostQueue.poll();// 移除并返问队列头部的元素    如果队列为空，则返回null
        return smallInfo;
    }

    public void work() {
        if (!looping) {
            return;
        }
        final RepostInfo currentRepost = getCurrentRepost();
        if (currentRepost == null) {
            return;
        }
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                RepostInfo repostInfo = SinaSSO.getInstance().repost(currentRepost, weiboId, getWeiboReason());
                if (repostInfo.getRepostResult() == null) {
                    sendLog(currentRepost, "", false);
                    removeSmall(currentRepost);
                    EventBus.getDefault().post(EventCode.WORK_NEXT);
                } else if (repostInfo.getRepostResult().getCode().equals(RepostResult.SUCCESS)) {
                    sendLog(currentRepost, "", true);
                    EventBus.getDefault().post(EventCode.WORK_NEXT);
                } else if (currentRepost.getRepostResult().getCode().equals(RepostResult.ERROR_RELOAD)) {
                    //重新登录再试一次
                    work(currentRepost);
                } else {
                    sendLog(currentRepost, repostInfo.getRepostResult().getMsg(), false);
                    removeSmall(currentRepost);
                    EventBus.getDefault().post(EventCode.WORK_NEXT);
                }
            }
        });
      /*  WeiboTokenInfo tokenInfo = HelperFactory.getTokenDbHelper().uniqueQuery(currentRepost.getWeibosmallNumId());
        if (tokenInfo == null || TextUtils.isEmpty(tokenInfo.getMAccessToken())) {
            sendLog(currentRepost.getSmallWeiboName() + "微博发布失败:" + "无微博授权或微博授权已过期", true);
            removeSmall(currentRepost);
            EventBus.getDefault().post(EventCode.WORK_NEXT);
            return;
        }
        String weibo = getWeibo();
        WeiboNet.shareWeibo(tokenInfo.getMAccessToken(), weibo, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.errorBody() != null) {
                    String errorMsg = "";
                    try {
                        errorMsg = response.errorBody().string();
                        WeiboErrorResp errorResp = JsonUtils.toObject(errorMsg, WeiboErrorResp.class);
                        String errorStr = errorResp.getError();
                        if (errorStr.contains(WeiboErrorResp.APPLICATION_RESTRICTIONS)) {
                            errorStr = "应用使用受限";
                        } else if (errorStr.contains(WeiboErrorResp.NO_DOMAIN)) {
                            errorStr = "未发现有效链接";
                        } else if (errorStr.contains(WeiboErrorResp.REPEAT_CONTENT)) {
                            errorStr = "重复内容";
                        } else if (errorStr.contains(WeiboErrorResp.UPDATE_TOO_FAST)) {
                            errorStr = "发送过快";
                        }
                        sendLog(currentRepost.getSmallWeiboName() + "微博发布失败:" + errorStr, true);
                        removeSmall(currentRepost);
                        EventBus.getDefault().post(EventCode.WORK_NEXT);
                    } catch (IOException e) {
                        e.printStackTrace();
                        sendLog(currentRepost.getSmallWeiboName() + "微博发布失败", true);
                        removeSmall(currentRepost);
                        EventBus.getDefault().post(EventCode.WORK_NEXT);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        sendLog(currentRepost.getSmallWeiboName() + "微博发布失败：" + errorMsg, true);
                        removeSmall(currentRepost);
                        EventBus.getDefault().post(EventCode.WORK_NEXT);
                    }
                } else {
                    sendLog(currentRepost.getSmallWeiboName() + "微博发布成功", true);
                    EventBus.getDefault().post(EventCode.WORK_NEXT);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                sendLog(currentRepost.getSmallWeiboName() + "微博发布失败：" + t.toString(), true);
                removeSmall(currentRepost);
                EventBus.getDefault().post(EventCode.WORK_NEXT);
            }
        });*/
    }

    private void work(final RepostInfo currentRepost) {
        if (TextUtils.isEmpty(weiboId)) {
            return;
        }
        if (!looping) {
            return;
        }
        if (currentRepost == null) {
            return;
        }
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                RepostInfo repostInfo = SinaSSO.getInstance().repost(currentRepost, weiboId, getWeiboReason());
                if (repostInfo.getRepostResult() == null) {
                    sendLog(currentRepost, "", false);
                    removeSmall(currentRepost);
                    EventBus.getDefault().post(EventCode.WORK_NEXT);
                } else if (repostInfo.getRepostResult().getCode().equals(RepostResult.SUCCESS)) {
                    sendLog(currentRepost, "", true);
                    EventBus.getDefault().post(EventCode.WORK_NEXT);
                } else {
                    sendLog(currentRepost, repostInfo.getRepostResult().getMsg(), false);
                    removeSmall(currentRepost);
                    EventBus.getDefault().post(EventCode.WORK_NEXT);
                }
            }
        });
    }

    public void reset() {
        logSb.delete(0, logSb.length());
//        logCount = 0;
        totalRepostList.clear();
        repostQueue.clear();
        looping = false;
    }
}
