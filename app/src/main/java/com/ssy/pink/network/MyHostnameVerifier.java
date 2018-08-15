package com.ssy.pink.network;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @author ssy
 * @date 2018/7/4
 */
public class MyHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
        // 没有需求说做验证，暂时屏蔽此段代码
           /* try {
                String peerHost = session.getPeerHost(); //服务器返回的主机名
                X509Certificate[] peerCertificates = (X509Certificate[]) session.getPeerCertificates();
                for (X509Certificate certificate : peerCertificates) {
                    X500Principal subjectX500Principal = certificate.getSubjectX500Principal();
                    String name = subjectX500Principal.getName();
                    String[] split = name.split(",");
                    for (String str : split) {
                        if (str.startsWith("CN")) {//证书绑定的域名或者ip
                            if (str.contains(hostname) && str.contains(peerHost)) {
                                return true;
                            }
                        }
                    }
                }
            } catch (SSLPeerUnverifiedException e1) {
                e1.printStackTrace();
            }
            return false;*/
    }
}
