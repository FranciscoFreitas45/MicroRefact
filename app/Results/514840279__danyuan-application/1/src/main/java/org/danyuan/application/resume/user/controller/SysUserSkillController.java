package org.danyuan.application.resume.user.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.resume.user.po.SysUserSkill;
import org.danyuan.application.resume.user.service.SysUserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.danyuan.application.Interface.SysUserSkillService;
@RestController
@RequestMapping("/sysUserSkill")
public class SysUserSkillController extends BaseControllerImpl<SysUserSkill>implements BaseController<SysUserSkill>{

@Autowired
 private SysUserSkillService sysUserSkillService;


}