package com.ipe.module.core.web.security;
 import com.ipe.module.core.web.util.WebUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
public class CustFormAuthenticationFilter extends FormAuthenticationFilter{


@Override
public boolean executeLogin(ServletRequest request,ServletResponse response){
    CustUsernamePasswordToken token = (CustUsernamePasswordToken) createToken(request, response);
    try {
        Subject subject = getSubject(request, response);
        // 正常验证
        subject.login(token);
        return onLoginSuccess(token, subject, request, response);
    } catch (AuthenticationException e) {
        return onLoginFailure(token, e, request, response);
    }
}


public String getCaptcha(ServletRequest request){
    return request.getParameter("captcha");
}


@Override
public AuthenticationToken createToken(ServletRequest request,ServletResponse response){
    String username = getUsername(request);
    String password = getPassword(request);
    String captcha = getCaptcha(request);
    String host = WebUtil.getIpAddr((HttpServletRequest) request);
    String method = ((HttpServletRequest) request).getMethod();
    String accessUrl = ((HttpServletRequest) request).getRequestURL().toString();
    return new CustUsernamePasswordToken(username, password, host, method, captcha, accessUrl);
}


}