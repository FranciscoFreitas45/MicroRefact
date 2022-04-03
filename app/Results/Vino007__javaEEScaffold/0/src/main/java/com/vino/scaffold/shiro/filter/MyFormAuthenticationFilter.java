package com.vino.scaffold.shiro.filter;
 import java.util.Date;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import com.vino.scaffold.shiro.entity.User;
import com.vino.scaffold.shiro.service.UserService;
public class MyFormAuthenticationFilter extends FormAuthenticationFilter{

@Autowired
 private  UserService userService;


@Override
public boolean onLoginSuccess(AuthenticationToken token,Subject subject,ServletRequest request,ServletResponse response) throws Exception{
    // TODO Auto-generated method stub
    User curUser = userService.findByUsername((String) subject.getPrincipal());
    // ���õ�¼ʱ����ϴε�¼ʱ��
    if (curUser.getLoginTime() != null) {
        curUser.setLastLoginTime(curUser.getLoginTime());
    }
    curUser.setLoginTime(new Date());
    userService.update(curUser);
    System.out.println("ִ��myfilter");
    return super.onLoginSuccess(token, subject, request, response);
}


@Override
public boolean executeLogin(ServletRequest arg0,ServletResponse arg1) throws Exception{
    // TODO Auto-generated method stub
    return super.executeLogin(arg0, arg1);
}


}