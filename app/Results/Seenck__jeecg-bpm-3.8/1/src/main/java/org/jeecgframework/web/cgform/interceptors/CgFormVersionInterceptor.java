package org.jeecgframework.web.cgform.interceptors;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.apache.log4j.Logger;
import org.jeecgframework.core.interceptors.AuthInterceptor;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import Interface.CgFormFieldServiceI;
public class CgFormVersionInterceptor implements HandlerInterceptor{

 private  List<String> includeUrls;

 private  Logger logger;

@Autowired
 private  CgFormFieldServiceI cgFormFieldService;


public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1,Object arg2,Exception arg3){
    // 用户访问的资源地址
    String requestPath = ResourceUtil.getJgAuthRequsetPath(arg0);
    if (!includeUrls.contains(requestPath)) {
        return;
    }
    // 获取表单ID
    String formId = arg0.getParameter("formId");
    if (StringUtil.isEmpty(formId)) {
        return;
    } else {
        try {
            cgFormFieldService.updateVersion(formId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
        }
    }
}


public void postHandle(HttpServletRequest arg0,HttpServletResponse arg1,Object arg2,ModelAndView arg3){
}


public boolean preHandle(HttpServletRequest arg0,HttpServletResponse arg1,Object arg2){
    return true;
}


public void setIncludeUrls(List<String> includeUrls){
    this.includeUrls = includeUrls;
}


public List<String> getIncludeUrls(){
    return includeUrls;
}


}