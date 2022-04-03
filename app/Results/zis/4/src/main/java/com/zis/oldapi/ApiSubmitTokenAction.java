package com.zis.oldapi;
 import java.util.Random;
import org.apache.log4j.Logger;
import com.zis.api.response.TokenResponse;
import com.zis.common.util.ZisUtils;
public class ApiSubmitTokenAction extends BaseApiAction{

 private  long serialVersionUID;

 private  Logger logger;


public String generateToken(){
    return ZisUtils.getDateString("yyyyMMddHHmmss") + new Random().nextInt(100);
}


public String getToken(){
    logger.info("api.ApiSubmitTokenAction::requestSubmitToken.");
    // 生成token
    String token = generateToken();
    // 把token放入当前session中
    setSessionToken(token);
    // 设置返回结果
    TokenResponse response = new TokenResponse();
    response.setCode(TokenResponse.CODE_SUCCESS);
    response.setToken(token);
    renderResult(response);
    return SUCCESS;
}


}