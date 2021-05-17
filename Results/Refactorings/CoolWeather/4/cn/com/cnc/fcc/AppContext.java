import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Component
@Lazy(false)
public class AppContext implements ApplicationContextAware{

 private  ApplicationContext applicationContext;


@Override
public void setApplicationContext(ApplicationContext applicationContext){
    // TODO Auto-generated method stub
    AppContext.applicationContext = applicationContext;
}


public ApplicationContext getApplicationContext(){
    return applicationContext;
}


public T getBean(Class<T> clz){
    return (T) applicationContext.getBean(clz);
}


}