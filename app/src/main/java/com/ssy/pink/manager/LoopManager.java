package com.ssy.pink.manager;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONException;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.net.HttpManager;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.ssy.greendao.helper.HelperFactory;
import com.ssy.pink.MyApplication;
import com.ssy.pink.bean.EmotionInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.WeiboTokenInfo;
import com.ssy.pink.bean.response.WeiboErrorResp;
import com.ssy.pink.common.ConstantWeibo;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.network.OkHttpClientProvider;
import com.ssy.pink.network.api.WeiboApi;
import com.ssy.pink.network.api.WeiboNet;
import com.ssy.pink.service.WorkService;
import com.ssy.pink.utils.CommonUtils;
import com.ssy.pink.utils.JsonUtils;
import com.ssy.pink.utils.ListUtils;
import com.ssy.pink.utils.MyUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

/**
 * 抡博管理器
 */
public class LoopManager {
    private final String divider = "\n----------------------------------------------\n";
    private static LoopManager instance;
    public List<SmallInfo> smallList = new ArrayList<>();//抡博的小号集合
    private List<EmotionInfo> emotionInfoList = new ArrayList<>();
    private LinkedList<SmallInfo> smallQueue = new LinkedList();
    private int finishedCount;//本次抡博已完成了几轮
    public StringBuilder logSb = new StringBuilder();
    public long acountWait = 7 * 1000l;//账号之间间隔时间
    public long roundWait = 300 * 1000l;//每轮之间间隔时间

    //配置项
    public boolean customOn;//开启自定义
    public String customContent;//自定义内容
    public boolean randomOn;//开启随机表情
    public boolean keepOthers;//其他人转发内容保留
    public int speed;// 0慢速  1稳定 2快速
    public int count;//数量设置
    public String url;//要抡博的链接

    private LoopManager() {
        emotionInfoList = HelperFactory.getEmotionDbHelper().queryAll();
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

    public void startWork() {
        logSb.delete(0, logSb.length());
        WorkService.startService(MyApplication.getInstance(), acountWait, roundWait);
        sendLog("初始化成功，开始抡博");
        EventBus.getDefault().post(EventCode.WORK_UPDATE_LOG);
    }

    public void stopWork() {
        WorkService.stopService(MyApplication.getInstance());
        sendLog("抡博结束");
    }

    /**
     * 设置抡博的小号集合
     *
     * @param workSmalls
     */
    public void setSmalls(List<SmallInfo> workSmalls) {
        smallList.clear();
        smallList.addAll(workSmalls);
        smallQueue.clear();
        smallQueue.addAll(workSmalls);
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

    private void sendLog(String log) {
        logSb.insert(0, log + "\n" + CommonUtils.formatData(null, System.currentTimeMillis()) + divider);
        EventBus.getDefault().post(EventCode.WORK_UPDATE_LOG);
    }

    private void removeSmall(SmallInfo smallInfo) {
        smallList.remove(smallInfo);
        sendLog("已将账号" + smallInfo.getSmallWeiboNum() + "移出抡博队列");
    }

    private String getEmotion() {
        if (ListUtils.isEmpty(emotionInfoList)) {
            return "[吃瓜]";
        } else {
            //获取随机表情
            int random = new Random().nextInt(emotionInfoList.size());
            return emotionInfoList.get(random).getValue();
        }
    }

    private String getWeibo() {
        StringBuilder sbWeibo = new StringBuilder();
        if (customOn) {
            sbWeibo.append(customContent);
        }
        if (randomOn) {
            sbWeibo.append(getEmotion());
        }
        sbWeibo.append(url);
        return sbWeibo.toString();
    }

    private SmallInfo getCurrentSmall() {
        if (ListUtils.isEmpty(smallQueue)) {
            finishedCount++;
            sendLog("第" + finishedCount + "轮微博抡博完成");
            if (finishedCount >= count) {
//                stopWork();
                EventBus.getDefault().post(EventCode.WORK_FINISH);
                return null;
            } else {
                if (ListUtils.isEmpty(smallList)) {
                    sendLog("无有效抡博账号");
                    EventBus.getDefault().post(EventCode.WORK_FINISH);
                    return null;
                }
                EventBus.getDefault().post(EventCode.WORK_WAITING);
            }
            if (ListUtils.isEmpty(smallList)) {
                sendLog("无有效抡博账号");
                EventBus.getDefault().post(EventCode.WORK_FINISH);
                return null;
            }
            smallQueue.addAll(smallList);
        }
        SmallInfo smallInfo = smallQueue.poll();// 移除并返问队列头部的元素    如果队列为空，则返回null
        return smallInfo;
    }

    public void work() {
        final SmallInfo currentSmall = getCurrentSmall();
        if (currentSmall == null) {
            return;
        }
        WeiboTokenInfo tokenInfo = HelperFactory.getTokenDbHelper().uniqueQuery(currentSmall.getWeibosmallNumId());
        if (tokenInfo == null || TextUtils.isEmpty(tokenInfo.getMAccessToken())) {
            sendLog(currentSmall.getSmallWeiboNum() + "无微博授权或微博授权已过期");
            removeSmall(currentSmall);
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
                        sendLog(currentSmall.getSmallWeiboNum() + "微博发布失败:" + errorResp.getError());
                        removeSmall(currentSmall);
                        EventBus.getDefault().post(EventCode.WORK_NEXT);
                    } catch (IOException e) {
                        e.printStackTrace();
                        sendLog(currentSmall.getSmallWeiboNum() + "微博发布失败");
                        removeSmall(currentSmall);
                        EventBus.getDefault().post(EventCode.WORK_NEXT);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        sendLog(currentSmall.getSmallWeiboNum() + "微博发布失败：" + errorMsg);
                        removeSmall(currentSmall);
                        EventBus.getDefault().post(EventCode.WORK_NEXT);
                    }
                } else {
                    sendLog(currentSmall.getSmallWeiboNum() + "微博发布成功");
                    EventBus.getDefault().post(EventCode.WORK_NEXT);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                sendLog(currentSmall.getSmallWeiboNum() + "微博发布失败：" + t.toString());
                removeSmall(currentSmall);
                EventBus.getDefault().post(EventCode.WORK_NEXT);
            }
        });
    }

    private void refreshToken(WeiboTokenInfo tokenInfo) {
        String REFRESH_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
        WeiboParameters params = new WeiboParameters(ConstantWeibo.APP_KEY);
        params.put("client_id", ConstantWeibo.APP_KEY);
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", tokenInfo.getMRefreshToken());
        HttpManager.openUrl(MyApplication.getInstance(), REFRESH_TOKEN_URL, "POST", params);
    }

}
