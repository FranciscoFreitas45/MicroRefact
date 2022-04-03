package org.jeecgframework.core.common.exception;
 import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver{

@Autowired
 private  SystemService systemService;

 private  Logger log;

 private  int WIRTE_DB_MAX_LENGTH;

 private  short LOG_LEVEL;

 private  short LOG_OPT;


public String getThrowableMessage(Throwable ex){
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    ex.printStackTrace(pw);
    return sw.toString();
}


public ModelAndView processAjax(HttpServletRequest request,HttpServletResponse response,Object handler,Throwable deepestException){
    ModelAndView empty = new ModelAndView();
    // response.setContentType("application/json");
    response.setHeader("Cache-Control", "no-store");
    AjaxJson json = new AjaxJson();
    json.setSuccess(true);
    json.setMsg(deepestException.getMessage());
    PrintWriter pw = null;
    try {
        pw = response.getWriter();
        pw.write(JSONHelper.bean2json(json));
        pw.flush();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            pw.close();
        } catch (Exception e2) {
        }
    }
    empty.clear();
    return empty;
}


public void logDb(Throwable ex){
    // String exceptionMessage = getThrowableMessage(ex);
    String exceptionMessage = "错误异常: " + ex.getClass().getSimpleName() + ",错误描述：" + ex.getMessage();
    if (oConvertUtils.isNotEmpty(exceptionMessage)) {
        if (exceptionMessage.length() > WIRTE_DB_MAX_LENGTH) {
            exceptionMessage = exceptionMessage.substring(0, WIRTE_DB_MAX_LENGTH);
        }
    }
    systemService.addLog(exceptionMessage, LOG_OPT, LOG_LEVEL);
}


public boolean isAjax(HttpServletRequest request,HttpServletResponse response){
    return oConvertUtils.isNotEmpty(request.getHeader("X-Requested-With"));
}


public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
    boolean isajax = isAjax(request, response);
    Throwable deepestException = deepestException(ex);
    return processException(request, response, handler, deepestException, isajax);
}


public ModelAndView processNotAjax(HttpServletRequest request,HttpServletResponse response,Object handler,Throwable ex){
    String exceptionMessage = getThrowableMessage(ex);
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("exceptionMessage", exceptionMessage);
    model.put("ex", ex);
    return new ModelAndView("common/error", model);
}


public ModelAndView processException(HttpServletRequest request,HttpServletResponse response,Object handler,Throwable ex,boolean isajax){
    // 步骤一、异常信息记录到日志文件中.
    log.error("全局处理异常捕获:", ex);
    // 步骤二、异常信息记录截取前50字符写入数据库中.
    logDb(ex);
    // 步骤三、分普通请求和ajax请求分别处理.
    if (isajax) {
        return processAjax(request, response, handler, ex);
    } else {
        return processNotAjax(request, response, handler, ex);
    }
}


public Throwable deepestException(Throwable e){
    Throwable tmp = e;
    int breakPoint = 0;
    while (tmp.getCause() != null) {
        if (tmp.equals(tmp.getCause())) {
            break;
        }
        tmp = tmp.getCause();
        breakPoint++;
        if (breakPoint > 1000) {
            break;
        }
    }
    return tmp;
}


}