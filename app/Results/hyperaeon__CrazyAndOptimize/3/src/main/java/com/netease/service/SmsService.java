package com.netease.service;
 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.netease.util.ConstantUtil;
@Service
public class SmsService {

 private  Logger logger;


@SuppressWarnings("resource")
public Object sendMes(String mobile,String code){
    // 短信服务器连接信息初始化工作
    HttpClient client = new DefaultHttpClient();
    HttpPost method = new HttpPost(ConstantUtil.SMS_URL);
    client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
    method.setHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
    // 短信服务器连接信息
    String userName_passWord = getUserAndPass();
    StringBuilder codeStringBuilder = new StringBuilder();
    codeStringBuilder.append("您的验证码是：");
    logger.info("the code is {}", code);
    codeStringBuilder.append(code);
    codeStringBuilder.append("。请不要把验证码泄露给其他人。");
    // String content = new String(code);
    String content = codeStringBuilder.toString();
    logger.info("the whole message is {}", content);
    String account = userName_passWord.split(":")[0];
    String password = userName_passWord.split(":")[1];
    List<NameValuePair> formparams = new ArrayList<NameValuePair>();
    formparams.add(new BasicNameValuePair("account", account));
    formparams.add(new BasicNameValuePair("password", password));
    formparams.add(new BasicNameValuePair("mobile", mobile));
    formparams.add(new BasicNameValuePair("content", content));
    formparams.add(new BasicNameValuePair("format", "json"));
    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
    method.setEntity(entity);
    String jsonString;
    try {
        HttpResponse response = client.execute(method);
        System.out.println("response code is " + response.getStatusLine().getStatusCode());
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity();
            System.out.println("enetity is " + httpEntity);
            if (httpEntity != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"), 8 * 1024);
                    StringBuilder entityStringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        entityStringBuilder.append(line);
                    }
                    List<Map<String, String>> list;
                    String[] data = entityStringBuilder.toString().split(",");
                    System.out.println("code is " + data[0].split(":")[1]);
                    System.out.println("msg  is " + data[1].split(":")[1]);
                    System.out.println("smsid  is " + data[2].split(":")[1]);
                } catch (Exception e) {
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}


public void volidate(String str){
}


public String getRandomNum(){
    Random r = new Random();
    // (Math.random()*(999999-100000)+100000)
    return String.valueOf(r.nextInt(900000) + 100000);
}


public boolean checkMobileNumber(String mobileNumber){
    boolean flag = false;
    try {
        Pattern regex = Pattern.compile("^(((13[0-9])|(17[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
        Matcher matcher = regex.matcher(mobileNumber);
        flag = matcher.matches();
    } catch (Exception e) {
        flag = false;
    }
    return flag;
}


public String getUserAndPass(){
    Properties properties = new Properties();
    InputStream input = this.getClass().getResourceAsStream("/sms.properties");
    try {
        properties.load(input);
    } catch (IOException e) {
        e.printStackTrace();
    }
    String username = properties.getProperty("username");
    String password = properties.getProperty("password");
    logger.info("username is {}, password is {}", username, password);
    return username + ":" + password;
}


public void main(String[] args){
    new SmsService().sendMes("ceshi");
}


}