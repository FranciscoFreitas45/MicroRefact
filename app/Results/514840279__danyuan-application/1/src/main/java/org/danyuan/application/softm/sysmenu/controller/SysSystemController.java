package org.danyuan.application.softm.sysmenu.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.softm.sysmenu.po.SysSystemInfo;
import org.danyuan.application.softm.sysmenu.service.SysSystemInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.danyuan.application.Interface.SysSystemInfoService;
@RestController
@RequestMapping("/sysSystem")
public class SysSystemController extends BaseControllerImpl<SysSystemInfo>implements BaseController<SysSystemInfo>{

 private  Logger logger;

@Autowired
 private  SysSystemInfoService sysSystemInfoService;


}