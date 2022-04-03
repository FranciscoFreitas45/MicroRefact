package org.danyuan.application.dbms.tabs.controller;
 import java.util.List;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.dbms.tabs.po.SysDbmsUserIndexInfo;
import org.danyuan.application.dbms.tabs.service.SysDbmsUserIndexInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/sysDbmsUserIndexInfo")
public class SysDbmsUserIndexInfoController extends BaseControllerImpl<SysDbmsUserIndexInfo>implements BaseController<SysDbmsUserIndexInfo>{

 private  Logger logger;

@Autowired
 private SysDbmsUserIndexInfoService sysDbmsUserIndexInfoService;


@ApiOperation(value = "查询图表信息", notes = "")
@RequestMapping(path = "/chartList", method = RequestMethod.POST)
public List<SysDbmsUserIndexInfo> chartList(){
    logger.info("findAll", SysDbmsUserIndexInfoController.class);
    return sysDbmsUserIndexInfoService.chartList();
}


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysDbmsUserIndexInfoController.class);
    ModelAndView modelAndView = new ModelAndView("dbms/tabs/sysdbmsuserindexinfodetail");
    SysDbmsUserIndexInfo info = new SysDbmsUserIndexInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysDbmsUserIndexInfo", sysDbmsUserIndexInfoService.findOne(info));
    return modelAndView;
}


}