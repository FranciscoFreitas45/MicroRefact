package cn.com.cnc.fcc.security.jwt;
 import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import cn.com.cnc.fcc.web.rest.errors.PAPIException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
public class JWTFilter extends GenericFilterBean{

 public  String AUTHORIZATION_HEADER;

 private  TokenProvider tokenProvider;

public JWTFilter(TokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
}
@Override
public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse,FilterChain filterChain){
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    String jwt = resolveToken(httpServletRequest);
    boolean isToken = this.tokenProvider.validateToken(jwt);
    if (StringUtils.hasText(jwt) && isToken) {
        Authentication authentication = this.tokenProvider.getAuthentication(jwt);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    if (httpServletRequest.getRequestURI().indexOf("papi") > 0 && httpServletRequest.getRequestURI().indexOf("gettoken") < 0) {
        if (jwt == null) {
            throw new PAPIException(10004);
        } else if (!isToken) {
            throw new PAPIException(10002);
        }
    }
    filterChain.doFilter(servletRequest, servletResponse);
}


public String resolveToken(HttpServletRequest request){
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
        return bearerToken.substring(7);
    } else if (StringUtils.hasText(bearerToken) && bearerToken.indexOf("Bearer") <= 0) {
        return bearerToken;
    }
    return null;
}


}