package com.kingen.web;
 import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kingen.aop.ControllerLogAnnotation;
import com.kingen.bean.StoreRoom;
import com.kingen.service.storeRoom.StoreRoomService;
import com.kingen.util.Json;
import com.kingen.util.JsonResultBuilder;
import com.kingen.util.Page;
import com.kingen.util.TreeConverter;
import com.kingen.util.mapper.BeanMapper;
import com.kingen.vo.TreeNode;
@Controller
@RequestMapping(value = "/storeRoom")
public class StoreRoomController extends CommonController{

 private  Logger logger;

@Autowired
 private  StoreRoomService service;


@RequestMapping(value = "/treeData")
@ResponseBody
public Object data(Page<StoreRoom> page,HttpServletResponse response){
    List<StoreRoom> all = service.list();
    List<TreeNode> allConverted = BeanMapper.mapList(all, TreeNode.class);
    // JsonResultBuilder.success(true).data(TreeConverter.toComplexJsonString(allConverted)).json();
    // Json json = new Json();
    // List<Map<String, Object>> children = Lists.newArrayList(TreeConverter.tree(allPermissions));
    // json.setChildren(children);//静态树必须这么玩
    // json.setSuccess(true);
    // writeJson(response,json);
    // return ExtUtils.toComplexJson(offices);
    Map<String, Object> json = Maps.newHashMapWithExpectedSize(1);
    // 必须要加chidlren,直接return toComplexJsonString(offices);不行.而且jsonString里面中文会变成???乱码
    json.put("children", TreeConverter.toComplexTree(allConverted));
    return json;
}


@ControllerLogAnnotation(moduleName = "配置管理-库房管理", option = "删除")
@RequestMapping(value = "deleteCascade")
public void deleteCascade(String id,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        service.delCascade(id);
        json = JsonResultBuilder.success(true).msg("删除成功").json();
    } catch (Exception e) {
        json = JsonResultBuilder.success(false).msg("系统出错").json();
        logger.error(e.getMessage());
        e.printStackTrace();
    }
    writeJson(response, json);
}


@RequestMapping(value = "/one")
public void one(String id,HttpServletResponse response){
    StoreRoom u = service.unique(id);
    writeJson(response, u);
}


@RequestMapping(value = "save")
@ControllerLogAnnotation(moduleName = "配置管理-库房管理", option = "新增")
public void save(StoreRoom data,HttpServletResponse response){
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
@ControllerLogAnnotation(moduleName = "配置管理-库房管理", option = "编辑")
public void update(StoreRoom data,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        service.updateForm(data);
        json = JsonResultBuilder.success(true).msg("保存成功").json();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        json = JsonResultBuilder.success(false).msg(e.getMessage()).json();
        logger.error(e.getMessage());
    }
    writeJson(response, json);
}


@RequestMapping(value = "toEdit")
public String toEdit(String id,String pid,String action,HttpServletResponse response,HttpServletRequest request,Model model){
    model.addAttribute("action", action);
    // null的话 前台是空串
    model.addAttribute("id", id);
    // null的话 前台是空串
    model.addAttribute("pid", pid);
    return "storeRoom/edit";
}


@RequestMapping(value = "/")
@RequiresPermissions("storeRoom:view")
@ControllerLogAnnotation(moduleName = "配置管理", option = "库房管理")
public String execute(Model m,HttpServletResponse response){
    return "storeRoom/tree";
}


}