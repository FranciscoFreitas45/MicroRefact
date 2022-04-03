package com.circle.utils;
 import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import com.xwtec.xwserver.constant.ConstantBusiKeys.ResultInfoKey;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.util.CommonUtil;
public class SystemExceptionUtil {


public void logSPTException(ModelAndView mav,Logger logger,SPTException e){
    mav.addObject(ResultInfoKey.MSG, "页面跳转失败，请联系管理员！");
    mav.addObject(ResultInfoKey.URL, "/login/login.action");
    mav.addObject(ResultInfoKey.ERROR, e);
    mav.setViewName(ResultInfoKey.RESULT_INFO_PATH);
    logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
}


}