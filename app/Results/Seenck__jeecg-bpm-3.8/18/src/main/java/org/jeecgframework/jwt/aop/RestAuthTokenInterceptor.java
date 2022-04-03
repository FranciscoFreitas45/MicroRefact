package org.jeecgframework.jwt.aop;
 import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.jwt.def.JwtConstants;
import org.jeecgframework.jwt.model.TokenModel;
import org.jeecgframework.jwt.service.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
public class RestAuthTokenInterceptor implements HandlerInterceptor{

@Autowired
 private  TokenManager manager;

 private  List<String> excludeUrls;

 private  List<String> excludeContainUrls;


public List<String> getExcludeUrls(){
    return excludeUrls;
}


public void setExcludeUrls(List<String> excludeUrls){
    this.excludeUrls = excludeUrls;
}


public void setExcludeContainUrls(List<String> excludeContainUrls){
    this.excludeContainUrls = excludeContainUrls;
}


public boolean moHuContain(List<String> list,String key){
    for (String str : list) {
        if (key.contains(str)) {
            return true;
        }
    }
    return false;
}


@Override
public void afterCompletion(HttpServletRequest httpservletrequest,HttpServletResponse httpservletresponse,Object obj,Exception exception){
// TODO Auto-generated method stub
}


@Override
public void postHandle(HttpServletRequest httpservletrequest,HttpServletResponse httpservletresponse,Object obj,ModelAndView modelandview){
// TODO Auto-generated method stub
}


@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object obj){
    String requestPath = request.getRequestURI().substring(request.getContextPath().length());
    if (requestPath.indexOf("/rest/") == -1 || excludeUrls.contains(requestPath) || moHuContain(excludeContainUrls, requestPath)) {
        return true;
    }
    // 从header中得到token
    String authHeader = request.getHeader(JwtConstants.AUTHORIZATION);
    if (authHeader == null) {
        throw new ServletException("Missing or invalid X-AUTH-TOKEN header.");
    }
    // 验证token
    Claims claims = null;
    try {
        claims = Jwts.parser().setSigningKey(JwtConstants.JWT_SECRET).parseClaimsJws(authHeader).getBody();
    } catch (final SignatureException e) {
        throw new ServletException("Invalid token.");
    }
    Object username = claims.getId();
    if (oConvertUtils.isEmpty(username)) {
        throw new ServletException("Invalid X-AUTH-TOKEN Subject no exist username.");
    }
    TokenModel model = manager.getToken(authHeader, username.toString());
    if (manager.checkToken(model)) {
        // 如果token验证成功，将对象传递给下一个请求
        request.setAttribute(JwtConstants.CURRENT_TOKEN_CLAIMS, claims);
        // 如果token验证成功，将token对应的用户id存在request中，便于之后注入
        request.setAttribute(JwtConstants.CURRENT_USER_NAME, model.getUsername());
        return true;
    } else {
        // 如果验证token失败，则返回401错误
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}


public List<String> getExcludeContainUrls(){
    return excludeContainUrls;
}


}