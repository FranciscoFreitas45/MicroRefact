package com.xwtec.xwserver.util;
 import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import com.xwtec.xwserver.constant.ConstantKeys;
public class WxcsHttpClient {

 private  Logger logger;

 public  String UTF8;

 public  String GBK;

 private  int HTTP_PORT;

 private  int HTTPS_PORT;

 private  String HTTP;

 private  String HTTPS;

 private  String encoding;

 private  int maxConnector;

 private  int connectTimeout;

 private  int readTimeout;

 private  DefaultHttpClient httpclient;

 private  SchemeRegistry schemeRegistry;

 private  PoolingClientConnectionManager clientConnectionManager;

/**
 * 默认构造方法 ，
 *
 * @param maxConnector
 */
public WxcsHttpClient() {
    _init();
}/**
 * 构造方法
 *
 * @param maxConnector
 *            设置每个实例化对象的最大连接数
 */
public WxcsHttpClient(int maxConnector) {
    this.maxConnector = maxConnector;
    _init();
}/**
 * 初始化超时时间
 *
 * @param connectTimeout
 * @param readTimeout
 */
public WxcsHttpClient(int maxConnector, int connectTimeout, int readTimeout) {
    this.maxConnector = maxConnector;
    this.connectTimeout = connectTimeout;
    this.readTimeout = readTimeout;
    _init();
}
public void _init(){
    clientConnectionManager.setDefaultMaxPerRoute(this.maxConnector);
    httpclient = new DefaultHttpClient(clientConnectionManager);
    httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, this.connectTimeout);
    httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, this.readTimeout);
}


public void setEncoding(String encoding){
    this.encoding = encoding;
}


public Map<String,Object> sendRequestByGetUpgrades(String url,String encodeType){
    logger.info("[WxcsHttpClient:sendRequestByGetUpgrades],url:" + url);
    logger.info("[WxcsHttpClient:sendRequestByGetUpgrades],encodeType:" + encodeType);
    Map<String, Object> requestResult = new HashMap<String, Object>();
    StringBuilder builder = new StringBuilder();
    BufferedReader reader = null;
    HttpResponse response = null;
    HttpGet httpGet = null;
    try {
        httpGet = new HttpGet(url);
        response = httpclient.execute(httpGet);
        // 返回状态码
        requestResult.put("statusCode", response.getStatusLine().getStatusCode());
        logger.info("[WxcsHttpClient:sendRequestByStream],response statusCode:" + requestResult.get("statusCode"));
        reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), encodeType));
        String lines = "";
        while ((lines = reader.readLine()) != null) {
            builder.append(lines);
        }
    } catch (Exception e) {
        logger.debug("[WxcsHttpClient:sendRequestByGetUpgrades],url:" + url);
        logger.debug("[WxcsHttpClient:sendRequestByGetUpgrades],encodeType:" + encodeType);
        logger.debug("[WxcsHttpClient:sendRequestByGetUpgrades]", e);
        throw e;
    } finally {
        if (null != reader) {
            reader.close();
            reader = null;
        }
        if (null != response) {
            EntityUtils.consume(response.getEntity());
            response = null;
        }
        httpGet = null;
        httpclient.getConnectionManager().closeExpiredConnections();
    }
    logger.info("[WxcsHttpClient:sendRequestByGetUpgrades],response content:" + builder.toString());
    // 返回内容
    requestResult.put("content", builder.toString());
    return requestResult;
}


public JSON_TYPE getJSONType(String str){
    if (null == str)
        return JSON_TYPE.JSON_TYPE_ERROR;
    str = str.trim();
    if (str.startsWith("{") && str.endsWith("}")) {
        return JSON_TYPE.JSON_TYPE_OBJECT;
    }
    if (str.startsWith("[") && str.endsWith("]")) {
        return JSON_TYPE.JSON_TYPE_ARRAY;
    }
    return JSON_TYPE.JSON_TYPE_ERROR;
}


