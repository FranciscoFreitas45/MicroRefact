package com.gbcom.system.aop;
 import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class AbstractAspect {

 protected  Logger logger;


public void doBefore(JoinPoint joinPoint)


public void doAfter(JoinPoint joinPoint)


public Object around(ProceedingJoinPoint joinPoint)


}