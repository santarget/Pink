package com.ssy.pink.iview;

import java.util.ArrayList;

/**
 * @author ssy
 * @date 2018/8/9
 */
public interface ISplashActivityView extends IView {
    void showPermissionDialog(ArrayList<String> deniedPermission, int requestCode);

    void requestPermission(String[] needPermission);

    void checkNeedPermission(String[] permissionNeed);

    void toLoginActivity();

    void toMainActivity();

}
