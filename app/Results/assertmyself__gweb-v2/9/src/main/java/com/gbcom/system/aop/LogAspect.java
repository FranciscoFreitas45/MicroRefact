package com.gbcom.system.aop;
 import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.gbcom.system.domain.SysLog;
import com.hc.core.utils.DateTimeHelper;
@Aspect
@Component
public class LogAspect extends BaseLogAspect{

 private  String EDP;


@Override
public void doBefore(JoinPoint joinPoint){
    logger.info("doBefore");
}


@Pointcut("@annotation(com.gbcom.system.aop.UserLog)")
public void controllerAspect(){
}


@Override
public void doAfter(JoinPoint joinPoint){
    logger.info("doAfter");
}


@Around("controllerAspect()")
public Object around(ProceedingJoinPoint joinPoint){
    // NOPMD
    Object obj = null;
    UserLog userLog = null;
    SysLog sysLog = new SysLog();
    // 获取连接点的方法签名对象
    try {
        MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
        // 连接点对象的方法
        Method method = joinPointObject.getMethod();
        if (method.isAnnotationPresent(UserLog.class)) {
            userLog = method.getAnnotation(UserLog.class);
            super.buildClassMethod(joinPoint, sysLog);
            super.buildUserLog(userLog, sysLog);
            super.buildSession(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(), sysLog);
        }
    } catch (Exception e) {
        logger.info("building logItem error", e);
    }
    try {
        sysLog.setEnterTime(DateTimeHelper.getTimestamp());
        obj = joinPoint.proceed(joinPoint.getArgs());
        sysLog.setOutTime(DateTimeHelper.getTimestamp());
    } catch (Throwable e) {
        if (sysLog != null) {
            sysLog.setResult("failed");
        }
        throw e;
    } finally {
        try {
            if (userLog.include()) {
                super.log(sysLog);
            }
        } catch (Exception e) {
            logger.info("record log  failed!! the exception not throws", e);
        }
    }
    return obj;
}


}