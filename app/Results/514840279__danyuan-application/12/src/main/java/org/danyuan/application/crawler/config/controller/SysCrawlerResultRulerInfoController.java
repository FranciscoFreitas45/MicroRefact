package org.danyuan.application.crawler.config.controller;
 import java.util.List;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.crawler.config.po.SysCrawlerResultRulerInfo;
import org.danyuan.application.crawler.config.service.SysCrawlerResultRulerInfoService;
import org.danyuan.application.crawler.config.vo.SysCrawlerResultRulerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/sysCrawlerResultRulerInfo")
public class SysCrawlerResultRulerInfoController extends BaseControllerImpl<SysCrawlerResultRulerInfo>implements BaseController<SysCrawlerResultRulerInfo>{

@Autowired
 private SysCrawlerResultRulerInfoService sysCrawlerResultRulerInfoService;


@RequestMapping("/saveResultRulerInfo")
public List<SysCrawlerResultRulerInfo> saveResultRulerInfo(SysCrawlerResultRulerVo vo){
    return sysCrawlerResultRulerInfoService.saveResultRulerInfo(vo);
}


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    ModelAndView modelAndView = new ModelAndView("crawler/config/syscrawlerresultrulerinfodetail");
    SysCrawlerResultRulerInfo info = new SysCrawlerResultRulerInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysCrawlerResultRulerInfo", sysCrawlerResultRulerInfoService.findOne(info));
    return modelAndView;
}


}