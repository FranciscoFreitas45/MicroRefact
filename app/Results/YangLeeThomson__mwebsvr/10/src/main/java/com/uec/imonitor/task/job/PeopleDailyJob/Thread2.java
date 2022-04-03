package com.uec.imonitor.task.job.PeopleDailyJob;
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
public class Thread2 implements Runnable{

 private  Logger logger;

 private  IPeoplesDailyService peoplesDailyService;

public Thread2(IPeoplesDailyService peoplesDailyService) {
    this.peoplesDailyService = peoplesDailyService;
}
@Override
public void run(){
    Date dt = new Date();
    logger.info("进入Imdeia推送线程，时间：" + dt);
    peoplesDailyService.dataToImedia();
}


}