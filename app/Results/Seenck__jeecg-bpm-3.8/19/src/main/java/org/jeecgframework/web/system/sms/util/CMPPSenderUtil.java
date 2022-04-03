package org.jeecgframework.web.system.sms.util;
 import java.io.IOException;
import java.net.URLEncoder;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jeecgframework.web.system.sms.util.msg.util.MsgContainer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
public class CMPPSenderUtil {

 private  Logger logger;


public String sendTMsgs(String phone,String content){
    String[] phoneAddress = phone.split(",");
    String sendResult = "";
    try {
        for (int i = 0; i < phoneAddress.length; i++) {
            boolean result = MsgContainer.sendMsg(content, phoneAddress[i]);
            if (!result) {
                sendResult += "-号码" + phoneAddress[i] + "发送失败";
                continue;
            }
        }
        return sendResult;
    } catch (Exception e) {
        e.printStackTrace();
        return "fasle";
    }
}


public String sendDifferenceNetMsg(String phone,String content){
    try {
        String msg = URLEncoder.encode(content, "utf-8");
        // String msg = "测试短信发送内容";
        // csp0短信地址
        String url = "";
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setAuthenticationPreemptive(true);
        // 创建POST方法的实例
        PostMethod postMethod = new PostMethod(url);
        // 使用系统提供的默认的恢复策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        // 初始化返回结果（String类型）
        String result = null;
        // 初始化返回结果（byte[]类型）
        byte[] responseBody = null;
        try {
            // 执行getMethod
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + postMethod.getStatusLine());
            }
            // 返回结果（byte[]类型）
            responseBody = postMethod.getResponseBody();
            // 返回结果（String类型，GBK格式）
            result = new String(responseBody, "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            postMethod.releaseConnection();
        }
        logger.debug("#调用短信发送接口返回数据\n{}", result);
        JSONObject jsonObject = (JSONObject) JSON.parse(result);
        String code = jsonObject.getString("code");
        if ("R0".equals(code)) {
            // 发送成功
            return "0";
        } else {
            // 发送失败
            return "1";
        }
    } catch (Exception e) {
        e.printStackTrace();
        // 系统故障
        return "2";
    }
}


public String sendMessage(String phone,String content,String exCode){
    try {
        String msg = URLEncoder.encode(content, "utf-8");
        // csp0短信地址
        String url = "";
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setAuthenticationPreemptive(true);
        // 创建POST方法的实例
        PostMethod postMethod = new PostMethod(url);
        // 使用系统提供的默认的恢复策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        // 初始化返回结果（String类型）
        String result = null;
        // 初始化返回结果（byte[]类型）
        byte[] responseBody = null;
        try {
            // 执行getMethod
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + postMethod.getStatusLine());
            }
            // 返回结果（byte[]类型）
            responseBody = postMethod.getResponseBody();
            // 返回结果（String类型，GBK格式）
            result = new String(responseBody, "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            postMethod.releaseConnection();
        }
        logger.debug("#调用短信发送接口返回数据\n{}", result);
        JSONObject jsonObject = (JSONObject) JSON.parse(result);
        String code = jsonObject.getString("code");
        if ("R0".equals(code)) {
            // 发送成功
            return "0";
        } else {
            // 发送失败
            return "1";
        }
    } catch (Exception e) {
        e.printStackTrace();
        // 系统故障
        return "2";
    }
}


public String sendMsg(String phone,String content){
    try {
        String msg = URLEncoder.encode(content, "utf-8");
        // String msg = "测试短信发送内容";
        // csp0短信地址
        String url = "";
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setAuthenticationPreemptive(true);
        // 创建POST方法的实例
        PostMethod postMethod = new PostMethod(url);
        // 使用系统提供的默认的恢复策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        // 初始化返回结果（String类型）
        String result = null;
        // 初始化返回结果（byte[]类型）
        byte[] responseBody = null;
        try {
            // 执行getMethod
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + postMethod.getStatusLine());
            }
            // 返回结果（byte[]类型）
            responseBody = postMethod.getResponseBody();
            // 返回结果（String类型，GBK格式）
            result = new String(responseBody, "GBK");
        } catch (IOException e) {
            logger.error(e.toString());
        // e.printStackTrace();
        } finally {
            // 释放连接
            postMethod.releaseConnection();
        }
        logger.debug("#调用短信发送接口返回数据\n{}", result);
        JSONObject jsonObject = (JSONObject) JSON.parse(result);
        String code = jsonObject.getString("code");
        if ("R0".equals(code)) {
            // 发送成功
            return "0";
        } else {
            // 发送失败
            return "1";
        }
    } catch (Exception e) {
        // e.printStackTrace();
        logger.error(e.toString());
        // 系统故障
        return "2";
    }
}


}