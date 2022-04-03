package com.ec.survey.tools;
 import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.ec.survey.service.LdapDBService;
import com.ec.survey.service.LdapService;
@Service("domainWorker")
@Scope("singleton")
public class DomainUpdater implements Runnable{

 protected  Logger logger;

@Resource(name = "ldapDBService")
 private  LdapDBService ldapDBService;

@Resource(name = "ldapService")
 private  LdapService ldapService;


public void reloadDomains(Map<String,String> allDomains){
    ldapDBService.reloadDomains(allDomains);
}


@Override
public void run(){
    try {
        logger.info("DomainUpdater started");
        Map<String, String> allDomains = ldapService.getAllDomains();
        logger.info("DomainUpdater get all Domains " + allDomains.size());
        reloadDomains(allDomains);
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
    }
    logger.info("DomainUpdater completed");
}


}