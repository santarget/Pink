package com.ssy.pink.mvp.presenter;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.manager.BindManager;
import com.ssy.pink.mvp.iview.IAddSmallActivityView;
import com.ssy.pink.utils.AesUtils;
import com.ssy.pink.view.dialog.CheckDialog;

/**
 * @author ssy
 * @date 2018/9/6
 */
public class AddSmallActivityPresenter extends BasePresenter {
    private static final int CODE_ERROR = 1;
    private static final int CODE_FINISH = 2;
    private IAddSmallActivityView iView;
    public String errorWord;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_ERROR:
                    iView.setCheckDialog(CheckDialog.STATUS_ERROR);
                    break;
                case CODE_FINISH:
                    iView.setCheckDialog(CheckDialog.STATUS_FINISH);
                    break;
            }
        }
    };

    public AddSmallActivityPresenter(IAddSmallActivityView iView) {
        this.iView = iView;
    }

    /**
     * @param content
     * @return 出错位置的关键词，null则表示无错误
     */
    public void checkAccout(final String content) {
        BindManager.getInstance().smallInfos.clear();
        iView.setCheckDialog(CheckDialog.STATUS_CHECKING);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] accouts = content.split("\n");
                for (String str : accouts) {
                    if (TextUtils.isEmpty(str)) {
                        continue;
                    }
                    String[] temp = str.trim().split("----");
                    if (temp.length < 2 || TextUtils.isEmpty(temp[0])) {
                        errorWord = str;
                        handler.sendEmptyMessage(CODE_ERROR);
                        return;
                    } else {
                        SmallInfo info = new SmallInfo();
                        info.setSmallWeiboNum(temp[0]);
                        info.setUsepwd(AesUtils.encrypt(temp[1]));
                        BindManager.getInstance().smallInfos.add(info);
                    }
                }
                handler.sendEmptyMessage(CODE_FINISH);
            }
        }).start();

    }

}
