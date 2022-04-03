package org.danyuan.application.crawler.task.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.crawler.task.po.SysCrawlerTaskErrInfo;
import org.danyuan.application.crawler.task.service.SysCrawlerTaskErrInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.danyuan.application.Interface.SysCrawlerTaskErrInfoService;
@RestController
@RequestMapping("/sysCrawlerTaskErrInfo")
public class SysCrawlerTaskErrInfoController extends BaseControllerImpl<SysCrawlerTaskErrInfo>implements BaseController<SysCrawlerTaskErrInfo>{

 private  Logger logger;

@Autowired
 private SysCrawlerTaskErrInfoService sysCrawlerTaskErrInfoService;


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysCrawlerTaskErrInfoController.class);
    ModelAndView modelAndView = new ModelAndView("crawler/task/syscrawlertaskerrinfodetail");
    SysCrawlerTaskErrInfo info = new SysCrawlerTaskErrInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysCrawlerTaskErrInfo", sysCrawlerTaskErrInfoService.findOne(info));
    return modelAndView;
}


}