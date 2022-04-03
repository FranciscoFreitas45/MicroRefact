package com.cym.controller.api;
 import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cym.model.Admin;
import com.cym.service.AdminService;
import com.cym.utils.BaseController;
import com.cym.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
@Api(tags = "获取token")
@RestController
@RequestMapping("token")
public class TokenController extends BaseController{

@Autowired
 private AdminService adminService;


@PostMapping("getToken")
public JsonResult getToken(String name,String pass){
    // 用户名密码
    Admin admin = adminService.login(name, pass);
    if (admin == null || !admin.getApi()) {
        // 用户名密码错误
        return renderError(m.get("loginStr.backError2"));
    }
    Map<String, String> map = new HashMap<String, String>();
    map.put("token", adminService.makeToken(admin.getId()));
    return renderSuccess(map);
}


}