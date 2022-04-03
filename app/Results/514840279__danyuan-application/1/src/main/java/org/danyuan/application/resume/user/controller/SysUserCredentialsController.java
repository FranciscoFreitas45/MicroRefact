package org.danyuan.application.resume.user.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.resume.user.po.SysUserCredentials;
import org.danyuan.application.resume.user.service.SysUserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.danyuan.application.Interface.SysUserCredentialsService;
@RestController
@RequestMapping("/sysUserCredentials")
public class SysUserCredentialsController extends BaseControllerImpl<SysUserCredentials>implements BaseController<SysUserCredentials>{

@Autowired
 private SysUserCredentialsService sysUserCredentialsService;


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    ModelAndView modelAndView = new ModelAndView("resume/user/sysusercredentialsdetail");
    SysUserCredentials info = new SysUserCredentials();
    info.setUuid(uuid);
    modelAndView.addObject("sysUserCredentials", sysUserCredentialsService.findOne(info));
    return modelAndView;
}


}