package com.ssy.pink.network.api.sin;

import android.text.TextUtils;
import android.util.Log;

import com.ssy.pink.bean.weibo.PreLoginInfo;
import com.ssy.pink.utils.JsonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class LoginWei {
	
	/*public String servertime = "1540098120";
	
	public String nonce = "IJMSY8";
	
	public String rsakv = "1330428213";
	
	public String sp = "6f0e7565a3f8f34d069453e2ad73f80720e72c2c542dad42cb995b8b9c58f939c284c006066f48721bb5c6abfeaa8d6eaef86c292c779476fbff7620686707137fbd6a2e85d767e4303129f49834f58ea85fb1fcae1514c2b4ea9b99a265e1e54e99be0f7d4c3f66c00d339c13437909e48266b326591ed0784eaced6c77938f";
	
	public String su = "MTU5MDYyNzQ5NzA=";
	
	public String door = "";*/
    /**
     * @param args
     * @throws IOException
     * @throws ClientProtocolException
     */
//    public static void main(String[] args) throws ClientProtocolException, IOException {
//        LoginWei wei = new LoginWei();
////        wei.login("https://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.19)&_=1539745862512");
//    }
    public void preLogin() {
        HttpClient httpClient = new DefaultHttpClient();
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String url = "https://login.sina.com.cn/sso/prelogin.php?entry=weibo&callback=sinaSSOController" +
                ".preloginCallBack&su=MTgzMTI0OTMxMDc%3D&rsakt=mod&checkpin=1&client=ssologin.js(v1.4.19)&_="
                + timeStamp;
        Log.i("aaaa", "preLogin url:" + url);
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                String result = EntityUtils.toString(resEntity, "UTF-8");
                //返回数据格式： sinaSSOController.preloginCallBack(json);
                result = result.substring(0, result.length() - 1)
                        .replace("sinaSSOController.preloginCallBack(", "");
                PreLoginInfo info = JsonUtils.toObject(result, PreLoginInfo.class);
                login(httpClient, info, timeStamp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login(HttpClient httpClient, PreLoginInfo preLoginInfo, String timeStamp) throws ClientProtocolException, IOException {
        String charset = "UTF-8";
        HttpPost post = null;
        HttpPost postz = null;
//        HttpClient httpClient = new DefaultHttpClient();
        post = new HttpPost("https://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.19)&_=" + "1539745862512");
        post.setHeader("Content-type", "application/x-www-form-urlencoded");
        post.setHeader("Access-Control-Allow-Credentials", "true");
        post.setHeader("Access-Control-Allow-Origin", "http://my.sina.com.cn");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("cdult", "3"));
        list.add(new BasicNameValuePair("domain", "sina.com.cn"));
        list.add(new BasicNameValuePair("entry", "homepage"));
        list.add(new BasicNameValuePair("gateway", "1"));
        list.add(new BasicNameValuePair("from", ""));
        list.add(new BasicNameValuePair("savestate", "30"));
        list.add(new BasicNameValuePair("useticket", "0"));
        list.add(new BasicNameValuePair("vsnf", "1"));
        list.add(new BasicNameValuePair("ssosimplelogin", "1"));
        list.add(new BasicNameValuePair("su", preLoginInfo.getSu()));
        list.add(new BasicNameValuePair("service", "sso"));
        list.add(new BasicNameValuePair("servertime", preLoginInfo.getServertime()));
        list.add(new BasicNameValuePair("nonce", preLoginInfo.getNonce()));
        list.add(new BasicNameValuePair("pwencode", "rsa2"));
        list.add(new BasicNameValuePair("rsakv", preLoginInfo.getRsakv()));
        list.add(new BasicNameValuePair("sp", preLoginInfo.getSp()));
        list.add(new BasicNameValuePair("encoding", "UTF-8"));
        list.add(new BasicNameValuePair("prelt", "162"));
        list.add(new BasicNameValuePair("url", "http://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack"));
        list.add(new BasicNameValuePair("returntype", "TEXT"));
        list.add(new BasicNameValuePair("sr", "1366*768"));
        if (!TextUtils.isEmpty(preLoginInfo.getDoor())) {
            list.add(new BasicNameValuePair("door", preLoginInfo.getDoor()));
        }
        list.add(new BasicNameValuePair("pagerefer", "http://my.sina.com.cn/profile/logined"));
        list.add(new BasicNameValuePair("qrcode_flag", "true"));
        list.add(new BasicNameValuePair("__proto__", "Object"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
        post.setEntity(entity);
        HttpResponse response = httpClient.execute(post);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                String result = EntityUtils.toString(resEntity, charset);
                System.out.println("login result =" + result);

                JSONObject info = JSONObject.fromObject(result);
                System.out.println("crossDomainUrlList = " + info.get("crossDomainUrlList"));

                JSONArray urlList = (JSONArray) info.get("crossDomainUrlList");
                String weibourl = (String) urlList.get(0);
                System.out.println("weibo sso url = " + weibourl);

                HttpGet getsso = new HttpGet(weibourl);
                HttpResponse getssoresponse = httpClient.execute(getsso);
                System.out.println("sso info = " + EntityUtils.toString(getssoresponse.getEntity(), charset));


                //请求转发请求参数
                String postZ = "https://weibo.com/aj/v6/mblog/forward?ajwvr=6&domain=5693779992&__rnd=" + Calendar.getInstance().getTimeInMillis();
                postz = new HttpPost(postZ);
                postz.setHeader("Content-type", "application/x-www-form-urlencoded");
                postz.setHeader("Host", "weibo.com");
                postz.setHeader("Referer", "https://weibo.com/u/5693779992/home?topnav=1&wvr=6");
                List<NameValuePair> listParam = new ArrayList<NameValuePair>();
                listParam.add(new BasicNameValuePair("_t", "0"));
                listParam.add(new BasicNameValuePair("appkey", ""));
                listParam.add(new BasicNameValuePair("from_plugin", "0"));
                listParam.add(new BasicNameValuePair("group_source", "group_all"));
                listParam.add(new BasicNameValuePair("isReEdit", "false"));
                listParam.add(new BasicNameValuePair("location", "page_100206_home"));
                listParam.add(new BasicNameValuePair("mark", "1_12D077FC3E70881A4D5B5F159E8AA51119F0B8914BE1BCFA127D1AF92F8D74FFC1F1406A824F98FFDA6285141BC789DDC0FAEF719ED86D689C63C3E4887B3142FEFF915263D2A3097EC0DFB90FF89CACDDD2CF8DE37BF800B8D58EE4449207634BD91DD382074A8547B9C8E79B8EDD85"));
                listParam.add(new BasicNameValuePair("mid", "4289628737675854"));//被转发的微博id
                listParam.add(new BasicNameValuePair("module", ""));
                listParam.add(new BasicNameValuePair("page_module_id", ""));
                listParam.add(new BasicNameValuePair("pdetail", "1002061642909335"));
                listParam.add(new BasicNameValuePair("pic_id", ""));
                listParam.add(new BasicNameValuePair("pic_src", ""));
                listParam.add(new BasicNameValuePair("rank", "0"));
                listParam.add(new BasicNameValuePair("rankid", ""));
                listParam.add(new BasicNameValuePair("reason", "测试转发微博"));
                listParam.add(new BasicNameValuePair("refer_sort", ""));
                listParam.add(new BasicNameValuePair("rid", "0_0_0_3071694476221415112_0_0"));
                listParam.add(new BasicNameValuePair("style_type", "1"));
                UrlEncodedFormEntity entityz = new UrlEncodedFormEntity(listParam, charset);
                postz.setEntity(entityz);
                HttpResponse responsez = httpClient.execute(postz);
                if (responsez != null) {
                    HttpEntity resEntityz = responsez.getEntity();
                    if (resEntityz != null) {
                        String resultz = EntityUtils.toString(resEntityz, charset);
                        System.out.println(resultz);
                    } else {
                        System.out.println(responsez);
                    }
                } else {
                    System.out.println("responsez is null");
                }

            }
        } else {
            System.out.println(" response is null");
        }
    }

}
