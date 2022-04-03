package com.easyshopping.listener;
 import java.io.File;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import com.easyshopping.service.CacheService;
import com.easyshopping.service.SearchService;
import com.easyshopping.service.StaticService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
@Component("initListener")
public class InitListener implements ApplicationListener<ContextRefreshedEvent>,ServletContextAware{

 private  String INSTALL_INIT_CONFIG_FILE_PATH;

 private  Logger logger;

 private  ServletContext servletContext;

@Value("${system.version}")
 private  String systemVersion;

@Resource(name = "staticServiceImpl")
 private  StaticService staticService;

@Resource(name = "cacheServiceImpl")
 private  CacheService cacheService;

@Resource(name = "searchServiceImpl")
 private  SearchService searchService;


public void setServletContext(ServletContext servletContext){
    this.servletContext = servletContext;
}


public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
    if (servletContext != null && contextRefreshedEvent.getApplicationContext().getParent() == null) {
        String info = "Initializing Easy Shopping " + systemVersion;
        logger.info(info);
        File installInitConfigFile = new File(servletContext.getRealPath(INSTALL_INIT_CONFIG_FILE_PATH));
        if (installInitConfigFile.exists()) {
            cacheService.clear();
            staticService.buildAll();
            searchService.purge();
            searchService.index();
            installInitConfigFile.delete();
        } else {
            staticService.buildIndex();
            staticService.buildOther();
        }
    }
}


}