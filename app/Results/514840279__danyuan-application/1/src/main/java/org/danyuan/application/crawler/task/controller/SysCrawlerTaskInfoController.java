package org.danyuan.application.crawler.task.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.crawler.task.po.SysCrawlerTaskInfo;
import org.danyuan.application.crawler.task.service.SysCrawlerTaskInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.danyuan.application.Interface.SysCrawlerTaskInfoService;
@RestController
@RequestMapping("/sysCrawlerTaskInfo")
public class SysCrawlerTaskInfoController extends BaseControllerImpl<SysCrawlerTaskInfo>implements BaseController<SysCrawlerTaskInfo>{

 private  Logger logger;

@Autowired
 private SysCrawlerTaskInfoService sysCrawlerTaskInfoService;


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysCrawlerTaskInfo.class);
    ModelAndView modelAndView = new ModelAndView("crawler/task/syscrawlertaskinfodetail");
    SysCrawlerTaskInfo info = new SysCrawlerTaskInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysCrawlerTaskInfo", sysCrawlerTaskInfoService.findOne(info));
    return modelAndView;
}


@RequestMapping(path = "/config/{uuid}", method = RequestMethod.GET)
public ModelAndView config(String uuid){
    logger.info("config", SysCrawlerTaskInfoController.class);
    ModelAndView view = new ModelAndView("crawler/task/config");
    SysCrawlerTaskInfo task = new SysCrawlerTaskInfo();
    task.setUuid(uuid);
    task = sysCrawlerTaskInfoService.findOne(task);
    view.addObject("task", task);
    return view;
}


}