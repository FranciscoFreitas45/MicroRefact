package com.kingen.web;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kingen.aop.ControllerLogAnnotation;
import com.kingen.bean.Menu;
import com.kingen.bean.MenuData;
import com.kingen.bean.User;
import com.kingen.service.account.AccountService;
import com.kingen.util.ActivitiUtils;
import com.kingen.util.Constants;
import com.kingen.util.Json;
import com.kingen.util.JsonResultBuilder;
import com.kingen.util.Page;
@Controller
@RequestMapping(value = "/user")
public class UserController extends CommonController{

 private  Logger logger;

 public  String ADMIN;

@Autowired
 private  AccountService service;

@Autowired
 private  SessionDAO sessionDao;


@RequestMapping("/{sessionId}/forceLogout")
@ControllerLogAnnotation(moduleName = "??????????????????", option = "????????????")
@ResponseBody
public JSONObject forceLogout(String sessionId,RedirectAttributes redirectAttributes){
    try {
        Session session = sessionDao.readSession(sessionId);
        if (session != null) {
            session.setAttribute(Constants.SESSION_FORCE_LOGOUT_KEY, Boolean.TRUE);
        }
    } catch (Exception e) {
    /*ignore*/
    }
    // redirectAttributes.addFlashAttribute("msg", "?????????????????????");
    JSONObject json = JsonResultBuilder.success(true).msg("?????????????????????").json();
    return json;
// return "redirect:/userAction/listSession";
}


@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
@ControllerLogAnnotation(moduleName = "????????????", option = "????????????")
public void updatePwd(String userId,String password,HttpServletRequest request,HttpServletResponse response,HttpSession session){
    Json json = new Json();
    try {
        if (StringUtils.isBlank(userId)) {
            // ??????????????????
            User current = getCurrentUser();
            current.setPassword(password);
            service.updatePwd(current);
        } else {
            // ?????????????????????
            User current = service.findUserByLoginName(userId);
            current.setPassword(password);
            service.updatePwd(current);
        }
        json.setSuccess(true);
        json.setMsg("????????????");
        writeJson(response, json);
    } catch (Exception e) {
        // TODO ???????????????
        logger.error(e.getMessage());
        e.printStackTrace();
        json.setSuccess(false);
        json.setMsg("????????????");
        writeJson(response, json);
    }
}


@RequestMapping(value = "listData")
public void listData(Page<User> page,User user,HttpServletResponse response){
    page = service.getUsers(page, user);
    writeJson(response, page);
}


@RequestMapping(value = "restUsers")
@ControllerLogAnnotation(moduleName = "????????????", option = "??????????????????")
public void restUsers(String[] userIds,HttpServletResponse response){
    Json json = new Json();
    try {
        service.updateUsersPwd(Arrays.asList(userIds));
        json.setSuccess(true);
        json.setMsg("????????????");
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
public void one(User user,HttpServletResponse response){
    User u = service.one(user);
    writeJson(response, u);
}


@RequestMapping(value = "updateUser")
@ControllerLogAnnotation(moduleName = "????????????", option = "????????????")
public void updateUser(User data,String[] funidvalue,HttpServletResponse response,Boolean synToActiviti){
    Json json = new Json();
    try {
        service.updateUser(data, funidvalue, synToActiviti);
        json.setSuccess(true);
        json.setMsg("????????????");
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
public String toEdit(String userId,String action,HttpServletResponse response,HttpServletRequest request,Model model){
    model.addAttribute("action", action);
    // null?????? ???????????????
    model.addAttribute("userId", userId);
    return "account/tmanageruserrEidt";
}


@RequestMapping(value = "deleteThem")
public void deleteThem(String[] userIds,HttpServletResponse response,Boolean synToActiviti){
    Json json = new Json();
    try {
        service.delThem(Arrays.asList(userIds), synToActiviti);
        json.setSuccess(true);
        json.setMsg("????????????");
    } catch (Exception e) {
        json.setMsg("????????????");
        logger.error(e.getMessage());
        e.printStackTrace();
    }
    writeJson(response, json);
}


@RequiresPermissions("session:view")
@RequestMapping(value = "/listSession")
@ControllerLogAnnotation(moduleName = "??????????????????", option = "??????")
public String list(Model model){
    return "account/session-list";
}


@RequestMapping(value = "/listSession/data")
public void listSessionData(User user,HttpServletResponse response){
    Collection<Session> sessions = sessionDao.getActiveSessions();
    // model.addAttribute("sessions", sessions);
    // model.addAttribute("sessionCount", sessions.size());
    List<Map<String, Object>> result = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(sessions)) {
        for (Session s : sessions) {
            Map<String, Object> m = ActivitiUtils.MapSetter.mapSetterSession(s);
            result.add(m);
        }
    }
    writeJson(response, result);
}


@RequestMapping(value = "/")
@ControllerLogAnnotation(moduleName = "????????????", option = "??????")
@RequiresPermissions("user:view")
public String execute(HttpServletResponse response){
    // try{
    // service.testException();
    // }catch(Exception e){
    // e.printStackTrace();
    // throw new Exception("test");
    // }
    return "account/tmanageruser";
}


@RequestMapping(value = "delete")
public void delete(User user,HttpServletResponse response){
    Json json = new Json();
    try {
        service.del(user);
        json.setSuccess(true);
        json.setMsg("????????????");
    } catch (Exception e) {
        json.setMsg("????????????");
        logger.error(e.getMessage());
        e.printStackTrace();
    }
    writeJson(response, json);
}


public List<MenuData> getMenuTree(List<Menu> menus){
    List<MenuData> menuDatas = new ArrayList<MenuData>();
    String path = getRequest().getScheme() + "://" + getRequest().getServerName() + ":" + getRequest().getServerPort() + getRequest().getContextPath() + "/";
    for (Menu menu1 : menus) {
        if (StringUtils.isBlank(menu1.getFmenuId())) {
            // 1??????????????????menuData,??????????????????
            MenuData menuData = new MenuData();
            // ??????"null"
            menuData.setId(menu1.getMenuId() + "|" + (StringUtils.isEmpty(menu1.getFunUrl()) ? "" : menu1.getFunUrl()));
            menuData.setTitle(menu1.getMenuName());
            menuData.setIconCls(menu1.getIconUrl());
            if (menuData.getChildren() == null) {
                menuData.setChildren(new ArrayList<MenuData>());
            }
            List<MenuData> menuDatas1 = new ArrayList<MenuData>();
            for (Menu menu : menus) {
                if (StringUtils.isNotEmpty(menu.getFmenuId()) && menu.getFmenuId().equals(menu1.getMenuId())) {
                    MenuData menuData1 = new MenuData();
                    menuData1.setFMenuId(menu.getFmenuId());
                    menuData1.setId(menu.getMenuId() + "|" + (StringUtils.isEmpty(menu.getFunUrl()) ? "" : menu.getFunUrl()));
                    menuData1.setText(menu.getMenuName());
                    menuData1.setIcon(path + menu.getIconUrl());
                    menuData1.setExpanded(false);
                    menuData1.setLeaf(true);
                    menuDatas1.add(menuData1);
                }
            }
            menuData.setChildren(menuDatas1);
            menuDatas.add(menuData);
        }
    }
    return menuDatas;
}


@RequestMapping(value = "getMainMenu")
public void getMainMenu(HttpServletResponse response){
    User u = getCurrentUser();
    List<MenuData> menuTree = new ArrayList<MenuData>();
    if (u != null) {
        String userIdString = u.getUserId();
        List<Menu> menus;
        if (userIdString.equals(ADMIN)) {
            // ??????????????????
            menus = service.findAllMenus();
        } else {
            menus = service.findMenuByuserId(userIdString);
        }
        menuTree = getMenuTree(menus);
    }
    writeJson(response, menuTree);
}


@RequestMapping(value = "updateUsers")
@ControllerLogAnnotation(moduleName = "????????????", option = "??????????????????")
public void updateUsers(String[] userIds,String status,HttpServletResponse response){
    Json json = new Json();
    try {
        service.updateUser(Arrays.asList(userIds), status);
        json.setSuccess(true);
        json.setMsg("????????????");
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        json.setSuccess(false);
        json.setMsg(e.getMessage());
        logger.error(e.getMessage());
    }
    writeJson(response, json);
}


@RequestMapping(value = "updateCurrentPwd", method = RequestMethod.POST)
@ControllerLogAnnotation(moduleName = "?????????", option = "????????????")
public void updateCurrentPwd(String password,HttpServletRequest request,HttpServletResponse response,HttpSession session){
    JSONObject jsonObject = new JSONObject();
    try {
        User current = getCurrentUser();
        current.setPassword(password);
        service.updatePwd(current);
        jsonObject = JsonResultBuilder.success(true).msg("????????????").json();
    } catch (Exception e) {
        // TODO ???????????????
        logger.error(e.getMessage());
        e.printStackTrace();
        jsonObject = JsonResultBuilder.success(false).msg("????????????").json();
    }
    writeJson(response, jsonObject);
}


@RequestMapping(value = "saveUser")
@ControllerLogAnnotation(moduleName = "????????????", option = "????????????")
public void saveUser(User data,String[] funidvalue,HttpServletResponse response,Boolean synToActiviti){
    Json json = new Json();
    try {
        service.saveUser(data, funidvalue, synToActiviti);
        json.setSuccess(true);
        json.setMsg("????????????");
    } catch (Exception e) {
        // service ??????????????????????????????
        // TODO Auto-generated catch block
        e.printStackTrace();
        json.setSuccess(false);
        json.setMsg(e.getMessage());
        logger.error(e.getMessage());
    }
    writeJson(response, json);
}


}