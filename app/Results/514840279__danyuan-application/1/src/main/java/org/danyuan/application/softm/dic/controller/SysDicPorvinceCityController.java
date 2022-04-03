package org.danyuan.application.softm.dic.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.softm.dic.po.SysDicPorvinceCity;
import org.danyuan.application.softm.dic.service.SysDicPorvinceCityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.danyuan.application.Interface.SysDicPorvinceCityService;
@RestController
@RequestMapping("/sysDicPorvinceCity")
public class SysDicPorvinceCityController extends BaseControllerImpl<SysDicPorvinceCity>implements BaseController<SysDicPorvinceCity>{

 private  Logger logger;

@Autowired
 private SysDicPorvinceCityService sysDicPorvinceCityService;


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysDicPorvinceCity.class);
    ModelAndView modelAndView = new ModelAndView("softm/dic/sysdicporvincecitydetail");
    SysDicPorvinceCity info = new SysDicPorvinceCity();
    info.setUuid(uuid);
    modelAndView.addObject("sysDicPorvinceCity", sysDicPorvinceCityService.findOne(info));
    return modelAndView;
}


}