package com.uec.imonitor.task.job;
 import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import com.uec.imonitor.news.service.INewsSpreadingAnalysisService;
@Service("newsSpreadingInsertJob")
@DisallowConcurrentExecution
public class NewsSpreadingInsertJob extends QuartzJobBean{

 private  Logger logger;


@Override
public void executeInternal(JobExecutionContext context){
    // 获取spring上下文，可以从上下文中获取所有被注入到spring的bean
    try {
        ApplicationContext ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
        INewsSpreadingAnalysisService newsSpreadService = (INewsSpreadingAnalysisService) ctx.getBean("newsSpreadingAnalysisService");
        /**
         * 获取数据,新增的数据
         */
        // 单次分析数
        int do_num = 3000;
        newsSpreadService.insertSpreadingAnalysisIndexJob(do_num);
    } catch (SchedulerException e) {
        logger.error("通过spring 上下文获取bean异常," + e);
    }
}


}