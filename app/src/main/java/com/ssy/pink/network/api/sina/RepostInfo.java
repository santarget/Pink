package com.ssy.pink.network.api.sina;

import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.weibo.RepostResult;
import com.ssy.pink.bean.weibo.WeiboLoginInfo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/10/31
 */
public class RepostInfo implements Serializable {
    private static final long serialVersionUID = -3426075099812335796L;
    SmallInfo smallInfo;//小号
    WeiboLoginInfo weiboLoginInfo;//鉴权

    HttpClient httpClient;
    HttpResponse ssoResponse;

    RepostResult repostResult;//转发日志

    public SmallInfo getSmallInfo() {
        return smallInfo;
    }

    public void setSmallInfo(SmallInfo smallInfo) {
        this.smallInfo = smallInfo;
    }

    public WeiboLoginInfo getWeiboLoginInfo() {
        return weiboLoginInfo;
    }

    public void setWeiboLoginInfo(WeiboLoginInfo weiboLoginInfo) {
        this.weiboLoginInfo = weiboLoginInfo;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpResponse getSsoResponse() {
        return ssoResponse;
    }

    public void setSsoResponse(HttpResponse ssoResponse) {
        this.ssoResponse = ssoResponse;
    }

    public RepostResult getRepostResult() {
        return repostResult;
    }

    public void setRepostResult(RepostResult repostResult) {
        this.repostResult = repostResult;
    }

    @Override
    public String toString() {
        return "RepostInfo{" +
                "smallInfo=" + smallInfo +
                ", weiboLoginInfo=" + weiboLoginInfo +
                ", httpClient=" + httpClient +
                ", ssoResponse=" + ssoResponse +
                ", repostResult=" + repostResult +
                '}';
    }
}
