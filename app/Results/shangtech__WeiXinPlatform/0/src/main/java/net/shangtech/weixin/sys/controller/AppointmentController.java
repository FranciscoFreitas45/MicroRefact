package net.shangtech.weixin.sys.controller;
 import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.ssh.core.base.Page;
import net.shangtech.ssh.core.util.DateUtils;
import net.shangtech.ssh.core.util.FileUtils;
import net.shangtech.weixin.appointment.entity.Appointment;
import net.shangtech.weixin.appointment.service.AppointmentService;
import net.shangtech.weixin.site.entity.SiteTemplate;
import net.shangtech.weixin.site.service.SiteTemplateService;
import net.shangtech.weixin.sys.entity.SysUser;
import net.shangtech.weixin.type.SiteTemplateType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import net.shangtech.Interface.SiteTemplateService;
@Controller
@RequestMapping("/manage/application/appointment")
public class AppointmentController extends BaseController{

@Autowired
 private  AppointmentService service;

@Autowired
 private  SiteTemplateService tempService;

 private  String PATH;

 private  List<String> ALLOW_TYPES;


@RequestMapping("/template/form")
public String formTemplate(HttpServletRequest request){
    Integer id = getId(request);
    SiteTemplate temp = tempService.find(id);
    request.setAttribute("temp", temp);
    return PATH + "/temp-form";
}


@RequestMapping("/form")
public String form(HttpServletRequest request){
    List<SiteTemplate> tempList = tempService.findByType(SiteTemplateType.APPOINTMENT.getType());
    request.setAttribute("tempList", tempList);
    return PATH + "/form";
}


@RequestMapping("/template/list")
public String appointmentTemplates(){
    return null;
}


@RequestMapping("/save")
public String saveAppointment(HttpServletRequest request,HttpServletResponse response,Appointment appointment){
    SysUser user = getUser(request);
    String startTimeStr = request.getParameter("start_time");
    String endTimeStr = request.getParameter("end_time");
    appointment.setStartTime(DateUtils.parse(startTimeStr + ":00"));
    appointment.setEndTime(DateUtils.parse(endTimeStr + ":00"));
    appointment.setCreateTime(new Date());
    appointment.setSysUserId(user.getId());
    service.add(appointment);
    return success(response);
}


@RequestMapping("/delete")
public String deleteAppointment(HttpServletRequest request,HttpServletResponse response){
    Integer id = getId(request);
    service.delete(id);
    return success(response);
}


@RequestMapping("/list")
public String list(HttpServletRequest request){
    return listPage(request, "1");
}


@RequestMapping("/list/p{pageInfo}")
public String listPage(HttpServletRequest request,String pageInfo){
    int pageSize = super.parsePageSize(pageInfo, 10);
    int pageNo = super.parsePageNo(pageInfo);
    SysUser user = super.getUser(request);
    Page<Appointment> page = service.findAppointmentByPage(pageNo, pageSize, user.getId());
    request.setAttribute("page", page);
    return PATH + "/list";
}


@RequestMapping("/image/save")
public String saveImage(HttpServletRequest request,HttpServletResponse response){
    MultipartFile file = null;
    try {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        file = multipartRequest.getFile("image");
        if (file == null) {
            return failed(response, "文件上传失败");
        }
        String name = file.getOriginalFilename();
        if (StringUtils.isBlank(name) || !name.contains(".")) {
            return failed(response, "文件格式错误");
        }
        String extend = name.substring(name.lastIndexOf("."));
        if (!ALLOW_TYPES.contains(extend)) {
            return failed(response, "不支持的文件格式");
        }
        String path = FileUtils.saveStreamToFile(file.getInputStream(), file.getOriginalFilename());
        return success(response, path);
    } catch (ClassCastException e) {
        e.printStackTrace();
        return failed(response, "保存文件失败");
    } catch (IOException e) {
        e.printStackTrace();
        return failed(response, "保存文件失败");
    }
}


}