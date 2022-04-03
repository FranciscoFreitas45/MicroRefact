package com.kingen.aop;
 import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.kingen.bean.Log;
import com.kingen.bean.User;
import com.kingen.service.log.LogService;
import com.kingen.util.Constants;
import com.kingen.util.DateUtils;
import com.kingen.util.SpringContextHolder;
import com.kingen.util.StringUtils;
import com.kingen.util.UserUtils;
@Aspect
@Component
public class LogAOP {

 private  LogService logService;

 private  Logger logger;


public String getAnnotationValue(ServiceLogAnnotation anno){
    String action = anno.action();
    Assert.hasText(action, "操作名字不应为空");
    return action;
}


@Transactional
public void saveLog(HttpServletRequest request,String title,Exception ex){
    User user = UserUtils.getCurrentUser();
    if (user != null && user.getUserId() != null) {
        Log log = new Log();
        log.setCreateDate(DateUtils.getDateTime());
        log.setTitle(title);
        log.setType(ex == null ? Log.TYPE_ACCESS : Log.TYPE_EXCEPTION);
        log.setRemoteAddr(StringUtils.getRemoteAddr(request));
        log.setUserAgent(user.getUsername());
        // log.setUserAgent(request.getHeader("user-agent"));
        log.setRequestUri(request.getRequestURI());
        log.setParams(request.getParameterMap());
        // 如果有异常，设置异常信息
        log.setException(ex == null ? null : ex.getMessage());
        // log.setException(ex == null ? null : Exceptions.getStackTraceAsString(ex));
        // log.setStatus(ex == null ? Constants.StatusEnum.Success.getIndex() : Constants.StatusEnum.Fail.getIndex());
        // log.setMethod(request.getMethod());
        // 异步保存日志
        // new SaveLogThread(log, handler, ex).start();
        logService.saveLog(log);
    }
}


@Pointcut("@annotation(com.kingen.aop.ServiceLogAnnotation)")
public void servicelogAspect(){
}


@Pointcut("@annotation(com.kingen.aop.ControllerLogAnnotation)")
public void controllerlogAspect(){
}


@AfterThrowing(value = "servicelogAspect() && @annotation(annotation) &&args(..) ", throwing = "e")
public void doAfterThrowing(JoinPoint joinPoint,ServiceLogAnnotation annotation,Exception e){
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    try {
        String title = getAnnotationValue(annotation);
        saveLog(request, title, e);
    } catch (Exception ex) {
        // 记录本地异常日志
        logger.error("==异常通知异常==");
        logger.error("异常信息:{}", ex.getMessage());
    }
}


@After(value = "controllerlogAspect() && @annotation(annotation) &&args(object,..) ", argNames = "")
public void doAfter(JoinPoint joinPoint,ControllerLogAnnotation annotation,Object object){
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    try {
        // String title = getAnnotationValue(joinPoint);
        String title = getAnnotationValue(annotation);
        saveLog(request, title);
    } catch (Exception e) {
        e.printStackTrace();
        // 记录本地异常日志
        logger.error("==异常通知异常==");
        logger.error("异常信息:{}", e.getMessage());
    }
}


}