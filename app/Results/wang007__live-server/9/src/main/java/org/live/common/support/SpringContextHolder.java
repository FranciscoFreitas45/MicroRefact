package org.live.common.support;
 import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.util.Map;
@Component
public class SpringContextHolder implements ApplicationContextAware{

 private  ApplicationContext springContext;

private SpringContextHolder() {
}
public Map<String,T> getBeansOfType(Class<T> type){
    return springContext.getBeansOfType(type);
}


public void setApplicationContext(ApplicationContext context) throws BeansException{
    SpringContextHolder.springContext = context;
}


public ApplicationContext getSpringContext(){
    return SpringContextHolder.springContext;
}


public T getBean(Class<T> requiredType){
    return springContext.getBean(requiredType);
}


}