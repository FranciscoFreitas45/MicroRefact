package upce.semprace.eshop.security.jwt;
 import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint{

 private  Logger logger;


@Override
public void commence(HttpServletRequest request,HttpServletResponse response,AuthenticationException e){
    logger.error("Unauthorized error. Message - {}", e.getMessage());
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error -> Unauthorized");
}


}