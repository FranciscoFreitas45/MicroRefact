package org.danyuan.application.crawler.task.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.crawler.task.po.SysCrawlerTaskSelfCodeInfo;
import org.danyuan.application.crawler.task.service.SysCrawlerTaskSelfCodeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.danyuan.application.Interface.SysCrawlerTaskSelfCodeInfoService;
@RestController
@RequestMapping("/sysCrawlerTaskSelfCodeInfo")
public class SysCrawlerTaskSelfCodeInfoController extends BaseControllerImpl<SysCrawlerTaskSelfCodeInfo>implements BaseController<SysCrawlerTaskSelfCodeInfo>{

 private  Logger logger;

@Autowired
 private SysCrawlerTaskSelfCodeInfoService sysCrawlerTaskSelfCodeInfoService;


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysCrawlerTaskSelfCodeInfoController.class);
    ModelAndView modelAndView = new ModelAndView("crawler/task/syscrawlertaskselfcodeinfodetail");
    SysCrawlerTaskSelfCodeInfo info = new SysCrawlerTaskSelfCodeInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysCrawlerTaskSelfCodeInfo", sysCrawlerTaskSelfCodeInfoService.findOne(info));
    return modelAndView;
}


}