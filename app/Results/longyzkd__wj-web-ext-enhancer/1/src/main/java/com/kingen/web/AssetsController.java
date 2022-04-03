package com.kingen.web;
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
import com.google.common.collect.Maps;
import com.kingen.aop.ControllerLogAnnotation;
import com.kingen.bean.AssetsType;
import com.kingen.service.assets.AssetsService;
import com.kingen.util.JsonResultBuilder;
import com.kingen.util.Page;
import com.kingen.util.TreeConverter;
import com.kingen.util.mapper.BeanMapper;
import com.kingen.vo.TreeNode;
import Interface.AssetsService;
@Controller
@RequestMapping(value = "/assets")
public class AssetsController extends CommonController{

 private  Logger logger;

@Autowired
 private  AssetsService service;


@RequestMapping(value = "/type/treeData")
@ResponseBody
public Object data(Page<AssetsType> page,HttpServletResponse response){
    List<AssetsType> all = service.list("AssetsType");
    List<TreeNode> allConverted = BeanMapper.mapList(all, TreeNode.class);
    Map<String, Object> json = Maps.newHashMapWithExpectedSize(1);
    // 必须要加chidlren,直接return toComplexJsonString(offices);不行.而且jsonString里面中文会变成???乱码
    json.put("children", TreeConverter.toComplexTree(allConverted));
    return json;
}


@ControllerLogAnnotation(moduleName = "配置管理-资产类型管理", option = "删除")
@RequestMapping(value = "type/deleteCascade")
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


@RequestMapping(value = "type/one")
public void one(String id,HttpServletResponse response){
    AssetsType u = service.uniqueEntity("AssetsType", "id", id);
    writeJson(response, u);
}


@RequestMapping(value = "type/toEdit")
public String typetoEdit(String id,String pid,String action,HttpServletResponse response,HttpServletRequest request,Model model){
    model.addAttribute("action", action);
    // null的话 前台是空串
    model.addAttribute("id", id);
    // null的话 前台是空串
    model.addAttribute("pid", pid);
    return "assets/type/edit";
}


@RequestMapping(value = "type/save")
@ControllerLogAnnotation(moduleName = "配置管理-资产类型管理", option = "新增")
public void save(AssetsType data,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        service.addObj(data);
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


@RequestMapping(value = "type/update")
@ControllerLogAnnotation(moduleName = "配置管理-资产类型管理", option = "编辑")
public void update(AssetsType data,HttpServletResponse response){
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


@RequestMapping(value = "/type")
@RequiresPermissions("assetsType:view")
@ControllerLogAnnotation(moduleName = "配置管理", option = "资产类型管理")
public String execute(Model m,HttpServletResponse response){
    return "assets/type/tree";
}


}