package com.qidian.hcm.common.security;
 import com.alibaba.fastjson.JSON;
import com.qidian.hcm.common.utils.ResultGenerator;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import com.qidian.hcm.common.utils.ResultCode.UNAUTHORIZED;
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{


@Override
public void commence(HttpServletRequest request,HttpServletResponse response,AuthenticationException authException){
    printError(response, JSON.toJSONString(ResultGenerator.genFailResult(UNAUTHORIZED)));
}


public void printError(HttpServletResponse response,String str){
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    response.getWriter().println(str);
    response.getWriter().close();
}


}