package org.danyuan.application.softm.organization.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.softm.organization.po.SysRolesTabsInfo;
import org.danyuan.application.softm.organization.service.SysRolesTabsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.danyuan.application.Interface.SysRolesTabsInfoService;
@RestController
@RequestMapping("/sysRolesTabsInfo")
public class SysRolesTabsInfoController extends BaseControllerImpl<SysRolesTabsInfo>implements BaseController<SysRolesTabsInfo>{

@Autowired
 private SysRolesTabsInfoService sysRolesTabsInfoService;


}