package main.util.wechat;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by Administrator on 2016/7/30 0030.
 * 需要一个证书信任管理器，这个管理器类需要自己定义，但需要实现X509TrustManager接口
 * 证书管理器的作用就是让它信任我们指定的证书，
 * 下面的代码意味着信任所有证书，不管是否权威机构颁发
 */
public class MyX509TrustManager implements X509TrustManager{
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
//        return new X509Certificate[0];
        //TODO 为什么要改为null????????
        return null;
    }
}
