package org.danyuan.application.resume.user.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.resume.user.po.SysUserWorkExpreience;
import org.danyuan.application.resume.user.service.SysUserWorkExpreienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.danyuan.application.Interface.SysUserWorkExpreienceService;
@RestController
@RequestMapping("/sysUserWorkExpreience")
public class SysUserWorkExpreienceController extends BaseControllerImpl<SysUserWorkExpreience>implements BaseController<SysUserWorkExpreience>{

@Autowired
 private SysUserWorkExpreienceService sysUserWorkExpreienceService;


}