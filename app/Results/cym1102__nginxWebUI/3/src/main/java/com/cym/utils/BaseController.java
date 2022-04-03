package com.cym.utils;
 import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.model.Admin;
import com.cym.service.AdminService;
import cn.craccd.sqlHelper.utils.SqlHelper;
import com.cym.Interface.AdminService;
public class BaseController {

@Autowired
 protected  SqlHelper sqlHelper;

@Autowired
 protected  AdminService adminService;

@Autowired
 protected  MessageUtils m;


public JsonResult renderAuthError(){
    JsonResult result = new JsonResult();
    result.setSuccess(false);
    result.setStatus("401");
    return result;
}


public JsonResult renderSuccess(Object obj){
    JsonResult result = renderSuccess();
    result.setObj(obj);
    return result;
}


public Admin getAdmin(HttpServletRequest request){
    Admin admin = (Admin) request.getSession().getAttribute("admin");
    if (admin == null) {
        String token = request.getHeader("token");
        admin = adminService.getByToken(token);
    }
    if (admin == null) {
        String creditKey = request.getParameter("creditKey");
        admin = adminService.getByCreditKey(creditKey);
    }
    return admin;
}


public JsonResult renderError(String msg){
    JsonResult result = renderError();
    result.setMsg(msg);
    return result;
}


}