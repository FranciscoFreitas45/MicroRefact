package com.netease.service;
 import java.awt.image.BufferedImage;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.netease.credit.test.SSLClient;
import com.netease.domain.User;
import com.netease.dto.QuestionPageDTO;
import com.netease.repositories.UserRepository;
import com.netease.util.ConstantUtil;
import com.netease.util.HttpClientUtil;
import com.netease.util.ParsePageUtil;
@Service
public class LoginService {

 final  Logger logger;

 private  String charset;

 private  String loginUrl;

 private  String exectLoginUrl;

 private  String queryReportUrl;

 private  String imageUrl;

 private  String getSimpleReportUrl;

 private  String questionAnswerUrl;

 private  Header[] headers;

 private  List<String> cookieList;

 private  String cookie;

 private  Map<String,String> inputNameValue;

@Autowired
 private  UserRepository userRepository;


public Map<String,List<String>> extractQuestionPage(){
    String questionPage = fetchQuestionPageBeforeGetReport();
    return ParsePageUtil.extractQuestion(questionPage);
}


public String clickApplyingInfo(){
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap = new HashMap<String, String>();
    createMap.put(ConstantUtil.METHOD, ConstantUtil.APPLICATION_REPORT);
    String reportContent = httpClientUtil.doPostRequest(ConstantUtil.APPLY_REPORTURL, createMap, cookie);
    // inputNameValue = ParsePageUtil.parseInput(reportContent);
    logger.debug(" clickApplyingInfo result is {}", reportContent);
    return reportContent;
}


public String exectLogin(String loginname,String password,String mgrc){
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap = new HashMap<String, String>();
    createMap.put("loginname", loginname);
    createMap.put("password", password);
    createMap.put("method", "login");
    createMap.put("date", "1463573916280");
    createMap.put("_@IMGRC@_", mgrc);
    createMap.put("org.apache.struts.taglib.html.TOKEN", "26cc5591147b2d34ae3320bce2e0b383");
    String execlogin = httpClientUtil.doPost_0(exectLoginUrl, createMap, cookie);
    logger.debug("exec login result is {}", execlogin);
    return execlogin;
}


public Map<String,String> getInputNameValue(){
    return inputNameValue;
}


public String submitQuestionPage(QuestionPageDTO pageDTO,boolean flag){
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap = new HashMap<String, String>();
    createMap.put(ConstantUtil.METHOD, "");
    createMap.put(ConstantUtil.TOKEN_NAME, inputNameValue.get(ConstantUtil.TOKEN_NAME));
    createMap.put(ConstantUtil.AUTHTYPE, "2");
    createMap.put(ConstantUtil.APPLICATION_OPTION, "21");
    createMap.put("kbaList[0].answerresult", pageDTO.getAnswerResult0());
    createMap.put("kbaList[0].options", pageDTO.getAnswerResult0());
    createMap.put("kbaList[1].answerresult", pageDTO.getAnswerResult1());
    createMap.put("kbaList[1].options", pageDTO.getAnswerResult1());
    createMap.put("kbaList[2].answerresult", pageDTO.getAnswerResult2());
    createMap.put("kbaList[2].options", pageDTO.getAnswerResult2());
    createMap.put("kbaList[3].answerresult", pageDTO.getAnswerResult3());
    createMap.put("kbaList[3].options", pageDTO.getAnswerResult3());
    createMap.put("kbaList[4].answerresult", pageDTO.getAnswerResult4());
    createMap.put("kbaList[4].options", pageDTO.getAnswerResult4());
    buildSubmitQuestionParameter(createMap);
    // if (flag) {//报告已经生成，重新获取
    // createMap.put(ConstantUtil.FLAG, ConstantUtil.TRUE);
    // } else {
    // createMap.put(ConstantUtil.FLAG, ConstantUtil.FALSE);
    // }
    // 提交回答问题页面后返回的响应页面
    String questionResponsePage = httpClientUtil.doPostRequest(ConstantUtil.SUBMIT_REPORT_QUESTION, createMap, cookie);
    logger.info("response after submit question: " + questionResponsePage);
    String extractResult = ParsePageUtil.extractResponseAfterSubmitQuestion(questionResponsePage);
    return extractResult;
}


@SuppressWarnings("static-access")
public void getDynamicImg(){
    String time = String.valueOf(new Date().getTime());
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap = new HashMap<String, String>();
    createMap.put("org.apache.struts.taglib.html.TOKEN", "26cc5591147b2d14ae3320bce2e0b283");
    String httpOrgCreateTestRtn = httpClientUtil.downloadImage_3(imageUrl + time, createMap, cookie);
}


public void buildSubmitQuestionParameter(Map<String,String> createMap){
    createMap.put("kbaList[0].derivativecode", inputNameValue.get("kbaList[0].derivativecode"));
    createMap.put("kbaList[1].derivativecode", inputNameValue.get("kbaList[1].derivativecode"));
    createMap.put("kbaList[2].derivativecode", inputNameValue.get("kbaList[2].derivativecode"));
    createMap.put("kbaList[3].derivativecode", inputNameValue.get("kbaList[3].derivativecode"));
    createMap.put("kbaList[4].derivativecode", inputNameValue.get("kbaList[4].derivativecode"));
    createMap.put("kbaList[0].businesstype", inputNameValue.get("kbaList[0].businesstypee"));
    createMap.put("kbaList[1].businesstype", inputNameValue.get("kbaList[1].businesstypee"));
    createMap.put("kbaList[2].businesstype", inputNameValue.get("kbaList[2].businesstypee"));
    createMap.put("kbaList[3].businesstype", inputNameValue.get("kbaList[3].businesstypee"));
    createMap.put("kbaList[4].businesstype", inputNameValue.get("kbaList[4].businesstypee"));
    createMap.put("kbaList[0].questionno", inputNameValue.get("kbaList[0].questionno"));
    createMap.put("kbaList[1].questionno", inputNameValue.get("kbaList[1].questionno"));
    createMap.put("kbaList[2].questionno", inputNameValue.get("kbaList[2].questionno"));
    createMap.put("kbaList[3].questionno", inputNameValue.get("kbaList[3].questionno"));
    createMap.put("kbaList[4].questionno", inputNameValue.get("kbaList[4].questionno"));
    createMap.put("kbaList[0].kbanum", inputNameValue.get("kbaList[0].kbanum"));
    createMap.put("kbaList[1].kbanum", inputNameValue.get("kbaList[1].kbanum"));
    createMap.put("kbaList[2].kbanum", inputNameValue.get("kbaList[2].kbanum"));
    createMap.put("kbaList[3].kbanum", inputNameValue.get("kbaList[3].kbanum"));
    createMap.put("kbaList[4].kbanum", inputNameValue.get("kbaList[4].kbanum"));
    createMap.put("kbaList[0].question", inputNameValue.get("kbaList[0].question"));
    createMap.put("kbaList[1].question", inputNameValue.get("kbaList[1].question"));
    createMap.put("kbaList[2].question", inputNameValue.get("kbaList[2].question"));
    createMap.put("kbaList[3].question", inputNameValue.get("kbaList[3].question"));
    createMap.put("kbaList[4].question", inputNameValue.get("kbaList[4].question"));
    createMap.put("kbaList[0].options1", inputNameValue.get("kbaList[0].options1"));
    createMap.put("kbaList[1].options1", inputNameValue.get("kbaList[1].options1"));
    createMap.put("kbaList[2].options1", inputNameValue.get("kbaList[2].options1"));
    createMap.put("kbaList[3].options1", inputNameValue.get("kbaList[3].options1"));
    createMap.put("kbaList[4].options1", inputNameValue.get("kbaList[4].options1"));
    createMap.put("kbaList[0].options2", inputNameValue.get("kbaList[0].options2"));
    createMap.put("kbaList[1].options2", inputNameValue.get("kbaList[1].options2"));
    createMap.put("kbaList[2].options2", inputNameValue.get("kbaList[2].options2"));
    createMap.put("kbaList[3].options2", inputNameValue.get("kbaList[3].options2"));
    createMap.put("kbaList[4].options2", inputNameValue.get("kbaList[4].options2"));
    createMap.put("kbaList[0].options3", inputNameValue.get("kbaList[0].options3"));
    createMap.put("kbaList[1].options3", inputNameValue.get("kbaList[1].options3"));
    createMap.put("kbaList[2].options3", inputNameValue.get("kbaList[2].options3"));
    createMap.put("kbaList[3].options3", inputNameValue.get("kbaList[3].options3"));
    createMap.put("kbaList[4].options3", inputNameValue.get("kbaList[4].options3"));
    createMap.put("kbaList[0].options4", inputNameValue.get("kbaList[0].options4"));
    createMap.put("kbaList[1].options4", inputNameValue.get("kbaList[1].options4"));
    createMap.put("kbaList[2].options4", inputNameValue.get("kbaList[2].options4"));
    createMap.put("kbaList[3].options4", inputNameValue.get("kbaList[3].options4"));
    createMap.put("kbaList[4].options4", inputNameValue.get("kbaList[4].options4"));
    createMap.put("kbaList[0].options5", inputNameValue.get("kbaList[0].options5"));
    createMap.put("kbaList[1].options5", inputNameValue.get("kbaList[1].options5"));
    createMap.put("kbaList[2].options5", inputNameValue.get("kbaList[2].options5"));
    createMap.put("kbaList[3].options5", inputNameValue.get("kbaList[3].options5"));
    createMap.put("kbaList[4].options5", inputNameValue.get("kbaList[4].options5"));
}


public BufferedImage getDynamicImg_2(){
    String time = String.valueOf(new Date().getTime());
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap = new HashMap<String, String>();
    createMap.put("org.apache.struts.taglib.html.TOKEN", "26cc5591147b2d14ae3320bce2e0b283");
    BufferedImage httpOrgCreateTestRtn = httpClientUtil.downloadImage(imageUrl + time, createMap, cookie);
    logger.info("图片地址：" + imageUrl);
    return httpOrgCreateTestRtn;
}


public String queryReport(){
    @SuppressWarnings("unused")
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    String reportContent = HttpClientUtil.doGet(queryReportUrl, cookie);
    logger.debug(" queryReport result is {}", reportContent);
    return reportContent;
}


public String getCreditContent(String tradeCode){
    // initHeader(); //初始化请求头信息
    // getDynamicImg(); //获取动态验证码
    // exectLogin(loginname,password,mgrc); //模拟执行登陆
    // 跳转到征信报告查询页面
    queryReport();
    // 传入手机查询短信号，获取征信报告模板
    String creditResult = getViewReport(tradeCode);
    return creditResult;
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
    logger.debug(" loginregister result is {}", result);
}


public void extractAndReconstructCookies(){
    if (headers != null) {
        // 提取Cookie
        // 先清空list，否则无法获取最新的cookie
        cookieList.clear();
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
        logger.info(cookie);
        logger.debug(" cookies after rebuild: {}", cookie);
    }
}


public String getViewReport(String tradeCode){
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap = new HashMap<String, String>();
    createMap.put("method", "viewReport");
    createMap.put("reportformat", "21");
    createMap.put("tradeCode", tradeCode);
    String viewReport = httpClientUtil.doPost_1(getSimpleReportUrl, createMap, cookie);
    logger.debug(" 征信报告明细如下: {}", viewReport);
    return viewReport;
}


public String fetchQuestionPageBeforeGetReport(){
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    Map<String, String> createMap = new HashMap<String, String>();
    // String status = getReportStatus();
    // createMap.put(ConstantUtil.METHOD, ConstantUtil.CHECK_ISHAS_REPORT);
    createMap.put(ConstantUtil.TOKEN_NAME, inputNameValue.get(ConstantUtil.TOKEN_NAME));
    createMap.put(ConstantUtil.AUTHTYPE, "2");
    createMap.put(ConstantUtil.APPLICATION_OPTION, "21");
    String httpOrgCreateTestRtn = "";
    createMap.put(ConstantUtil.METHOD, ConstantUtil.VERIFY);
    httpOrgCreateTestRtn = httpClientUtil.doPostRequest(ConstantUtil.REFETCH_QUESTION_URL, createMap, cookie);
    // if (status.equals(ConstantUtil.GENERATED)) {//如果报告已经生成了，还要重新获取，则访问ConstantUtil.REFETCH_QUESTION_URL
    // createMap.put(ConstantUtil.METHOD, ConstantUtil.VERIFY);
    // httpOrgCreateTestRtn = httpClientUtil.doPostRequest(ConstantUtil.REFETCH_QUESTION_URL
    // , createMap, cookie);
    // } else {
    // httpOrgCreateTestRtn = httpClientUtil.doPostRequest(ConstantUtil.QUESTION_BEFORE_GETREPORT,
    // createMap, cookie);
    // String message = ParsePageUtil.parseDivMessage(httpOrgCreateTestRtn);
    // if (message.contains(ConstantUtil.EXIST)) {//虽然验证未通过，但是可能之前申请的身份码还没有过期，这个时候其实报告也是已经生成了，所以要想重新获取问题，则需要发送一个新的请求
    // createMap.put(ConstantUtil.METHOD, ConstantUtil.VERIFY);
    // httpOrgCreateTestRtn = httpClientUtil.doPostRequest(ConstantUtil.REFETCH_QUESTION_URL
    // , createMap, cookie);
    // }
    // }
    inputNameValue = ParsePageUtil.parseInput(httpOrgCreateTestRtn);
    logger.debug("   result is {}", httpOrgCreateTestRtn);
    return httpOrgCreateTestRtn;
}


public BufferedImage getImage(){
    // 初始化请求头信息
    initHeader();
    BufferedImage bi = getDynamicImg_2();
    return bi;
}


public String getReportStatus(){
    String responsePage = clickApplyingInfo();
    return ParsePageUtil.parseReportStatus(responsePage);
}


}