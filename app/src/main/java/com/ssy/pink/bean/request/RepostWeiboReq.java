package com.ssy.pink.bean.request;

import com.ssy.pink.common.ConstantWeibo;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/9/14
 */
public class RepostWeiboReq implements Serializable {
    private static final long serialVersionUID = 7271530614484642399L;

    String source = ConstantWeibo.APP_KEY;
    long id;
    String status;//添加的转发文本。必须做URLEncode,信息内容不超过140个汉字。如不填则默认为“转发微博”。

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RepostWeiboReq{" +
                "source='" + source + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
