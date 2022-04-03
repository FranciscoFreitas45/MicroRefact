package net.shangtech.weixin.sys.interceptor;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shangtech.weixin.sys.entity.SysUser;
import net.shangtech.weixin.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
public class WeixinInterceptor implements HandlerInterceptor{

@Autowired
 private  SysUserService sysUserService;


@Override
public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1,Object arg2,Exception arg3){
// TODO Auto-generated method stub
}


@Override
public void postHandle(HttpServletRequest arg0,HttpServletResponse arg1,Object arg2,ModelAndView arg3){
// TODO Auto-generated method stub
}


@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
    String appid = request.getParameter("a");
    SysUser user = sysUserService.findByAppid(appid);
    request.setAttribute("sysUser", user);
    return true;
}


}