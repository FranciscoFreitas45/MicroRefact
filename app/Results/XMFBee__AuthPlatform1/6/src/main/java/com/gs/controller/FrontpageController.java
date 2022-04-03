package com.gs.controller;
 import ch.qos.logback.classic.Logger;
import com.gs.bean.Appointment;
import com.gs.bean.Company;
import com.gs.bean.MaintainDetail;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.controller.customerBooking.PhoneReservationController;
import com.gs.service.AppointmentService;
import com.gs.service.CompanyService;
import com.gs.service.MaintainDetailService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import com.gs.Interface.AppointmentService;
import com.gs.Interface.MaintainDetailService;
import com.gs.DTO.Appointment;
@Controller
@RequestMapping("/")
public class FrontpageController {

 private  Logger logger;

@Resource
 private  CompanyService companyService;

@Resource
 private  AppointmentService appointmentService;

@Resource
 private  MaintainDetailService maintainDetailService;


@RequestMapping(value = "factorydeta", method = RequestMethod.GET)
public String deta(){
    return "Frontpage/Factorydeta";
}


@RequestMapping(value = "userpage", method = RequestMethod.GET)
public String userpage(){
    return "Frontpage/UserIndex";
}


@ResponseBody
@RequestMapping(value = "addfrontrese", method = RequestMethod.POST)
public ControllerResult add(Appointment appointment,HttpSession session){
    User user = (User) session.getAttribute("frontUser");
    logger.info("添加电话预约");
    if (appointment != null) {
        appointment.setCompanyId(user.getCompanyId());
        appointment.setCurrentStatus("已预约");
        appointmentService.insert(appointment);
        return ControllerResult.getSuccessResult("预约成功");
    } else {
        return ControllerResult.getFailResult("预约失败");
    }
}


@RequestMapping(value = "common", method = RequestMethod.GET)
public String aboutus(){
    return "Frontpage/commonproblem";
}


@RequestMapping(value = "reg", method = RequestMethod.GET)
public String reg(){
    return "Frontpage/registered";
}


@RequestMapping(value = "resepage", method = RequestMethod.GET)
public String resepage(){
    return "Frontpage/resepage";
}


@RequestMapping(value = "factoryreg", method = RequestMethod.GET)
public String factoryreg(){
    return "user/login";
}


@RequestMapping(value = "platformIntro", method = RequestMethod.GET)
public String Goplatform(){
    return "Frontpage/PlatformIntro";
}


@RequestMapping(value = "home", method = RequestMethod.GET)
public ModelAndView Home(HttpSession session){
    ModelAndView mav = new ModelAndView("Frontpage/Frontindex");
    // 查询所有公司信息
    List<Company> company = companyService.queryAll((User) session.getAttribute("user"));
    mav.addObject("company", company);
    Pager pager = new Pager();
    // 查询前十条维修保养记录
    List<MaintainDetail> queryList = maintainDetailService.queryByFrontpage(pager);
    mav.addObject("queryList", queryList);
    return mav;
}


@RequestMapping(value = "factorypage", method = RequestMethod.GET)
public ModelAndView Homes(HttpServletRequest request,HttpSession session){
    List<Company> company = companyService.queryAll((User) session.getAttribute("user"));
    request.setAttribute("companypage", company);
    ModelAndView mav = new ModelAndView("Frontpage/Factorypage");
    return mav;
}


}