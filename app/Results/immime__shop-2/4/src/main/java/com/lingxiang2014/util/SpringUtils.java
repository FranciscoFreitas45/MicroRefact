package com.lingxiang2014.util;
 import java.util.Locale;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;
@Component("springUtils")
@Lazy(false)
public class SpringUtils implements ApplicationContextAware,DisposableBean{

 private  ApplicationContext applicationContext;

private SpringUtils() {
}
public void setApplicationContext(ApplicationContext applicationContext){
    SpringUtils.applicationContext = applicationContext;
}


public ApplicationContext getApplicationContext(){
    return applicationContext;
}


public void destroy(){
    applicationContext = null;
}


public String getMessage(String code,Object args){
    LocaleResolver localeResolver = getBean("localeResolver", LocaleResolver.class);
    Locale locale = localeResolver.resolveLocale(null);
    return applicationContext.getMessage(code, args, locale);
}


public T getBean(String name,Class<T> type){
    Assert.hasText(name);
    Assert.notNull(type);
    return applicationContext.getBean(name, type);
}


}