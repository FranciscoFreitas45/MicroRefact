package com.kingen.web;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.common.collect.Lists;
import com.kingen.aop.ControllerLogAnnotation;
import com.kingen.bean.Menu;
import com.kingen.bean.RightData;
import com.kingen.bean.SysOrgMenu;
import com.kingen.service.account.AccountService;
import com.kingen.service.permission.PermissionService;
import com.kingen.util.Json;
import com.kingen.util.TreeConverter;
import Interface.AccountService;
import DTO.RightData;
import DTO.Json;
@Controller
@RequestMapping(value = "/permission")
public class PermissionController extends CommonController{

 private  long serialVersionUID;

@Autowired
 private  PermissionService service;

@Autowired
 private  AccountService accountservice;


public void setRightChecked(List<RightData> allPermissions,RightData rightData){
    rightData.setChecked(true);
    for (RightData rightData2 : allPermissions) {
        if (StringUtils.equals(rightData.getPmenuId(), rightData2.getMenuId())) {
            // 父节点
            rightData2.setChecked(true);
            setRightChecked(allPermissions, rightData2);
        }
    }
}


@ControllerLogAnnotation(moduleName = "权限管理", option = "分配权限")
@RequestMapping(value = "checkTree")
public void checkTree(String orgId,String[] permissionIds,HttpServletResponse response){
    // String node ，异步树，会传node过来，再根据此Node加载下级树
    Json json = new Json();
    try {
        service.editPermission(orgId, permissionIds);
        json.setSuccess(true);
        json.setMsg("保存成功");
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        json.setSuccess(false);
        json.setMsg(e.getMessage());
        logger.error(e.getMessage());
    }
    writeJson(response, json);
}


public List<RightData> iniRightDatas(){
    List<RightData> rightDatas = new ArrayList<RightData>();
    List<Menu> tmanagerfuns = accountservice.findAllMenus();
    if (tmanagerfuns != null && tmanagerfuns.size() > 0) {
        for (Menu tmanagerfun : tmanagerfuns) {
            RightData rightData = new RightData();
            rightData.setChecked(false);
            rightData.setExpanded(false);
            rightData.setMenuId(tmanagerfun.getMenuId());
            rightData.setMenuName(tmanagerfun.getMenuName());
            rightData.setPmenuId(tmanagerfun.getFmenuId());
            rightDatas.add(rightData);
        }
    }
    return rightDatas;
}


public void checked(List<RightData> allPermissions,List<SysOrgMenu> permissioned){
    for (RightData rightData : allPermissions) {
        for (SysOrgMenu tmanageruserfun : permissioned) {
            if (StringUtils.equals(rightData.getMenuId(), tmanageruserfun.getId().getMenuId())) {
                setRightChecked(allPermissions, rightData);
            }
        }
    }
}


@ControllerLogAnnotation(moduleName = "权限管理", option = "查看")
@RequiresPermissions("permission:view")
@RequestMapping(value = "/")
public String execute(HttpServletResponse response){
    return "permission/permissions";
}


@RequestMapping(value = "checkedTree")
public void checkedTree(String orgId,HttpServletResponse response){
    // String node ，异步树，会传node过来，再根据此Node加载下级树
    List<RightData> allPermissions = iniRightDatas();
    List<SysOrgMenu> permissioned = null;
    if (StringUtils.isNotEmpty(orgId)) {
        permissioned = accountservice.findMenusBy(orgId);
    }
    if (!CollectionUtils.isEmpty(permissioned)) {
        checked(allPermissions, permissioned);
    }
    Json json = new Json();
    List<Map<String, Object>> children = Lists.newArrayList(TreeConverter.tree(allPermissions));
    // 静态树必须这么玩
    json.setChildren(children);
    json.setSuccess(true);
    writeJson(response, json);
}


}