package com.lingxiang2014.filter;
 import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import com.lingxiang2014.AuthenticationToken;
import com.lingxiang2014.service.RSAService;
public class AuthenticationFilter extends FormAuthenticationFilter{

 private  String DEFAULT_EN_PASSWORD_PARAM;

 private  String DEFAULT_CAPTCHA_ID_PARAM;

 private  String DEFAULT_CAPTCHA_PARAM;

 private  String enPasswordParam;

 private  String captchaIdParam;

 private  String captchaParam;

@Resource(name = "rsaServiceImpl")
 private  RSAService rsaService;


public String getCaptchaParam(){
    return captchaParam;
}


@Override
public String getPassword(ServletRequest servletRequest){
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    String password = rsaService.decryptParameter(enPasswordParam, request);
    rsaService.removePrivateKey(request);
    return password;
}


public void setCaptchaIdParam(String captchaIdParam){
    this.captchaIdParam = captchaIdParam;
}


@Override
public boolean onLoginSuccess(org.apache.shiro.authc.AuthenticationToken token,Subject subject,ServletRequest servletRequest,ServletResponse servletResponse){
    Session session = subject.getSession();
    Map<Object, Object> attributes = new HashMap<Object, Object>();
    Collection<Object> keys = session.getAttributeKeys();
    for (Object key : keys) {
        attributes.put(key, session.getAttribute(key));
    }
    session.stop();
    session = subject.getSession();
    for (Entry<Object, Object> entry : attributes.entrySet()) {
        session.setAttribute(entry.getKey(), entry.getValue());
    }
    return super.onLoginSuccess(token, subject, servletRequest, servletResponse);
}


@Override
public boolean onAccessDenied(ServletRequest servletRequest,ServletResponse servletResponse){
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String requestType = request.getHeader("X-Requested-With");
    if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
        response.addHeader("loginStatus", "accessDenied");
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }
    return super.onAccessDenied(request, response);
}


public void setEnPasswordParam(String enPasswordParam){
    this.enPasswordParam = enPasswordParam;
}


public void setCaptchaParam(String captchaParam){
    this.captchaParam = captchaParam;
}


public String getCaptcha(ServletRequest servletRequest){
    return WebUtils.getCleanParam(servletRequest, captchaParam);
}


public String getCaptchaId(ServletRequest servletRequest){
    String captchaId = WebUtils.getCleanParam(servletRequest, captchaIdParam);
    if (captchaId == null) {
        captchaId = ((HttpServletRequest) servletRequest).getSession().getId();
    }
    return captchaId;
}


@Override
public org.apache.shiro.authc.AuthenticationToken createToken(ServletRequest servletRequest,ServletResponse servletResponse){
    String username = getUsername(servletRequest);
    String password = getPassword(servletRequest);
    String captchaId = getCaptchaId(servletRequest);
    String captcha = getCaptcha(servletRequest);
    boolean rememberMe = isRememberMe(servletRequest);
    String host = getHost(servletRequest);
    return new AuthenticationToken(username, password, captchaId, captcha, rememberMe, host);
}


public String getCaptchaIdParam(){
    return captchaIdParam;
}


public String getEnPasswordParam(){
    return enPasswordParam;
}


}