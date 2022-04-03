package org.danyuan.application.softm.organization.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.softm.organization.po.SysOrganizationInfo;
import org.danyuan.application.softm.organization.service.SysOrganizationInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/sysOrganization")
public class SysOrganizationController extends BaseControllerImpl<SysOrganizationInfo>implements BaseController<SysOrganizationInfo>{

 private  Logger logger;

@Autowired
 private  SysOrganizationInfoService sysOrganizationInfoService;


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysOrganizationController.class);
    ModelAndView modelAndView = new ModelAndView("softm/organization/sysorganizationinfodetail");
    SysOrganizationInfo info = new SysOrganizationInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysOrganizationInfo", sysOrganizationInfoService.findOne(info));
    return modelAndView;
}


}