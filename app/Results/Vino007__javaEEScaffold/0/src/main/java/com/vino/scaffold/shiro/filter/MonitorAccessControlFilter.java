package com.vino.scaffold.shiro.filter;
 import java.util.Set;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.vino.scaffold.shiro.service.UserService;
public class MonitorAccessControlFilter extends AccessControlFilter{

@Autowired
 private  UserService userService;


@Override
public boolean isAccessAllowed(ServletRequest request,ServletResponse response,Object arg2) throws Exception{
    Subject subject = getSubject(request, response);
    String username = (String) subject.getPrincipal();
    if (// δ��¼
    username == null)
        return false;
    Set<String> permissions = userService.findAllPermissionsByUsername(username);
    for (String permission : permissions) {
        if ("monitor:view".equals(permission)) {
            return true;
        }
    }
    return false;
}


@Override
public boolean onAccessDenied(ServletRequest request,ServletResponse response) throws Exception{
    // ����404 not found,����login�����û��ѵ�¼���ᵼ��login��Ч
    WebUtils.issueRedirect(request, response, "/static/404.html");
    return false;
}


}