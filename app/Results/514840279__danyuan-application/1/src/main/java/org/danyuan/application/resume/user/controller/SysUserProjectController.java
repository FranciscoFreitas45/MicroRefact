package org.danyuan.application.resume.user.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.resume.user.po.SysUserProject;
import org.danyuan.application.resume.user.service.SysUserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.danyuan.application.Interface.SysUserProjectService;
@RestController
@RequestMapping("/sysUserProject")
public class SysUserProjectController extends BaseControllerImpl<SysUserProject>implements BaseController<SysUserProject>{

@Autowired
 private SysUserProjectService sysUserProjectService;


}