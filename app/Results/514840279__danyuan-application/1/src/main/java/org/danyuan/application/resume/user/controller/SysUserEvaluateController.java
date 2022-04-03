package org.danyuan.application.resume.user.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.resume.user.po.SysUserEvaluate;
import org.danyuan.application.resume.user.service.SysUserEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.danyuan.application.Interface.SysUserEvaluateService;
@RestController
@RequestMapping("/sysUserEvaluate")
public class SysUserEvaluateController extends BaseControllerImpl<SysUserEvaluate>implements BaseController<SysUserEvaluate>{

@Autowired
 private SysUserEvaluateService sysUserEvaluateService;


}