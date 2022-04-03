package com.circle.utils;
 import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
public class HttpKit {

 private  String DEFAULT_CHARSET;


public String post(String url,String s){
    AsyncHttpClient http = new AsyncHttpClient();
    AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
    builder.setBodyEncoding(DEFAULT_CHARSET);
    builder.setBody(s);
    Future<Response> f = builder.execute();
    String body = f.get().getResponseBody(DEFAULT_CHARSET);
    http.close();
    // System.out.println("ResponseBody:" + body);
    return body;
}


public String get(String url,Map<String,String> params){
    return get(url, params, null);
}


}