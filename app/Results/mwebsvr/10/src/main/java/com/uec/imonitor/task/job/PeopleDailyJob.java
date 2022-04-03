package com.uec.imonitor.task.job;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyEntity;
import com.uec.imonitor.peopledaily.service.impl.PeoplesDailyServiceImpl;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import com.uec.imonitor.peopledaily.service.IPeoplesDailyService;
import javax.xml.crypto.Data;
@Service("peopleDailyJob")
@DisallowConcurrentExecution
public class PeopleDailyJob extends QuartzJobBean{

 private  Logger logger;

 private  Logger logger;

 private  IPeoplesDailyService peoplesDailyService;

 private  Logger logger;

 private  IPeoplesDailyService peoplesDailyService;


@Override
public void executeInternal(JobExecutionContext context){
    logger.info("进入人民日报定时任务：" + new Date());
    IPeoplesDailyService peoplesDailyService = null;
    ApplicationContext ctx;
    try {
        ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
        peoplesDailyService = (IPeoplesDailyService) ctx.getBean("peoplesDailyService");
        // peoplesDailyService.saveNewsForMediaTest();
        // peoplesDailyService.dataToImediaTest();
        peoplesDailyService.dataToAPICloud();
        peoplesDailyService.dataToImedia();
    // Thread1 thread1 = new Thread1(peoplesDailyService);
    // thread1.run();
    // 
    // Thread2 thread2 = new Thread2(peoplesDailyService);
    // thread2.run();
    } catch (Exception e) {
        logger.error("人民日报定时任务测试失败：" + new Date() + "错误信息" + e);
        e.printStackTrace();
    }
}


@Override
public void run(){
    Date dt = new Date();
    logger.info("进入Imdeia推送线程，时间：" + dt);
    peoplesDailyService.dataToImedia();
}


}