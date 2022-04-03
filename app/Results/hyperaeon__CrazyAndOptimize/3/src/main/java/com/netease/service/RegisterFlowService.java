package com.netease.service;
 import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.netease.util.ConstantUtil;
import com.netease.util.HttpClientUtil;
import com.netease.util.ParsePageUtil;
import com.netease.util.SSLClient;
import com.netease.vo.UserInfoVO;
@Service
public class RegisterFlowService {

 private  Logger logger;

 private  String imageUrl;

 private  String checkLoginNameUrl;

 private  Header[] headers;

 private  List<String> cookieList;

 private  String cookie;

 private  Map<String,String> inputNameValue;

 private  String tcId;


public BufferedImage initAndGetImg(){
    logger.info("initAndGetImg");
    getLoginReg();
    getUserRegPage();
    return getDynamicImg();
}


public String checkRegLoginnameHasUsed(UserInfoVO userInfoVO){
    Map<String, String> paramMap = new HashMap<String, String>();
    // 必须传正确的值，否则返回302 Moved Temporarily
    paramMap.put(ConstantUtil.TOKEN_NAME, inputNameValue.get(ConstantUtil.TOKEN_NAME));
    // 点击提交触发的方法
    paramMap.put(ConstantUtil.METHOD, ConstantUtil.CHECK_REG_LOGINNAME_HASUSED);
    // 登录名
    paramMap.put(ConstantUtil.USER_LOGIN_NAME, userInfoVO.getLoginName());
    return HttpClientUtil.doAjaxPost(checkLoginNameUrl, paramMap, cookie);
}


public String postSecondReg(UserInfoVO userInfoVO){
    Map<String, String> paramMap = new HashMap<String, String>();
    // 必须传正确的值，否则返回302 Moved Temporarily
    paramMap.put(ConstantUtil.TOKEN_NAME, inputNameValue.get(ConstantUtil.TOKEN_NAME));
    // 点击提交触发的方法
    paramMap.put(ConstantUtil.METHOD, inputNameValue.get(ConstantUtil.METHOD));
    // 登录名
    paramMap.put(ConstantUtil.USER_LOGIN_NAME, userInfoVO.getLoginName());
    // 密码
    paramMap.put(ConstantUtil.USER_LOGIN_PASSWORD, userInfoVO.getPassword());
    // 确认密码
    paramMap.put(ConstantUtil.USER_LOGIN_COMFIRM_PASSWORD, userInfoVO.getConfirmPassword());
    // 电子邮箱
    paramMap.put(ConstantUtil.USER_EMAIL, userInfoVO.getEmail());
    // 手机号码
    paramMap.put(ConstantUtil.USER_MOBILE, userInfoVO.getMobileTel());
    // 动态码，在隐藏域中，由ajax请求获取
    paramMap.put(ConstantUtil.TCID, userInfoVO.getTcId());
    // 获取动态码时的倒计时，不能低于2
    paramMap.put(ConstantUtil.COUNTTIME, userInfoVO.getCounttime());
    // 显示填写的动态码
    paramMap.put(ConstantUtil.USER_VERIFY_CODE, userInfoVO.getVerifyCode());
    String postSecondRegPage = HttpClientUtil.doPost(ConstantUtil.REGURL, paramMap, cookie);
    inputNameValue = ParsePageUtil.parseInput(postSecondRegPage);
    if (isMovedTemporarily(postSecondRegPage)) {
        // 如果为302 Moved Temporarily则返回302
        return ConstantUtil.TEMPORARILY_MOVED_CODE;
    }
    return ParsePageUtil.parseErrorField(postSecondRegPage);
}


public String getDynamicCode(String phoneNumber){
    Map<String, String> inputMap = new HashMap<String, String>();
    // 请求方法
    inputMap.put(ConstantUtil.METHOD, ConstantUtil.GET_ACTIVITAVE_CODE);
    // 手机号码
    inputMap.put(ConstantUtil.MOBILE, phoneNumber);
    return HttpClientUtil.doAjaxPost(ConstantUtil.REGURL, inputMap, cookie);
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
    }
}


