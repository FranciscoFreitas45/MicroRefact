package com.kingen.web;
 import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.kingen.aop.ControllerLogAnnotation;
import com.kingen.bean.SysOrg;
import com.kingen.bean.User;
import com.kingen.service.account.AccountService;
import com.kingen.service.org.OrgService;
import com.kingen.util.Json;
import com.kingen.util.Page;
@Controller
@RequestMapping(value = "/org")
public class OrgController extends CommonController{

 private  Logger logger;

@Autowired
 private  OrgService service;

@Autowired
 private  AccountService accountService;


@ControllerLogAnnotation(moduleName = "组管理", option = "删除用户")
// 必须用数组不能用List
@RequestMapping(value = "delUsers")
public void delUsers(String orgId,String[] userIds,HttpServletResponse response,Boolean synToActiviti){
    Json json = new Json();
    try {
        service.delOrgUsers(orgId, Arrays.asList(userIds), synToActiviti);
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


@ControllerLogAnnotation(moduleName = "组管理", option = "同步用户、组、关系，到activiti")
// 必须用数组不能用List
@RequestMapping(value = "synAllUserAndGroupToActiviti")
public void synAllUserAndGroupToActiviti(HttpServletResponse response){
    Json json = new Json();
    try {
        service.synAllUserAndGroupToActiviti();
        json.setSuccess(true);
        json.setMsg("同步成功");
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        json.setSuccess(false);
        json.setMsg(e.getMessage());
        logger.error(e.getMessage());
    }
    writeJson(response, json);
}


@RequestMapping(value = "one")
public void one(SysOrg org,HttpServletResponse response){
    SysOrg u = service.one(org);
    writeJson(response, u);
}


@ControllerLogAnnotation(moduleName = "组管理", option = "保存组")
@RequestMapping(value = "saveOrg")
public void saveOrg(SysOrg data,HttpServletResponse response,Boolean synToActiviti){
    Json json = new Json();
    try {
        service.create(data, synToActiviti);
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


@ControllerLogAnnotation(moduleName = "组管理", option = "保存用户")
// 必须用数组不能用List
@RequestMapping(value = "saveUsers")
public void saveUsers(String orgId,String[] userIds,HttpServletResponse response,Boolean synToActiviti){
    Json json = new Json();
    try {
        service.saveOrgUsers(orgId, Arrays.asList(userIds), synToActiviti);
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


@RequestMapping(value = "toEdit")
public String toEdit(String orgId,String action,HttpServletResponse response,HttpServletRequest request,Model model){
    model.addAttribute("action", action);
    // null的话 前台是空串
    model.addAttribute("orgId", orgId);
    return "org/orgEidt";
}


@ControllerLogAnnotation(moduleName = "组管理", option = "更新组")
@RequestMapping(value = "updateOrg")
public void updateOrg(SysOrg data,HttpServletResponse response,Boolean synToActiviti){
    Json json = new Json();
    try {
        service.update(data, synToActiviti);
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


@RequestMapping(value = "orgUsers")
public void orgUsers(String orgId,Page<User> page,HttpServletResponse response){
    page = service.getOrgUsers(page, orgId);
    writeJson(response, page);
}


@ControllerLogAnnotation(moduleName = "组管理", option = "查看")
@RequiresPermissions("org:view")
@RequestMapping(value = "/")
public String execute(HttpServletResponse response){
    return "org/orgs";
}


@RequestMapping(value = "toUsersAdd")
public String toUsersAdd(String orgId,HttpServletResponse response,HttpServletRequest request,Model model){
    // null的话 前台是空串
    model.addAttribute("orgId", orgId);
    return "org/usersRemain";
}


@RequestMapping(value = "orgUsersRemain")
public void orgUsersRemain(String orgId,Page<User> page,HttpServletResponse response){
    page = service.getOrgUsersRemain(page, orgId);
    writeJson(response, page);
}


@RequiresPermissions("org:view")
@RequestMapping(value = "listOrgs")
public void listOrgs(Page<SysOrg> page,HttpServletResponse response){
    page = service.getOrgs(page, null);
    writeJson(response, page);
}


@ControllerLogAnnotation(moduleName = "组管理", option = "删除组")
// 必须用数组不能用List
@RequestMapping(value = "delOrg")
public void delOrg(String orgId,HttpServletResponse response,Boolean synToActiviti){
    Json json = new Json();
    try {
        service.delOrg(orgId, synToActiviti);
        json.setSuccess(true);
        json.setMsg("删除成功");
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        json.setSuccess(false);
        json.setMsg(e.getMessage());
        logger.error(e.getMessage());
    }
    writeJson(response, json);
}


}