public Map<String,Object> sendRequestByPostUpgrades(String url,List<NameValuePair> params,String encodingType){
    logger.info("[WxcsHttpClient:sendRequestByPostUpgrades],url:" + url);
    logger.debug("[WxcsHttpClient:sendRequestByPostUpgrades],encodingType:" + encodingType);
    Map<String, Object> requestResult = new HashMap<String, Object>();
    StringBuilder builder = new StringBuilder();
    BufferedReader reader = null;
    HttpResponse response = null;
    HttpPost httpPost = null;
    try {
        httpPost = new HttpPost(url);
        HttpEntity entity = new UrlEncodedFormEntity(params, encodingType);
        httpPost.setEntity(entity);
        response = httpclient.execute(httpPost);
        // 返回状态码
        requestResult.put("statusCode", response.getStatusLine().getStatusCode());
        logger.info("[WxcsHttpClient:sendRequestByStream],response statusCode:" + requestResult.get("statusCode"));
        reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), encodingType));
        String lines = "";
        while ((lines = reader.readLine()) != null) {
            builder.append(lines);
        }
    } catch (Exception e) {
        logger.debug("[WxcsHttpClient:sendRequestByPostUpgrades],url:" + url);
        logger.debug("[WxcsHttpClient:sendRequestByPostUpgrades],encodingType:" + encodingType);
        logger.debug("[WxcsHttpClient:sendRequestByPostUpgrades]", e);
        throw e;
    } finally {
        if (null != reader) {
            reader.close();
            reader = null;
        }
        if (null != response) {
            EntityUtils.consume(response.getEntity());
            response = null;
        }
        httpPost = null;
        httpclient.getConnectionManager().closeExpiredConnections();
    }
    logger.info("[WxcsHttpClient:sendRequestByPostUpgrades],response content:" + builder.toString());
    // 返回内容
    requestResult.put("content", builder.toString());
    return requestResult;
}


public JSONObject xmlStr2JsonObj(String xml){
    if (null == xml || "".equals(xml)) {
        return null;
    }
    XMLSerializer xmlSerializer = new XMLSerializer();
    String jsonStr = xmlSerializer.read(xml).toString();
    if (getJSONType(jsonStr) == JSON_TYPE.JSON_TYPE_OBJECT) {
        return JSONObject.fromObject(jsonStr);
    }
    return null;
}


public void main(String[] args){
    try {
    // sendRequestByStream("http://localhost:8080/xw_demo/demo/demo.do?method=23","sdsds");
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}


public String sendRequestByStream(String url,String strContent){
    logger.info("[WxcsHttpClient:sendRequestByStream],url:" + url);
    logger.info("[WxcsHttpClient:sendRequestByStream],sendContent:" + strContent);
    StringBuilder builder = new StringBuilder();
    BufferedReader reader = null;
    HttpResponse response = null;
    HttpPost httpPost = null;
    try {
        httpPost = new HttpPost(url);
        HttpEntity entity = new StringEntity(strContent, encoding);
        httpPost.setEntity(entity);
        response = httpclient.execute(httpPost);
        reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), encoding));
        String lines = "";
        while ((lines = reader.readLine()) != null) {
            builder.append(lines);
        }
    } catch (Exception e) {
        logger.debug("[WxcsHttpClient:sendRequestByStream],url:" + url);
        logger.debug("[WxcsHttpClient:sendRequestByStream],sendContent:" + strContent);
        logger.debug("[WxcsHttpClient:sendRequestByStream]", e);
        throw e;
    } finally {
        if (null != reader) {
            reader.close();
            reader = null;
        }
        if (null != response) {
            EntityUtils.consume(response.getEntity());
            response = null;
        }
        httpPost = null;
        httpclient.getConnectionManager().closeExpiredConnections();
    }
    logger.info("[WxcsHttpClient:sendRequestByStream],response content:" + builder.toString());
    return builder.toString();
}


public String sendRequestByGet(String url){
    logger.info("[WxcsHttpClient:sendRequestByStream],url:" + url);
    StringBuilder builder = new StringBuilder();
    BufferedReader reader = null;
    HttpResponse response = null;
    HttpGet httpGet = null;
    try {
        httpGet = new HttpGet(url);
        response = httpclient.execute(httpGet);
        reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), encoding));
        String lines = "";
        while ((lines = reader.readLine()) != null) {
            builder.append(lines);
        }
    } catch (Exception e) {
        logger.debug("[WxcsHttpClient:sendRequestByStream],url:" + url);
        logger.debug("[WxcsHttpClient:sendRequestByStream]", e);
        throw e;
    } finally {
        if (null != reader) {
            reader.close();
            reader = null;
        }
        if (null != response) {
            EntityUtils.consume(response.getEntity());
            response = null;
        }
        httpGet = null;
        httpclient.getConnectionManager().closeExpiredConnections();
    }
    logger.info("[WxcsHttpClient:sendRequestByStream],response content:" + builder.toString());
    return builder.toString();
}


public String sendRequestByPost(String url,String strContent){
    return sendRequestByStream(url, strContent);
}


}