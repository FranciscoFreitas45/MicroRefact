package com.netease.service;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.netease.credit.test.SSLClient;
import com.netease.util.ConstantUtil;
import com.netease.util.HttpClientUtil;
import com.netease.util.ParsePageUtil;
@Service
public class SecurityUpgradeService {

 final  Logger logger;

 private  String charset;

 private  String loginUrl;

 private  String exectLoginUrl;

 private  String imageUrl;

 private  String newerUrl;

 private  String newerUrl_1;

 private  Header[] headers;

 private  List<String> cookieList;

 private  String cookie;

 private  Map<String,String> inputNameValue;


public String SecurityThirdStep(){
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap = new HashMap<String, String>();
    createMap.put("method", "chooseCertify");
    createMap.put("authtype", "2");
    createMap.put("org.apache.struts.taglib.html.TOKEN", inputNameValue.get(ConstantUtil.TOKEN_NAME));
    String result = httpClientUtil.doPost_2(newerUrl_1, createMap, cookie);
    // logger.debug("新用户刚登陆需要确实安全升级第三步 :{} ",result);
    System.out.println("result:" + result);
    return result;
}


@SuppressWarnings("resource")
public void initHeader(){
    SSLClient httpClient = new SSLClient();
    HttpGet get = new HttpGet(loginUrl);
    HttpResponse response = httpClient.execute(get);
    headers = response.getAllHeaders();
    extractAndReconstructCookies();
    String result = null;
    if (response != null) {
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
            result = EntityUtils.toString(resEntity, charset);
        }
    }
    inputNameValue = ParsePageUtil.parseInput(result);
    // logger.debug("the result of first request is {} ",result);
    System.out.println("result:" + result);
}


public void extractAndReconstructCookies(){
    if (headers != null) {
        // 提取Cookie
        for (Header header : headers) {
            if (header.getName().contains("Set-Cookie")) {
                cookieList.add(header.getValue().substring(0, header.getValue().indexOf(";") + 1));
            }
        }
        // 重组Cookie
        StringBuilder resultCookie = new StringBuilder();
        resultCookie.append(cookieList.get(2));
        resultCookie.append(cookieList.get(3));
        resultCookie.append("_gscu_1241536983=631254300v6nyy11;");
        resultCookie.append("_gscbrs_1241536983=1;");
        resultCookie.append(cookieList.get(1));
        resultCookie.append(cookieList.get(0));
        resultCookie.append(cookieList.get(4));
        cookie = resultCookie.toString();
        // logger.debug("cookies after rebuild: {}",cookie);
        System.out.println("result:" + cookie);
    }
}


public String SecurityFourthStep(Map<String,String> createMap){
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap1 = createMap;
    String result = httpClientUtil.doPost_2(newerUrl_1, createMap1, cookie);
    // logger.debug("新用户刚登陆需要确实安全升级第三步 :{} ",result);
    System.out.println("result:" + result);
    return result;
}


public String getQuestionContent(String name,String password,String code){
    initHeader();
    getVerifyCode();
    UserLogin(name, password, code);
    SecurityFirstStep();
    SecuritySecondStep();
    String questionStr = SecurityThirdStep();
    return questionStr;
}


@SuppressWarnings("static-access")
public void getVerifyCode(){
    String time = String.valueOf(new Date().getTime());
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap = new HashMap<String, String>();
    createMap.put("org.apache.struts.taglib.html.TOKEN", inputNameValue.get(ConstantUtil.TOKEN_NAME));
    String httpOrgCreateRtn = httpClientUtil.downloadImage_3(imageUrl + time, createMap, cookie);
    System.out.println("result:" + httpOrgCreateRtn);
}


public void UserLogin(String username,String password,String code){
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap = new HashMap<String, String>();
    // createMap.put("loginname","superDCH");
    // createMap.put("password","liuliming321");
    createMap.put("loginname", username);
    createMap.put("password", password);
    createMap.put("method", "login");
    createMap.put("date", "1463573916280");
    createMap.put("_@IMGRC@_", code);
    createMap.put("org.apache.struts.taglib.html.TOKEN", inputNameValue.get(ConstantUtil.TOKEN_NAME));
    String httpOrgCreateRtn = httpClientUtil.doPost_1(exectLoginUrl, createMap, cookie);
    // logger.debug("the result of verifyCode is {} ",httpOrgCreateRtn);
    System.out.println("result:" + httpOrgCreateRtn);
}


@SuppressWarnings({ "unused", "static-access" })
public void SecurityFirstStep(){
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap = new HashMap<String, String>();
    String result = httpClientUtil.doGet(newerUrl, cookie);
    // logger.debug("新用户刚登陆需要确实安全升级下一步 :{} ",result);
    System.out.println("result:" + result);
}


public void SecuritySecondStep(){
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap = new HashMap<String, String>();
    createMap.put("method", "setSafetyLevelStep2");
    String result = httpClientUtil.doPost_1(newerUrl_1, createMap, cookie);
    inputNameValue = ParsePageUtil.parseInput(result);
    // logger.debug("新用户刚登陆需要确实安全升级第二步 :{} ",result);
    System.out.println("result:" + result);
}


}