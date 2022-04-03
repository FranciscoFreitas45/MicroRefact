package com.cym.config;
 import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.cym.model.Admin;
import com.cym.service.AdminService;
import com.cym.utils.JsonResult;
import com.cym.utils.MessageUtils;
import cn.hutool.json.JSONUtil;
import com.cym.Interface.MessageUtils;
import com.cym.DTO.JsonResult;
@Component
public class ApiInterceptor implements HandlerInterceptor{

@Autowired
 private MessageUtils m;

@Autowired
 private AdminService adminService;


@Override
public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1,Object arg2,Exception arg3){
}


@Override
public void postHandle(HttpServletRequest arg0,HttpServletResponse arg1,Object arg2,ModelAndView arg3){
}


@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object obj){
    String token = request.getHeader("token");
    Admin admin = adminService.getByToken(token);
    if (admin != null && admin.getApi()) {
        return true;
    } else {
        JsonResult result = new JsonResult();
        result.setSuccess(false);
        result.setStatus("401");
        result.setMsg(m.get("apiStr.wrongToken"));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.append(JSONUtil.toJsonPrettyStr(result));
        return false;
    }
}


}