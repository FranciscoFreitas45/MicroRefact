package org.danyuan.application.crawler.config.controller;
 import java.io.IOException;
import org.apache.http.ParseException;
import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.crawler.config.service.SysRuleInfoService;
import org.danyuan.application.crawler.task.po.SysCrawlerTaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/crawler")
public class CrawlerController {

@Autowired
 private SysRuleInfoService sysRuleInfoService;


@RequestMapping("/startTask")
public String startTask(Pagination<SysCrawlerTaskInfo> vo) throws ParseException{
    return sysRuleInfoService.startTask(vo.getList(), 1);
}


@RequestMapping("/stopTask")
public String stopTask(Pagination<SysCrawlerTaskInfo> vo) throws ParseException{
    return sysRuleInfoService.startTask(vo.getList(), 0);
}


@RequestMapping("/ruler/pageDict/{uuid}")
public ModelAndView pageDict(String uuid){
    ModelAndView view = new ModelAndView("crawler/rule/pageDict");
    view.addObject("uuid", uuid);
    return view;
}


@RequestMapping(path = { "/ruler/pageList/{uuid}" })
public ModelAndView pageList(String uuid){
    ModelAndView view = new ModelAndView("crawler/rule/pageList");
    view.addObject("uuid", uuid);
    return view;
}


@RequestMapping("/ruler/pageDetail/{uuid}")
public ModelAndView pageDetail(String uuid){
    ModelAndView view = new ModelAndView("crawler/rule/pageDetail");
    view.addObject("uuid", uuid);
    return view;
}


}