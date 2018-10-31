package com.ssy.pink.network.api.sina;

import com.ssy.pink.bean.weibo.RepostResult;
import com.ssy.pink.bean.weibo.WeiboLoginInfo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author ssy
 * @date 2018/10/31
 */
public class SinaAccout implements Serializable {
    private static final long serialVersionUID = -3426075099812335796L;
    String sinaAccout;
    WeiboLoginInfo weiboLoginInfo;

    HttpClient httpClient;
    HttpResponse ssoResponse;

    RepostResult repostResult;

    public String getSinaAccout() {
        return sinaAccout;
    }

    public void setSinaAccout(String sinaAccout) {
        this.sinaAccout = sinaAccout;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SinaAccout)) return false;
        SinaAccout that = (SinaAccout) o;
        return Objects.equals(sinaAccout, that.sinaAccout);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sinaAccout);
    }

    @Override
    public String toString() {
        return "SinaAccout{" +
                "sinaAccout='" + sinaAccout + '\'' +
                ", weiboLoginInfo=" + weiboLoginInfo +
                ", httpClient=" + httpClient +
                ", ssoResponse=" + ssoResponse +
                ", repostResult=" + repostResult +
                '}';
    }
}
