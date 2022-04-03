package com.ec.survey.tools;
 import java.util.Set;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.ec.survey.model.DepartmentItem;
import com.ec.survey.service.LdapDBService;
import com.ec.survey.service.LdapService;
@Service("departmentWorker")
@Scope("singleton")
public class DepartmentUpdater implements Runnable{

 protected  Logger logger;

@Resource(name = "ldapDBService")
 private  LdapDBService ldapDBService;

@Resource(name = "ldapService")
 private  LdapService ldapService;


public void reloadDepartments(Set<DepartmentItem> departments){
    ldapDBService.reload(departments);
}


@Override
public void run(){
    try {
        logger.info("DepartmentUpdater started");
        Set<DepartmentItem> departments = ldapService.getAllDepartments();
        logger.info("DepartmentUpdater: departments loaded");
        reloadDepartments(departments);
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
    }
    logger.info("DepartmentUpdater completed");
}


}