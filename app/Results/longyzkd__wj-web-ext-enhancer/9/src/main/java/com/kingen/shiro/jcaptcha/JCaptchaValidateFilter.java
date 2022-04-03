package com.kingen.shiro.jcaptcha;
 import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
public class JCaptchaValidateFilter extends AccessControlFilter{

 private  boolean jcaptchaEbabled;

 private  String jcaptchaParam;

 private  String failureKeyAttribute;


public void setJcaptchaEbabled(boolean jcaptchaEbabled){
    this.jcaptchaEbabled = jcaptchaEbabled;
}


public void setFailureKeyAttribute(String failureKeyAttribute){
    this.failureKeyAttribute = failureKeyAttribute;
}


@Override
public boolean isAccessAllowed(ServletRequest request,ServletResponse response,Object mappedValue){
    // 1、设置验证码是否开启属性，页面可以根据该属性来决定是否显示验证码
    request.setAttribute("jcaptchaEbabled", jcaptchaEbabled);
    HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
    // 2、判断验证码是否禁用 或不是表单提交（允许访问）
    if (jcaptchaEbabled == false || !"post".equalsIgnoreCase(httpServletRequest.getMethod())) {
        return true;
    }
    // 3、此时是表单提交，验证验证码是否正确
    // return JCaptcha.validateResponse(httpServletRequest, httpServletRequest.getParameter(jcaptchaParam));
    return JCaptcha2.validateResponse(httpServletRequest, httpServletRequest.getParameter(jcaptchaParam));
}


@Override
public boolean onAccessDenied(ServletRequest request,ServletResponse response){
    // 如果验证码失败了，存储失败key属性
    request.setAttribute(failureKeyAttribute, "jCaptcha.error");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    out.println("{success:false,msg:'验证码错误'}");
    out.flush();
    out.close();
    // return false;
    return true;
}


public void setJcaptchaParam(String jcaptchaParam){
    this.jcaptchaParam = jcaptchaParam;
}


}