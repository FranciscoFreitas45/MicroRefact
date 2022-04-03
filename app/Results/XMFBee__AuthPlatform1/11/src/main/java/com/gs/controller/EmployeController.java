package com.gs.controller;
 import ch.qos.logback.classic.Logger;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
@Controller
@RequestMapping("/emp")
public class EmployeController {

 private  Logger logger;

@Resource
 private  UserService userService;


@RequiresAuthentication
@RequestMapping(value = "workInfo", method = RequestMethod.GET)
public String workInfo(){
    logger.info("员工工单跳转页面");
    return "emp/workInfo";
}


@RequiresAuthentication
@RequestMapping(value = "carOwnerInfo", method = RequestMethod.GET)
public String carOwnerInfo(){
    logger.info("个人资料跳转页面");
    return "emp/carOwnerInfo";
}


@RequiresAuthentication
@RequestMapping(value = "empInformation", method = RequestMethod.GET)
public String empInformation(){
    logger.info("员工基本信息跳转页面");
    return "emp/empInformation";
}


@RequiresAuthentication
@RequestMapping(value = "selfManage", method = RequestMethod.GET)
public String selfManage(){
    logger.info("个人资料跳转页面");
    return "emp/selfManage";
}


@RequiresAuthentication
@RequestMapping(value = "empWages", method = RequestMethod.GET)
public String empWages(){
    logger.info("员工工资跳转页面");
    return "emp/empWages";
}


}