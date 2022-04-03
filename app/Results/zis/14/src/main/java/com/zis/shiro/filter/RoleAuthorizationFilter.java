package com.zis.shiro.filter;
 import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
public class RoleAuthorizationFilter extends AuthorizationFilter{


public boolean onAccessDenied(ServletRequest request,ServletResponse response){
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    Subject subject = getSubject(request, response);
    if (subject.getPrincipal() == null) {
        if (ShiroFilterUtils.isAjax(httpRequest)) {
            ShiroFilterUtils.out(httpResponse, "您未登陆 或者 登陆超时");
        } else {
            saveRequestAndRedirectToLogin(request, response);
        }
    } else {
        if (ShiroFilterUtils.isAjax(httpRequest)) {
            ShiroFilterUtils.out(httpResponse, "您没有足够的权限执行该操作!");
        } else {
            String unauthorizedUrl = getUnauthorizedUrl();
            if (StringUtils.hasText(unauthorizedUrl)) {
                WebUtils.issueRedirect(request, response, unauthorizedUrl);
            } else {
                WebUtils.toHttp(response).sendError(401);
            }
        }
    }
    return false;
}


public boolean isAccessAllowed(ServletRequest request,ServletResponse response,Object mappedValue){
    Subject subject = getSubject(request, response);
    String[] rolesArray = (String[]) mappedValue;
    if (rolesArray == null || rolesArray.length == 0) {
        // no roles specified, so nothing to check - allow access.
        return true;
    }
    Set<String> roles = CollectionUtils.asSet(rolesArray);
    for (String role : roles) {
        if (subject.hasRole(role)) {
            return true;
        }
    }
    return false;
}


}