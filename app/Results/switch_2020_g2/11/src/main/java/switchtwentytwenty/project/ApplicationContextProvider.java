package switchtwentytwenty.project;
 import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import switchtwentytwenty.project.domain.constant.Constants;
@Component
public class ApplicationContextProvider implements ApplicationContextAware{

 private  ApplicationContext applicationContext;


@Override
public void setApplicationContext(ApplicationContext applicationContext){
    this.applicationContext = new FileSystemXmlApplicationContext(Constants.URL_EXTERNAL_CATEGORIES_CONFIGURATION);
}


public ApplicationContext getApplicationContext(){
    return applicationContext;
}


}