package com.alipay.util.httpClient;
 import org.apache.commons.httpclient.HttpException;
import java.io.IOException;
import java.net.UnknownHostException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.FilePartSource;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class HttpProtocolHandler {

 private  String DEFAULT_CHARSET;

 private  int defaultConnectionTimeout;

 private  int defaultSoTimeout;

 private  int defaultIdleConnTimeout;

 private  int defaultMaxConnPerHost;

 private  int defaultMaxTotalConn;

 private  long defaultHttpConnectionManagerTimeout;

 private  HttpConnectionManager connectionManager;

 private  HttpProtocolHandler httpProtocolHandler;

/**
 * 私有的构造方法
 */
private HttpProtocolHandler() {
    // 创建一个线程安全的HTTP连接池
    connectionManager = new MultiThreadedHttpConnectionManager();
    connectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxConnPerHost);
    connectionManager.getParams().setMaxTotalConnections(defaultMaxTotalConn);
    IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
    ict.addConnectionManager(connectionManager);
    ict.setConnectionTimeout(defaultIdleConnTimeout);
    ict.start();
}
public String toString(NameValuePair[] nameValues){
    if (nameValues == null || nameValues.length == 0) {
        return "null";
    }
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < nameValues.length; i++) {
        NameValuePair nameValue = nameValues[i];
        if (i == 0) {
            buffer.append(nameValue.getName() + "=" + nameValue.getValue());
        } else {
            buffer.append("&" + nameValue.getName() + "=" + nameValue.getValue());
        }
    }
    return buffer.toString();
}


public HttpProtocolHandler getInstance(){
    return httpProtocolHandler;
}


public HttpResponse execute(HttpRequest request,String strParaFileName,String strFilePath){
    HttpClient httpclient = new HttpClient(connectionManager);
    // 设置连接超时
    int connectionTimeout = defaultConnectionTimeout;
    if (request.getConnectionTimeout() > 0) {
        connectionTimeout = request.getConnectionTimeout();
    }
    httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
    // 设置回应超时
    int soTimeout = defaultSoTimeout;
    if (request.getTimeout() > 0) {
        soTimeout = request.getTimeout();
    }
    httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
    // 设置等待ConnectionManager释放connection的时间
    httpclient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);
    String charset = request.getCharset();
    charset = charset == null ? DEFAULT_CHARSET : charset;
    HttpMethod method = null;
    // get模式且不带上传文件
    if (request.getMethod().equals(HttpRequest.METHOD_GET)) {
        method = new GetMethod(request.getUrl());
        method.getParams().setCredentialCharset(charset);
        // parseNotifyConfig会保证使用GET方法时，request一定使用QueryString
        method.setQueryString(request.getQueryString());
    } else if (strParaFileName.equals("") && strFilePath.equals("")) {
        // post模式且不带上传文件
        method = new PostMethod(request.getUrl());
        ((PostMethod) method).addParameters(request.getParameters());
        method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + charset);
    } else {
        // post模式且带上传文件
        method = new PostMethod(request.getUrl());
        List<Part> parts = new ArrayList<Part>();
        for (int i = 0; i < request.getParameters().length; i++) {
            parts.add(new StringPart(request.getParameters()[i].getName(), request.getParameters()[i].getValue(), charset));
        }
        // 增加文件参数，strParaFileName是参数名，使用本地文件
        parts.add(new FilePart(strParaFileName, new FilePartSource(new File(strFilePath))));
        // 设置请求体
        ((PostMethod) method).setRequestEntity(new MultipartRequestEntity(parts.toArray(new Part[0]), new HttpMethodParams()));
    }
    // 设置Http Header中的User-Agent属性
    method.addRequestHeader("User-Agent", "Mozilla/4.0");
    HttpResponse response = new HttpResponse();
    try {
        httpclient.executeMethod(method);
        if (request.getResultType().equals(HttpResultType.STRING)) {
            response.setStringResult(method.getResponseBodyAsString());
        } else if (request.getResultType().equals(HttpResultType.BYTES)) {
            response.setByteResult(method.getResponseBody());
        }
        response.setResponseHeaders(method.getResponseHeaders());
    } catch (UnknownHostException ex) {
        return null;
    } catch (IOException ex) {
        return null;
    } catch (Exception ex) {
        return null;
    } finally {
        method.releaseConnection();
    }
    return response;
}


}