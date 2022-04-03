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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;
import com.kingen.aop.ControllerLogAnnotation;
import com.kingen.bean.Supplier;
import com.kingen.service.supplier.SupplierService;
import com.kingen.util.JsonResultBuilder;
import com.kingen.util.Page;
@Controller
@RequestMapping(value = "/supplier")
public class SupplierController extends CommonController{

 private  Logger logger;

@Autowired
 private  SupplierService service;


@RequestMapping(value = "/data/{type}")
public void data(String type,Page<Supplier> page,HttpServletResponse response){
    page = service.data(page, type);
    writeJson(response, page);
}


@RequestMapping(value = "/one")
public void one(String id,HttpServletResponse response){
    Supplier u = service.unique(id);
    writeJson(response, u);
}


@ControllerLogAnnotation(moduleName = "服务管理", option = "供应商管理")
@RequiresPermissions("supplier:view")
public String supplier(){
    return "supplier/list";
}


@RequestMapping(value = "save")
@ControllerLogAnnotation(moduleName = "配置管理-厂商管理", option = "新增")
public void save(Supplier data,HttpServletResponse response){
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


@RequestMapping(value = "update")
@ControllerLogAnnotation(moduleName = "配置管理-厂商管理", option = "修改")
public void update(Supplier data,HttpServletResponse response){
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


@RequestMapping(value = "toEdit/{type}")
public String toEdit(String type,String id,String action,HttpServletResponse response,HttpServletRequest request,Model model){
    model.addAttribute("type", type);
    model.addAttribute("action", action);
    // null的话 前台是空串
    model.addAttribute("id", id);
    return "supplier/edit";
}


@ControllerLogAnnotation(moduleName = "配置管理-厂商管理", option = "删除")
@RequestMapping(value = "deleteThem")
public void deleteThem(String[] ids,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        service.delThem(Arrays.asList(ids), "id");
        json = JsonResultBuilder.success(true).msg("删除成功").json();
    } catch (Exception e) {
        json = JsonResultBuilder.success(false).msg("系统出错").json();
        logger.error(e.getMessage());
        e.printStackTrace();
    }
    writeJson(response, json);
}


@RequestMapping(value = "/{type}")
// @ControllerLogAnnotation(moduleName="服务管理",option="供应商/厂商")
// TODO 几个权限相加 and 之类的
@RequiresPermissions("manufacturer:view")
public String execute(String type,Model m,HttpServletResponse response){
    m.addAttribute("type", type);
    if ("supplier".equals(type)) {
        // 供应商
        return supplier();
    } else if ("manufacturer".equals(type)) {
        // 厂商
        return manufacturer();
    }
    return "";
}


@ControllerLogAnnotation(moduleName = "服务管理", option = "厂商管理")
@RequiresPermissions("manufacturer:view")
public String manufacturer(){
    return "supplier/list";
}


}