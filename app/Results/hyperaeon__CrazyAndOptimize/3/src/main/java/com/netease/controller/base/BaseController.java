package com.netease.controller.base;
 import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
public class BaseController {

 protected  Logger logger;

 private  long serialVersionUID;


public void logBefore(Logger logger,String interfaceName){
    logger.info("");
    logger.info("start");
    logger.info(interfaceName);
}


public void logAfter(Logger logger){
    logger.info("end");
    logger.info("");
}


public HttpServletRequest getRequest(){
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    return request;
}


public ModelAndView getModelAndView(){
    return new ModelAndView();
}


}