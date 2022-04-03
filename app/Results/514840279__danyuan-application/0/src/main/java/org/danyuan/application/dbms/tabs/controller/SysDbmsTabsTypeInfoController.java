package org.danyuan.application.dbms.tabs.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsTypeInfo;
import org.danyuan.application.dbms.tabs.service.SysDbmsTabsTypeInfoService;
import org.danyuan.application.dbms.tabs.vo.SysDbmsTabsTypeInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/sysDbmsTabsTypeInfo")
public class SysDbmsTabsTypeInfoController extends BaseControllerImpl<SysDbmsTabsTypeInfo>implements BaseController<SysDbmsTabsTypeInfo>{

 private  Logger logger;

@Autowired
 private  SysDbmsTabsTypeInfoService sysDbmsTabsTypeInfoService;


@RequestMapping(path = "/sysTableTypeDeleteAll", method = RequestMethod.POST)
@ResponseBody
public String sysTableTypeDeleteAll(SysDbmsTabsTypeInfoVo vo){
    logger.info("sysTableTypeDeleteAll", SysDbmsTabsTypeInfoController.class);
    try {
        sysDbmsTabsTypeInfoService.deleteAll(vo.getList());
        return "1";
    } catch (Exception e) {
        return "0";
    }
}


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysDbmsTabsTypeInfo.class);
    ModelAndView modelAndView = new ModelAndView("dbms/tabs/sysdbmstabstypeinfodetail");
    SysDbmsTabsTypeInfo info = new SysDbmsTabsTypeInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysDbmsTabsTypeInfo", sysDbmsTabsTypeInfoService.findOne(info));
    return modelAndView;
}


}