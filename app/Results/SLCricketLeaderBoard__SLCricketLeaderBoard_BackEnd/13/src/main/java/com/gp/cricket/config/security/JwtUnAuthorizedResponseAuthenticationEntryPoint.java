package com.gp.cricket.config.security;
 import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
@Component
public class JwtUnAuthorizedResponseAuthenticationEntryPoint implements Serializable,AuthenticationEntryPoint{

 private  long serialVersionUID;


@Override
public void commence(HttpServletRequest request,HttpServletResponse response,AuthenticationException authException){
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You would need to provide the Jwt Token to Access This resource");
}


}