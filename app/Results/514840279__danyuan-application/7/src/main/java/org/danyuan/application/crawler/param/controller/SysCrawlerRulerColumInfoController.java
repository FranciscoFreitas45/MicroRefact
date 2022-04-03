package org.danyuan.application.crawler.param.controller;
 import java.util.List;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.crawler.param.po.SysCrawlerRulerColumInfo;
import org.danyuan.application.crawler.param.po.SysCrawlerRulerInfo;
import org.danyuan.application.crawler.param.service.SysCrawlerRulerColumInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/sysCrawlerRulerColumInfo")
public class SysCrawlerRulerColumInfoController extends BaseControllerImpl<SysCrawlerRulerColumInfo>implements BaseController<SysCrawlerRulerColumInfo>{

 private  Logger logger;

@Autowired
 private SysCrawlerRulerColumInfoService sysCrawlerRulerColumInfoService;


@RequestMapping("/findParent")
public List<SysCrawlerRulerColumInfo> findParent(SysCrawlerRulerInfo info){
    logger.error("/findParent", SysCrawlerRulerColumInfoController.class);
    return sysCrawlerRulerColumInfoService.findParent(info);
}


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    ModelAndView modelAndView = new ModelAndView("crawler/param/syscrawlerrulercoluminfodetail");
    SysCrawlerRulerColumInfo info = new SysCrawlerRulerColumInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysCrawlerRulerColumInfo", sysCrawlerRulerColumInfoService.findOne(info));
    return modelAndView;
}


}