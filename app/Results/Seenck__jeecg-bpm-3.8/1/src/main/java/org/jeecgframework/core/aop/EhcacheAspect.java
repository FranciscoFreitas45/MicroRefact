package org.jeecgframework.core.aop;
 import java.io.Serializable;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jeecgframework.core.annotation.Ehcache;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.service.CacheServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
public class EhcacheAspect {

 private  Logger logger;

 private  Cache SYSTEM_BASE_CACHE;

 private  Cache SYS_AUTH_CACHE;


public String getCacheKey(String targetName,String methodName,Object[] arguments){
    StringBuffer sb = new StringBuffer();
    sb.append(targetName).append(".").append(methodName);
    if ((arguments != null && arguments.length != 0)) {
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i] instanceof String[]) {
                String[] strArray = (String[]) arguments[i];
                sb.append(".");
                for (String str : strArray) {
                    sb.append(str);
                }
            } else {
                sb.append(".").append(arguments[i]);
            }
        }
    }
    return sb.toString();
}


@Around("simplePointcut() && @annotation(ehcache)")
public Object aroundLogCalls(ProceedingJoinPoint joinPoint,Ehcache ehcache){
    String targetName = joinPoint.getTarget().getClass().getName();
    String methodName = joinPoint.getSignature().getName();
    Object[] arguments = joinPoint.getArgs();
    logger.debug("-------ehcache[aspect]-----------targetclass:[{}] , methodName:[{}] , arguments:[{}] ", new Object[] { targetName, methodName, arguments });
    Cache cache = null;
    Object result;
    String cacheKey = getCacheKey(targetName, methodName, arguments);
    Element element = null;
    if (oConvertUtils.isNotEmpty(ehcache.cacheName()) && CacheServiceI.SYS_AUTH_CACHE.equals(ehcache.cacheName())) {
        // 自定义缓存对象
        cache = this.SYS_AUTH_CACHE;
    } else {
        // 默认缓存对象
        cache = this.SYSTEM_BASE_CACHE;
    }
    // 获取缓存
    element = cache.get(cacheKey);
    if (element == null) {
        if ((arguments != null && arguments.length != 0)) {
            result = joinPoint.proceed(arguments);
        } else {
            result = joinPoint.proceed();
        }
        element = new Element(cacheKey, (Serializable) result);
        // 设置缓存
        cache.put(element);
        logger.debug("-------ehcache[aspect]----------设置缓存成功---");
    }
    return element.getValue();
}


@Pointcut("@annotation(org.jeecgframework.core.annotation.Ehcache)")
public void simplePointcut(){
}


}