public BufferedImage getDynamicImg(){
    String time = String.valueOf(new Date().getTime());
    logger.info("getDynamicImg");
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap = new HashMap<String, String>();
    return httpClientUtil.downloadImage(imageUrl + time, createMap, cookie);
}


public boolean isMovedTemporarily(String postSecondRegPage){
    if (!StringUtils.isEmpty(postSecondRegPage) && postSecondRegPage.contains("302")) {
        return true;
    }
    return false;
}


public void main(String[] args){
    RegisterFlowService registerFlow = new RegisterFlowService();
    registerFlow.getLoginReg();
    registerFlow.getUserRegPage();
    // 注册第一步需要填写的信息;
    UserInfoVO userInfoVO = new UserInfoVO();
    userInfoVO.setName("王恒沙");
    userInfoVO.setCertNo("110108198102133428");
    userInfoVO.setCheckCode("sigsvj");
    String errorMessage = registerFlow.inputBasicInfo(userInfoVO);
    // 获取动态码
    String phoneNumber = "13449375755";
    tcId = registerFlow.getDynamicCode(phoneNumber);
    // 注册第二部需要填写的信息
    UserInfoVO userInfoVO2 = new UserInfoVO();
    userInfoVO2.setLoginName("super");
    userInfoVO2.setPassword("abcd321");
    userInfoVO2.setConfirmPassword("abcd321");
    userInfoVO2.setEmail("");
    userInfoVO2.setMobileTel(phoneNumber);
    userInfoVO2.setTcId(tcId);
    userInfoVO2.setCounttime("2");
    userInfoVO2.setVerifyCode("");
    String postError = registerFlow.postSecondReg(userInfoVO2);
}


public void getLoginReg(){
    logger.info("getLoginReg");
    HttpClient httpClient;
    HttpGet get = new HttpGet(ConstantUtil.LOGIN_URL);
    HttpResponse response;
    try {
        httpClient = new SSLClient();
        response = httpClient.execute(get);
        // 获取响应部分的所有header
        headers = response.getAllHeaders();
    } catch (Exception e) {
        logger.error("getLoginReg()方法获取首页header信息失败", e);
    }
    extractAndReconstructCookies();
}


public void getUserRegPage(){
    logger.info("getUserRegPage");
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put(ConstantUtil.METHOD, ConstantUtil.INIT_REG);
    String regPage = HttpClientUtil.doPost(ConstantUtil.REGURL, paramMap, cookie);
    inputNameValue = ParsePageUtil.parseInput(regPage);
}


public String inputBasicInfo(UserInfoVO userInfoVO){
    Map<String, String> paramMap = new HashMap<String, String>();
    // 必须传正确的值，否则返回302 Moved Temporarily
    paramMap.put(ConstantUtil.TOKEN_NAME, inputNameValue.get(ConstantUtil.TOKEN_NAME));
    // 点击下一步触发的方法
    paramMap.put(ConstantUtil.METHOD, inputNameValue.get(ConstantUtil.METHOD));
    // 姓名
    paramMap.put(ConstantUtil.USER_NAME, userInfoVO.getName());
    // 证件类型
    paramMap.put(ConstantUtil.USER_CERT_TYPE, userInfoVO.getCertType());
    // 证件号码
    paramMap.put(ConstantUtil.USER_CERTNO, userInfoVO.getCertNo());
    // 验证码
    paramMap.put(ConstantUtil.IDENTIFYING_CODE, userInfoVO.getCheckCode());
    // 勾选“我已阅读并同意”
    paramMap.put(ConstantUtil.PROTOCOL, userInfoVO.getCheckAgreement());
    String inputResponsePage = HttpClientUtil.doPost(ConstantUtil.REGURL, paramMap, cookie);
    // 解析补充用户信息页面中的input标签
    inputNameValue = ParsePageUtil.parseInput(inputResponsePage);
    return ParsePageUtil.parseErrorField(inputResponsePage);
}


}