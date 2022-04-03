package org.danyuan.application.softm.dic.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.softm.dic.po.SysDicKeyList;
import org.danyuan.application.softm.dic.service.SysDicKeyListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
@RestController
@RequestMapping("/sysDicKeyList")
@Api(value = "/SysDicKeyList", description = "字典数据")
public class SysDicKeyListController extends BaseControllerImpl<SysDicKeyList>implements BaseController<SysDicKeyList>{

 private  Logger logger;

@Autowired
 private SysDicKeyListService sysDicKeyListService;


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysDicKeyListController.class);
    ModelAndView modelAndView = new ModelAndView("softm/dic/sysdickeylistdetail");
    SysDicKeyList info = new SysDicKeyList();
    info.setUuid(uuid);
    modelAndView.addObject("sysDicKeyList", sysDicKeyListService.findOne(info));
    return modelAndView;
}


}