package com.alipay.util.httpClient;
 import org.apache.commons.httpclient.NameValuePair;
public class HttpRequest {

 public  String METHOD_GET;

 public  String METHOD_POST;

 private  String url;

 private  String method;

 private  int timeout;

 private  int connectionTimeout;

 private  NameValuePair[] parameters;

 private  String queryString;

 private  String charset;

 private  String clientIp;

 private  HttpResultType resultType;

public HttpRequest(HttpResultType resultType) {
    super();
    this.resultType = resultType;
}
public void setCharset(String charset){
    this.charset = charset;
}


public void setClientIp(String clientIp){
    this.clientIp = clientIp;
}


public void setQueryString(String queryString){
    this.queryString = queryString;
}


public HttpResultType getResultType(){
    return resultType;
}


public String getQueryString(){
    return queryString;
}


public void setConnectionTimeout(int connectionTimeout){
    this.connectionTimeout = connectionTimeout;
}


public String getMethod(){
    return method;
}


public String getCharset(){
    return charset;
}


public void setUrl(String url){
    this.url = url;
}


public void setParameters(NameValuePair[] parameters){
    this.parameters = parameters;
}


public String getUrl(){
    return url;
}


public void setMethod(String method){
    this.method = method;
}


public void setTimeout(int timeout){
    this.timeout = timeout;
}


public void setResultType(HttpResultType resultType){
    this.resultType = resultType;
}


public String getClientIp(){
    return clientIp;
}


public int getTimeout(){
    return timeout;
}


public NameValuePair[] getParameters(){
    return parameters;
}


public int getConnectionTimeout(){
    return connectionTimeout;
}


}