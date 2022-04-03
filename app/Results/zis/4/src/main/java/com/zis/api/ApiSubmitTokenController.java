package com.zis.api;
 import java.util.Random;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.api.response.TokenResponse;
import com.zis.common.util.ZisUtils;
@Controller
@RequestMapping(value = "/api")
public class ApiSubmitTokenController extends BaseApiController{

 private  Logger logger;


public String generateToken(){
    return ZisUtils.getDateString("yyyyMMddHHmmss") + new Random().nextInt(100);
}


@RequestMapping(value = "/getToken", produces = "text/plain; charset=utf-8")
public String getToken(HttpServletResponse httpServletResponse){
    logger.info("api.ApiSubmitTokenAction::requestSubmitToken.");
    // 生成token
    String token = generateToken();
    // 把token放入当前session中
    setSessionToken(token);
    // 设置返回结果
    TokenResponse response = new TokenResponse();
    response.setCode(TokenResponse.CODE_SUCCESS);
    response.setToken(token);
    renderResult(response, httpServletResponse);
    return "";
}


}