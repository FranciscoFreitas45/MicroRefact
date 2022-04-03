package org.danyuan.application.resume.user.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.resume.user.po.SysUserEducation;
import org.danyuan.application.resume.user.service.SysUserEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.danyuan.application.Interface.SysUserEducationService;
@RestController
@RequestMapping("/sysUserEducation")
public class SysUserEducationController extends BaseControllerImpl<SysUserEducation>implements BaseController<SysUserEducation>{

@Autowired
 private SysUserEducationService sysUserEducationService;


}