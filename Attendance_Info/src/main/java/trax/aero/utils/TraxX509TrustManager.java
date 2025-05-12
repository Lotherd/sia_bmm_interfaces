package trax.aero.utils;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class TraxX509TrustManager implements X509TrustManager {
    
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String authType) {
        // Trust all client certificates
    }
    
    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String authType) {
        // Trust all server certificates
    }
    
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}