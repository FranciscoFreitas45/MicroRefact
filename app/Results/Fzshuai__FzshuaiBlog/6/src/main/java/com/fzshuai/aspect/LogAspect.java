package com.fzshuai.aspect;
 import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
@Aspect
@Component
public class LogAspect {

 private  Logger logger;

 private  String url;

 private  String ip;

 private  String classMethod;

 private  Object[] args;


@Before("log()")
public void doBefore(JoinPoint joinPoint){
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    String url = request.getRequestURL().toString();
    String ip = request.getRemoteAddr();
    String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
    Object[] args = joinPoint.getArgs();
    RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
    logger.info("Request : {}", requestLog);
}


@Pointcut("execution(* com.fzshuai.controller.*.*(..))")
public void log(){
}


@Override
public String toString(){
    return "{" + "url='" + url + '\'' + ", ip='" + ip + '\'' + ", classMethod='" + classMethod + '\'' + ", args=" + Arrays.toString(args) + '}';
}


@After("log()")
public void doAfter(){
// logger.info("--------doAfter--------");
}


@AfterReturning(returning = "result", pointcut = "log()")
public void doAfterRuturn(Object result){
    logger.info("Result : {}", result);
}


}