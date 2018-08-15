package com.ssy.pink.manager;

import android.content.res.AssetManager;

import com.ssy.pink.MyApplication;
import com.ssy.pink.common.ConfigProp;
import com.ssy.pink.network.HttpsUtils;
import com.ssy.pink.network.MyHostnameVerifier;
import com.ssy.pink.network.NobodyConverterFactory;
import com.ssy.pink.network.OkHttpClientProvider;
import com.ssy.pink.network.UnsafeOkHttpClient;
import com.ssy.pink.utils.EnDecrypUtil;
import com.ssy.pink.utils.JsonUtils;
import com.ssy.pink.utils.LogUtil;
import com.ssy.pink.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.query.WhereCondition;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author ssy
 * @date 2018/4/9
 */
public class LoginManager {
    private static final String TAG = "LoginManager";
    private static LoginManager instance;
//    private LoginResponse loginResponse;
    private LoginManager() {
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }

   /* public LoginResponse getLoginResponse() {
        return loginResponse;
    }*/

    /*public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
        MyApplication.token = loginResponse.getToken();
    }*/


    public void login(final String userName, final String passWord) {
       /* ReportManager.getInstance().setAccout(userName);

        LoginRequest req = new LoginRequest();
        req.setLoginName(userName);
        req.setPassword(passWord);
        req.setDomain(domain);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));

        Map<String, String> heads = new HashMap<>();
        DeviceInfo deviceInfo = new DeviceInfoUtil().getDeviceInfo();
        heads.put("x-device-sn", deviceInfo.getDeviceSN());
        heads.put("x-device-type", deviceInfo.getDeviceType());
        heads.put("x-device-os", deviceInfo.getDeviceOS());
        heads.put("x-device-name", deviceInfo.getDeviceName());
        heads.put("x-client-version", deviceInfo.getDeviceAgent());
        heads.put("x-request-ip", deviceInfo.getDeviceAddress());
        getRetrofit(getClient()).create(LoginApi.class).login(heads, requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        EventBus.getDefault().post(EventCode.LOGIN_FAIL);
                        MyUtils.handleExcep(e);
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        LogUtil.e("LoginManager", "LoginResponse:" + loginResponse.toString());
                        setLoginResponse(loginResponse);
                        ReportManager.getInstance().generateFaultFile("");
                        saveLoginResult(loginResponse);
                        saveUserInfo(userName, passWord, loginResponse);
                        LoginManager.getInstance().getUamUrl();
                        LoginManager.getInstance().getUfmUrl();
                    }
                });*/
    }

    /**
     * 本质上是重新登录,同步请求
     */
    public void refreshToken() throws IOException {
        /*UserInfo user = getLastLoginUser();
        if (user != null) {
            String[] result = PublicTools.resolveUserInfo(user);
            String userName = result[0];
            String passWord = result[1];
            String domain = result[2];

            LoginRequest req = new LoginRequest();
            req.setLoginName(userName);
            req.setPassword(EnDecrypUtil.AesDecrypt(passWord));
            req.setDomain(domain);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));

            Map<String, String> heads = new HashMap<>();
            DeviceInfo deviceInfo = new DeviceInfoUtil().getDeviceInfo();
            heads.put("x-device-sn", deviceInfo.getDeviceSN());
            heads.put("x-device-type", deviceInfo.getDeviceType());
            heads.put("x-device-os", deviceInfo.getDeviceOS());
            heads.put("x-device-name", deviceInfo.getDeviceName());
            heads.put("x-client-version", deviceInfo.getDeviceAgent());
            heads.put("x-request-ip", deviceInfo.getDeviceAddress());

            LoginResponse response = getRetrofit(getClient()).create(LoginApi.class).refreshToken(heads, requestBody).execute().body();
            LogUtil.e("LoginManager", "refreshToken:" + loginResponse.toString());
            setLoginResponse(response);
            saveLoginResult(response);
        }*/
    }

    public void autoLogin() {

       /* UserInfo user = getLastLoginUser();
        if (user == null) { //升级时删除所有数据库此时user为null
            EventBus.getDefault().post(EventCode.LOGIN_FAIL);
        } else {
            String[] result = PublicTools.resolveUserInfo(user);
            String userName = result[0];
            String passWord = result[1];
            String domain = result[2];
            login(userName, EnDecrypUtil.AesDecrypt(passWord), domain);
        }*/

    }





    public void getUfmUrl() {
       /* getRetrofit(OkHttpClientProvider.getClient()).create(LoginApi.class).getServerUrl("ufm")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ServerResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        EventBus.getDefault().post(EventCode.LOGIN_FAIL);
                    }

                    @Override
                    public void onNext(ServerResponse serverResponse) {
                        MyApplication.ufmAddress = serverResponse.getServerUrl();
                        MyApplication.isOffline = false;
                        EventBus.getDefault().post(EventCode.LOGIN_SUCCESS);//取到ufm url之后再登录进主界面
                    }
                });*/
    }

    /*public long saveUserInfo(String accout, String pwd, LoginResponse obj) {
        UserInfoDaoOperation userDao = (UserInfoDaoOperation) DaoFactory.getUserInfoDao();
        List<UserInfo> users = userDao.query(null, null, null, null, null);
        for (UserInfo user : users) {
            user.setLastLogin(false);
            userDao.update(user);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(accout);
        userInfo.setPassWord(EnDecrypUtil.AesEncrypt(pwd));
        userInfo.setLastLogin(true);
        userInfo.setCloudUserId(obj.getCloudUserId());
        return userDao.insertOrReplace(userInfo);
    }*/

    /*public UserInfo getLastLoginUser() {
        UserInfoDaoOperation userInfoDao = (UserInfoDaoOperation) DaoFactory.getUserInfoDao();
        return userInfoDao.uniqueQuery(null, new WhereCondition[]{UserInfoDao.Properties.IsLastLogin.eq(true)});
    }*/

    /*public void saveLoginResult(LoginResponse loginResponse) {
        SharedPreferencesUtil.saveNeedChangePassword(loginResponse.isNeedChangePassword());
        SharedPreferencesUtil.saveNeedDeclaration(loginResponse.isNeedDeclaration());
        MyApplication.token = loginResponse.getToken();
        MyApplication.tokenTimeStamp = System.currentTimeMillis();
        MyApplication.cloudUserId = loginResponse.getCloudUserId();
        MyApplication.userId = loginResponse.getUserId();
    }
*/
    private Retrofit getRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(ConfigProp.serverUrl)
                .addConverterFactory(NobodyConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    /**
     * 未添加token拦截器的okhttpclient，用于登录
     *
     * @return
     */
    private OkHttpClient getClient() {
        OkHttpClient.Builder builder;
        if (ConfigProp.verifyCert) {
            HttpsUtils.SSLParams sslParams = null;
            try {
                sslParams = HttpsUtils.getSslSocketFactory(
                        new InputStream[]{MyApplication.getInstance().getApplicationContext().getAssets().open(ConfigProp.certName, AssetManager.ACCESS_RANDOM)},
                        null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            builder = new OkHttpClient.Builder()
                    .hostnameVerifier(new MyHostnameVerifier())
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
            ;
        } else {
            builder = UnsafeOkHttpClient.getUnsafeOkHttpClientBuilder();
        }
        builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.i(TAG, message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .readTimeout(8000, TimeUnit.MILLISECONDS)
                .writeTimeout(8000, TimeUnit.MILLISECONDS);

        return builder.build();
    }

}
