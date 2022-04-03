package com.gs.controller;
 import ch.qos.logback.classic.Logger;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping("/systemManage")
public class SystemManageController {

 private  Logger logger;

@Resource
 private  RoleService roleService;


@RequiresAuthentication
@RequestMapping(value = "moduleManageIndex", method = RequestMethod.GET)
public ModelAndView moduleManage(HttpSession session){
    logger.info("跳转到模块管理页面");
    ModelAndView mav = new ModelAndView("systemManage/moduleManage");
    return mav;
}


@RequiresAuthentication
@RequestMapping(value = "perDistributionIndex", method = RequestMethod.GET)
public ModelAndView perDistribution(HttpSession session){
    logger.info("跳转到权限分配页面");
    ModelAndView mav = new ModelAndView("systemManage/permissionsDistribution");
    return mav;
}


@RequiresAuthentication
@RequestMapping(value = "roleManageIndex", method = RequestMethod.GET)
public String userRoleManage(){
    logger.info("跳转到人员角色管理页面");
    return "systemManage/roleManage";
}


@RequiresAuthentication
@RequestMapping(value = "flowIndex", method = RequestMethod.GET)
public String flow(){
    logger.info("跳转到流程管理页面");
    return "systemManage/flowManage";
}


@RequiresAuthentication
@RequestMapping(value = "perManageIndex", method = RequestMethod.GET)
public String perManage(){
    logger.info("跳转到权限管理页面");
    return "systemManage/permissionsManage";
}


}