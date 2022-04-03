package com.fosun.fc.projects.creepers.downloader;
 import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import us.codecraft.webmagic.Site;
public class HttpClientGenerator {

 private  PoolingHttpClientConnectionManager connectionManager;

public HttpClientGenerator() {
    SSLContext sslContext = null;
    try {
        sslContext = new SSLContextBuilder().loadTrustMaterial(new TrustStrategy() {

            // 信任所有
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }).build();
    } catch (Exception e) {
        e.printStackTrace();
    }
    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
    Registry<ConnectionSocketFactory> reg = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sslsf).build();
    connectionManager = new PoolingHttpClientConnectionManager(reg);
    connectionManager.setDefaultMaxPerRoute(100);
}
public HttpClientGenerator setPoolSize(int poolSize){
    connectionManager.setMaxTotal(poolSize);
    return this;
}


public void process(HttpRequest request,HttpContext context){
    if (!request.containsHeader("Accept-Encoding")) {
        request.addHeader("Accept-Encoding", "gzip");
    }
}


public CloseableHttpClient generateClient(Site site){
    HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(connectionManager);
    if (site != null && site.getUserAgent() != null) {
        httpClientBuilder.setUserAgent(site.getUserAgent());
    } else {
        httpClientBuilder.setUserAgent("");
    }
    if (site == null || site.isUseGzip()) {
        httpClientBuilder.addInterceptorFirst(new HttpRequestInterceptor() {

            public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
                if (!request.containsHeader("Accept-Encoding")) {
                    request.addHeader("Accept-Encoding", "gzip");
                }
            }
        });
    }
    SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(true).setTcpNoDelay(true).build();
    httpClientBuilder.setDefaultSocketConfig(socketConfig);
    if (site != null) {
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(site.getRetryTimes(), true));
    }
    // generateCookie(httpClientBuilder, site);
    return httpClientBuilder.build();
}


public CloseableHttpClient getClient(Site site){
    return generateClient(site);
}


public boolean isTrusted(X509Certificate[] chain,String authType){
    return true;
}


}