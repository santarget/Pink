package com.ssy.pink.network;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStore.LoadStoreParameter;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class HttpsUtils {
    public HttpsUtils() {
    }

    public static HttpsUtils.SSLParams getSslSocketFactory(InputStream[] certificates, InputStream bksFile, String password) {
        HttpsUtils.SSLParams sslParams = new HttpsUtils.SSLParams();

        try {
            TrustManager[] trustManagers = prepareTrustManager(certificates);
            KeyManager[] keyManagers = prepareKeyManager(bksFile, password);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            X509TrustManager trustManager = null;
            if (trustManagers != null) {
                trustManager = new MyTrustManager(chooseTrustManager(trustManagers));
            } else {
                trustManager = new UnSafeTrustManager();
            }
            sslContext.init(keyManagers, new TrustManager[]{trustManager}, null);
//            sslContext.init(null, new TrustManager[]{trustManager}, null);
            sslParams.sSLSocketFactory = new TLSSocketFactory(sslContext);
            sslParams.trustManager = trustManager;
            return sslParams;
        } catch (NoSuchAlgorithmException var8) {
            throw new AssertionError(var8);
        } catch (KeyManagementException var9) {
            throw new AssertionError(var9);
        } catch (KeyStoreException var10) {
            throw new AssertionError(var10);
        }
    }

    private static TrustManager[] prepareTrustManager(InputStream... certificates) {
        if (certificates != null && certificates.length > 0) {
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load((LoadStoreParameter) null);
                int index = 0;
                InputStream[] certificates1 = certificates;

                int len = certificates.length;

                for (int i = 0; i < len; ++i) {
                    InputStream certificate = certificates1[i];
                    String certificateAlias = Integer.toString(index++);
                    keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                    try {
                        if (certificate != null) {
                            certificate.close();
                        }
                    } catch (IOException var10) {

                    } finally {
                        certificate.close();
                    }
                }

                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                return trustManagers;
            } catch (NoSuchAlgorithmException var11) {
                var11.printStackTrace();
            } catch (CertificateException var12) {
                var12.printStackTrace();
            } catch (KeyStoreException var13) {
                var13.printStackTrace();
            } catch (Exception var14) {
                var14.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }

    private static KeyManager[] prepareKeyManager(InputStream bksFile, String password) {
        try {
            if (bksFile != null && password != null) {
                KeyStore clientKeyStore = KeyStore.getInstance("BKS");
                clientKeyStore.load(bksFile, password.toCharArray());
                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keyManagerFactory.init(clientKeyStore, password.toCharArray());
                return keyManagerFactory.getKeyManagers();
            }

            return null;
        } catch (KeyStoreException var4) {
            var4.printStackTrace();
        } catch (NoSuchAlgorithmException var5) {
            var5.printStackTrace();
        } catch (UnrecoverableKeyException var6) {
            var6.printStackTrace();
        } catch (CertificateException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return null;
    }

    private static X509TrustManager chooseTrustManager(TrustManager[] trustManagers) {
        TrustManager[] arr = trustManagers;
        int len = trustManagers.length;

        for (int i = 0; i < len; ++i) {
            TrustManager trustManager = arr[i];
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }

        return null;
    }

    private static class MyTrustManager implements X509TrustManager {
        private X509TrustManager defaultTrustManager;
        private X509TrustManager localTrustManager;

        public MyTrustManager(X509TrustManager localTrustManager) throws NoSuchAlgorithmException, KeyStoreException {
            TrustManagerFactory var4 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            var4.init((KeyStore) null);
            this.defaultTrustManager = HttpsUtils.chooseTrustManager(var4.getTrustManagers());
            this.localTrustManager = localTrustManager;
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            try {
                this.defaultTrustManager.checkServerTrusted(chain, authType);
            } catch (CertificateException var4) {
                this.localTrustManager.checkServerTrusted(chain, authType);
            }

        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class UnSafeTrustManager implements X509TrustManager {
        private UnSafeTrustManager() {
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private class UnSafeHostnameVerifier implements HostnameVerifier {
        private UnSafeHostnameVerifier() {
        }

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static class SSLParams {
        public SSLSocketFactory sSLSocketFactory;
        public X509TrustManager trustManager;

        public SSLParams() {
        }
    }
}
