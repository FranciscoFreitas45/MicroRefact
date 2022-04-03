package com.lingxiang2014.listener;
 import java.io.File;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import com.lingxiang2014.service.CacheService;
import com.lingxiang2014.service.MemberRankService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.service.StaticBonudsRankService;
import com.lingxiang2014.service.StaticService;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.MemberRankService;
import com.lingxiang2014.Interface.StaticBonudsRankService;
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

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "memberRankServiceImpl")
 private  MemberRankService memberRankService;

@Resource(name = "staticBonudsRankServiceImpl")
 private  StaticBonudsRankService staticBonudsRankService;


public void setServletContext(ServletContext servletContext){
    this.servletContext = servletContext;
}


public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
    if (servletContext != null && contextRefreshedEvent.getApplicationContext().getParent() == null) {
        String info = "I|n|i|t|i|a|l|i|z|i|n|g| |S|H|O|P|+|+| |" + systemVersion;
        logger.info(info.replace("|", ""));
        File installInitConfigFile = new File(servletContext.getRealPath(INSTALL_INIT_CONFIG_FILE_PATH));
        if (installInitConfigFile.exists()) {
            cacheService.clear();
            staticService.buildAll();
            installInitConfigFile.delete();
        } else {
            staticService.buildIndex();
            staticService.buildOther();
        }
    }
}


}