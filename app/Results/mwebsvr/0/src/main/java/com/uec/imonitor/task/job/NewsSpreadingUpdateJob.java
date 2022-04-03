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
@Service("newsSpreadingUpdateJob")
@DisallowConcurrentExecution
public class NewsSpreadingUpdateJob extends QuartzJobBean{

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
        int do_num = 2000;
        // 更新
        newsSpreadService.updateSpreadingAnalysisIndexJob(do_num);
        // 更新
        ;
        // 失败任务
        newsSpreadService.insertAndUpdateIndexFailureJob();
        // 删除
        newsSpreadService.deletedSpreadingAnalysisIndexJob(do_num);
        // 删除
        ;
    } catch (SchedulerException e) {
        logger.error("通过spring 上下文获取bean异常," + e);
    }
}


}