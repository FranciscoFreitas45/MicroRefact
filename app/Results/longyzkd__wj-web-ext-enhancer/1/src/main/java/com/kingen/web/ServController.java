package com.kingen.web;
 import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;
import com.kingen.aop.ControllerLogAnnotation;
import com.kingen.bean.ServiceLv;
import com.kingen.service.serv.ServService;
import com.kingen.util.JsonResultBuilder;
import com.kingen.util.Page;
@Controller
@RequestMapping(value = "/serv")
public class ServController extends CommonController{

 private  Logger logger;

@Autowired
 private  ServService service;


@RequestMapping(value = "/serviceLv")
@ControllerLogAnnotation(moduleName = "服务管理", option = "服务水平管理")
@RequiresPermissions("serviceLv:view")
public String serviceLv(Model m,HttpServletResponse response){
    return "serv/list";
}


@RequestMapping(value = "serviceLv/data")
public void data(Page<ServiceLv> page,HttpServletResponse response){
    page = service.find(page);
    writeJson(response, page);
}


@RequestMapping(value = "serviceLv/one")
public void one(String id,HttpServletResponse response){
    ServiceLv u = service.unique(id);
    writeJson(response, u);
}


@RequestMapping(value = "serviceLv/save")
@ControllerLogAnnotation(moduleName = "服务管理-服务水平管理", option = "新增")
public void save(ServiceLv data,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        service.add(data);
        json = JsonResultBuilder.success(true).msg("保存成功").json();
    } catch (Exception e) {
        // service ：回滚、记录异常日志
        // TODO Auto-generated catch block
        e.printStackTrace();
        json = JsonResultBuilder.success(false).msg(e.getMessage()).json();
        logger.error(e.getMessage());
    }
    writeJson(response, json);
}


@RequestMapping(value = "serviceLv/update")
@ControllerLogAnnotation(moduleName = "服务管理-服务水平管理", option = "编辑")
public void update(ServiceLv data,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        service.update(data);
        json = JsonResultBuilder.success(true).msg("保存成功").json();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        json = JsonResultBuilder.success(false).msg(e.getMessage()).json();
        logger.error(e.getMessage());
    }
    writeJson(response, json);
}


@RequestMapping(value = "serviceLv/toEdit")
public String toEdit(String id,String action,HttpServletResponse response,HttpServletRequest request,Model model){
    model.addAttribute("action", action);
    // null的话 前台是空串
    model.addAttribute("id", id);
    return "serv/edit";
}


@ControllerLogAnnotation(moduleName = "服务管理-服务水平管理", option = "删除")
@RequestMapping(value = "serviceLv/deleteThem")
public void deleteThem(String[] ids,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        service.delThem(Arrays.asList(ids));
        json = JsonResultBuilder.success(true).msg("删除成功").json();
    } catch (Exception e) {
        json = JsonResultBuilder.success(false).msg("系统出错").json();
        logger.error(e.getMessage());
        e.printStackTrace();
    }
    writeJson(response, json);
}


}