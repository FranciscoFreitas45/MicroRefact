package kielce.tu.weaii.telelearn.security;
 import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import kielce.tu.weaii.telelearn.security.Constants.RESPONSE_WITH_UNAUTH_ERROR;
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{


@Override
public void commence(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,AuthenticationException e){
    log.error(RESPONSE_WITH_UNAUTH_ERROR, e.getClass(), e.getMessage());
    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
}


}