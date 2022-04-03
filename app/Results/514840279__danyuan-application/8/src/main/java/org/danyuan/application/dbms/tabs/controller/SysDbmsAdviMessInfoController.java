package org.danyuan.application.dbms.tabs.controller;
 import java.util.List;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.dbms.tabs.po.SysDbmsAdviMessInfo;
import org.danyuan.application.dbms.tabs.service.SysDbmsAdviMessInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/sysAdviceMess")
public class SysDbmsAdviMessInfoController extends BaseControllerImpl<SysDbmsAdviMessInfo>implements BaseController<SysDbmsAdviMessInfo>{

 private  Logger logger;

@Autowired
 private SysDbmsAdviMessInfoService sysDbmsAdviMessInfoService;


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysDbmsAdviMessInfoController.class);
    ModelAndView modelAndView = new ModelAndView("dbms/tabs/sysdbmsadvimessinfodetail");
    SysDbmsAdviMessInfo info = new SysDbmsAdviMessInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysDbmsAdviMessInfo", sysDbmsAdviMessInfoService.findOne(info));
    return modelAndView;
}


@RequestMapping(path = "/findAll", method = { RequestMethod.GET, RequestMethod.POST })
public List<SysDbmsAdviMessInfo> findAll() throws ClassNotFoundException{
    return sysDbmsAdviMessInfoService.findAll(new SysDbmsAdviMessInfo());
}


}