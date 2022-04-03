package com.gs.controller;
 import ch.qos.logback.classic.Logger;
import com.gs.bean;
import com.gs.common.util.DateFormatUtil;
import com.gs.common.util.DateUtil;
import com.gs.common.util.RoleUtil;
import com.gs.service;
import org.activiti.engine.repository.Model;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.gs.Interface.CompanyService;
import com.gs.Interface.ChargeBillService;
import com.gs.Interface.UserService;
import com.gs.Interface.AppointmentService;
import com.gs.Interface.IncomingOutgoingService;
import com.gs.Interface.RemindService;
@Controller
@RequestMapping("/backstage")
public class BackstageController {

 private  Logger logger;

@Resource
 private  CompanyService companyService;

@Resource
 private  MaintainDetailService maintainDetailService;

@Resource
 private  MaintainRecordService maintainRecordService;

@Resource
 private  ChargeBillService chargeBillService;

@Resource
 private  MaintainFixAccService maintainFixAccService;

@Resource
 private  UserService userService;

@Resource
 private  AppointmentService appointmentService;

@Resource
 private  MaintainFixService maintainFixService;

@Resource
 private  IncomingOutgoingService incomingOutgoingService;

@Resource
 private  RemindService remindService;


@RequestMapping(value = "home", method = RequestMethod.GET)
public ModelAndView backstageHome(HttpSession session,HttpServletRequest request){
    logger.info("跳转到后台主页");
    User user = (User) session.getAttribute("user");
    ModelAndView mav = new ModelAndView("backstage/home");
    String roles = "系统超级管理员,系统普通管理员";
    if (RoleUtil.checkRoles(roles)) {
        List<Company> companyInfo = companyService.queryByCompanyInfo();
        mav.addObject("companyInfo", companyInfo);
    } else {
        List<User> userinfo = userService.queryByCompanyId(user.getCompanyId());
        List<Appointment> appinfo = appointmentService.queryByCompanyId(user.getCompanyId());
        List<MaintainFix> mainInfo = maintainFixService.queryByCompanyId(user.getCompanyId());
        List<IncomingOutgoing> incomInfo = incomingOutgoingService.queryByCompanyIdForInType(user.getCompanyId());
        List<IncomingOutgoing> outgoInfo = incomingOutgoingService.queryByCompanyIdForOutType(user.getCompanyId());
        List<Remind> remindInfo = remindService.selectRemind();
        mav.addObject("userinfo", userinfo);
        mav.addObject("appinfo", appinfo);
        mav.addObject("maininfo", mainInfo);
        mav.addObject("incomInfo", incomInfo);
        mav.addObject("outgoInfo", outgoInfo);
        mav.addObject("remindInfo", remindInfo);
    }
    return mav;
}


@RequestMapping(value = "chargeBillprint/{chargeBillId}", method = RequestMethod.GET)
public ModelAndView chargeBillprint(String chargeBillId){
    logger.info("跳转到收费单据打印页面");
    ChargeBill chargeBill = chargeBillService.queryById(chargeBillId);
    ModelAndView mav = new ModelAndView("backstage/chargeBillPrint");
    mav.addObject("chargeBill", chargeBill);
    return mav;
}


@RequestMapping(value = "recordPrint/{maintainRecordId}/{ids}", method = RequestMethod.GET)
public ModelAndView recordPrint(String maintainRecordId,String ids){
    logger.info("跳转到维修保养明细打印页面");
    ModelAndView mav = new ModelAndView("backstage/recordPrint");
    // 拿到选中的维修保养记录
    MaintainRecord maintainRecord = maintainRecordService.queryById(maintainRecordId);
    maintainRecord.setTodayTime(new Date());
    // 拿到此维修保养记录下所有明细
    List<MaintainDetail> maintainDetails = maintainDetailService.queryByRecordId(maintainRecordId);
    Double disCountMoney = 0.d;
    Double money = 0.d;
    for (MaintainDetail md : maintainDetails) {
        if (md.getMaintainDiscount() >= 1) {
            md.setDisCount("无折扣");
        } else {
            String str = md.getMaintainDiscount().toString();
            String str1 = str.substring(str.indexOf(".") + 1);
            md.setDisCount(str1 + "折");
        }
        money += md.getMaintainFix().getMaintainMoney();
        disCountMoney += md.getMaintainDiscount() * md.getMaintainFix().getMaintainMoney();
    }
    maintainRecord.setDiscountMoney(disCountMoney);
    maintainRecord.setTotal(money);
    // 拿到所有项目配件
    List<MaintainFixAcc> maintainFixAccs = maintainFixAccService.queryByRecord(ids);
    // 项目配件
    mav.addObject("maintainFixAccs", maintainFixAccs);
    // 维修保养记录
    mav.addObject("maintainRecord", maintainRecord);
    // 维修保养明细
    mav.addObject("maintainDetails", maintainDetails);
    return mav;
}


}