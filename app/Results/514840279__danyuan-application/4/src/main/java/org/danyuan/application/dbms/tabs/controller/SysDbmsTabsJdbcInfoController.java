package org.danyuan.application.dbms.tabs.controller;
 import javax.servlet.http.HttpServletRequest;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsJdbcInfo;
import org.danyuan.application.dbms.tabs.service.SysDbmsTabsJdbcInfoService;
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
@RequestMapping("/sysDbmsTabsJdbcInfo")
public class SysDbmsTabsJdbcInfoController extends BaseControllerImpl<SysDbmsTabsJdbcInfo>implements BaseController<SysDbmsTabsJdbcInfo>{

 private  Logger logger;

@Autowired
 private  SysDbmsTabsJdbcInfoService sysDbmsTabsJdbcInfoService;


@RequestMapping(path = "/addBefor", method = RequestMethod.GET)
public ModelAndView addBefor(HttpServletRequest request){
    SysDbmsTabsJdbcInfo info = new SysDbmsTabsJdbcInfo();
    if (request.getParameter("uuid") != null && !"".equals(request.getParameter("uuid"))) {
        info.setUuid(request.getParameter("uuid"));
        info = sysDbmsTabsJdbcInfoService.findOne(info);
    }
    logger.info("addBefor", SysDbmsTabsJdbcInfoController.class);
    ModelAndView view = new ModelAndView("dbms/addr/add_addr");
    view.addObject("SysDbmsTabsJdbcInfo", info);
    return view;
}


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysDbmsTabsJdbcInfoController.class);
    ModelAndView modelAndView = new ModelAndView("dbms/tabs/sysdbmstabsjdbcinfodetail");
    SysDbmsTabsJdbcInfo info = new SysDbmsTabsJdbcInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysDbmsTabsJdbcInfo", sysDbmsTabsJdbcInfoService.findOne(info));
    return modelAndView;
}


}