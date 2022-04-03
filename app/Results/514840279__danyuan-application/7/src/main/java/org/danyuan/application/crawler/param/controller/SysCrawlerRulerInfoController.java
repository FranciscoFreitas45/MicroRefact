package org.danyuan.application.crawler.param.controller;
 import java.io.IOException;
import org.apache.http.ParseException;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.crawler.param.po.SysCrawlerRulerInfo;
import org.danyuan.application.crawler.param.service.SysCrawlerRulerInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.danyuan.application.DTO.Pagination;
@RestController
@RequestMapping("/sysCrawlerRulerInfo")
public class SysCrawlerRulerInfoController extends BaseControllerImpl<SysCrawlerRulerInfo>implements BaseController<SysCrawlerRulerInfo>{

 private  Logger logger;

@Autowired
 private SysCrawlerRulerInfoService sysCrawlerRulerInfoService;


@RequestMapping("/stop")
public String stop(Pagination<SysCrawlerRulerInfo> vo){
    return sysCrawlerRulerInfoService.stop(vo.getList());
}


@RequestMapping("/start")
public String start(Pagination<SysCrawlerRulerInfo> vo) throws ParseException{
    return sysCrawlerRulerInfoService.start(vo.getList());
}


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    ModelAndView modelAndView = new ModelAndView("crawler/param/syscrawlerrulerinfodetail");
    SysCrawlerRulerInfo info = new SysCrawlerRulerInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysCrawlerRulerInfo", sysCrawlerRulerInfoService.findOne(info));
    return modelAndView;
}


@RequestMapping(path = "/index/{uuid}", method = RequestMethod.GET)
public ModelAndView config(String uuid){
    logger.info("index", SysCrawlerRulerInfoController.class);
    ModelAndView view = new ModelAndView("crawler/ruler/index");
    view.addObject("taskUuid", uuid);
    return view;
}


}