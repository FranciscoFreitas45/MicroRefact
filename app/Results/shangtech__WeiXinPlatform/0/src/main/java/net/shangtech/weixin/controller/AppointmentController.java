package net.shangtech.weixin.controller;
 import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.weixin.appointment.entity.Appointment;
import net.shangtech.weixin.appointment.entity.AppointmentHistory;
import net.shangtech.weixin.appointment.service.AppointmentService;
import net.shangtech.weixin.site.entity.SiteTemplate;
import net.shangtech.weixin.site.service.SiteTemplateService;
import net.shangtech.weixin.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import net.shangtech.Interface.SiteTemplateService;
@Controller(value = "weixinAppointmentController")
@RequestMapping("/weixin/application/appointment")
public class AppointmentController extends BaseController{

@Autowired
 private  AppointmentService service;

@Autowired
 private  SiteTemplateService tempService;


@RequestMapping("/view")
public String view(HttpServletRequest request){
    Integer id = getId(request);
    Appointment appointment = service.find(id);
    request.setAttribute("appointment", appointment);
    SiteTemplate temp = tempService.find(appointment.getTempId());
    request.setAttribute("temp", temp);
    return "weixin/appointment/view";
}


@RequestMapping("/save")
public String save(HttpServletRequest request,HttpServletResponse response){
    String xm = request.getParameter("xm");
    String tel = request.getParameter("tel");
    Integer appointment = getInt(request, "appointment");
    AppointmentHistory history = new AppointmentHistory();
    history.setCreateTime(new Date());
    history.setRealName(xm);
    history.setTel(tel);
    history.setAppointmentId(appointment);
    service.saveAppointmentHistory(history);
    return success(response);
}


@RequestMapping("/list")
public String list(HttpServletRequest request){
    SysUser user = getUser(request);
    List<Appointment> list = service.findBySysUserId(user.getId());
    request.setAttribute("list", list);
    request.setAttribute("url", "view.htm");
    request.setAttribute("tip", "我要参加");
    return "weixin/appointment/list";
}


}