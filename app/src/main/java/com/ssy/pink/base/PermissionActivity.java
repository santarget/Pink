package com.ssy.pink.base;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by tys on 2017/4/2.
 */

public class PermissionActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected boolean mCheckSelfPermission(@NonNull String permission) {
        return PermissionChecker.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void mRequestPermission(@NonNull String[] permissions, int requestCode) {
        requestPermissions(permissions, requestCode);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isGranted = true;
        int index = 0;
        ArrayList<String> denPermissions = new ArrayList<>();
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
                denPermissions.add(permissions[index]);
            }
            index++;
        }
        if (isGranted)
            permissionGranted(requestCode);
        else
            permissionDenied(denPermissions, requestCode);
    }

    /**
     * 申请的权限中任何一个被拒绝时调用，默认是finish当前Activity
     *
     * @param deniedPermission 被拒绝的权限列表
     * @param requestCode      请求码
     */
    protected void permissionDenied(ArrayList<String> deniedPermission, int requestCode) {
        finish();
    }

    /**
     * 申请的权限中所有都同意时调用
     *
     * @param requestCode 请求码
     */
    protected void permissionGranted(int requestCode) {
    }

}
