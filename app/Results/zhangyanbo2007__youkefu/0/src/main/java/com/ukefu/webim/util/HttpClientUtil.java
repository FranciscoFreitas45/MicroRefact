package com.ukefu.webim.util;
 import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
@SuppressWarnings("deprecation")
public class HttpClientUtil {

 private  PoolingHttpClientConnectionManager connMgr;

 private  RequestConfig requestConfig;

 private  int MAX_TIMEOUT;


public String doPostSSL(String apiUrl,Object json){
    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
    HttpPost httpPost = new HttpPost(apiUrl);
    CloseableHttpResponse response = null;
    String httpStr = null;
    try {
        httpPost.setConfig(requestConfig);
        // 解决中文乱码问题
        StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            return null;
        }
        HttpEntity entity = response.getEntity();
        if (entity == null) {
            return null;
        }
        httpStr = EntityUtils.toString(entity, "utf-8");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (response != null) {
            try {
                EntityUtils.consume(response.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (httpClient != null) {
            httpClient.close();
        }
    }
    return httpStr;
}


@Override
public void verify(String host,String[] cns,String[] subjectAlts){
}


public String doPost(String apiUrl,String json){
    CloseableHttpClient httpClient = HttpClients.createDefault();
    String httpStr = null;
    HttpPost httpPost = new HttpPost(apiUrl);
    CloseableHttpResponse response = null;
    try {
        httpPost.setConfig(requestConfig);
        // 解决中文乱码问题
        StringEntity stringEntity = new StringEntity(json, "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        httpStr = EntityUtils.toString(entity, "UTF-8");
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (response != null) {
            try {
                EntityUtils.consume(response.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (httpClient != null) {
            httpClient.close();
        }
    }
    return httpStr;
}


public SSLConnectionSocketFactory createSSLConnSocketFactory(){
    SSLConnectionSocketFactory sslsf = null;
    try {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }).build();
        sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }

            @Override
            public void verify(String host, SSLSocket ssl) throws IOException {
            }

            @Override
            public void verify(String host, X509Certificate cert) throws SSLException {
            }

            @Override
            public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
            }
        });
    } catch (GeneralSecurityException e) {
        e.printStackTrace();
    }
    return sslsf;
}


public boolean isTrusted(X509Certificate[] chain,String authType){
    return true;
}


public String doGet(String url,Map<String,Object> params){
    String apiUrl = url;
    StringBuffer param = new StringBuffer();
    int i = 0;
    for (String key : params.keySet()) {
        if (i == 0)
            param.append("?");
        else
            param.append("&");
        param.append(key).append("=").append(params.get(key));
        i++;
    }
    apiUrl += param;
    String result = null;
    CloseableHttpClient httpclient = HttpClients.createDefault();
    try {
        HttpGet httpPost = new HttpGet(apiUrl);
        HttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();
            result = IOUtils.toString(instream, "UTF-8");
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (httpclient != null) {
            httpclient.close();
        }
    }
    return result;
}


}