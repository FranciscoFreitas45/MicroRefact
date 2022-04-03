package org.jeecgframework.web.system.controller.core;
 import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/dataSourceController")
public class DataSourceController extends BaseController{

 private  Logger logger;


@RequestMapping(params = "goDruid")
public ModelAndView goDruid(){
    return new ModelAndView("/system/druid/index");
}


}