package com.fosun.fc.projects.creepers.schedule;
 import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.redis.service.Impl.RedisConfigServiceImpl;
import com.fosun.fc.projects.creepers.service.impl.CreepersJobServiceImpl;
import com.fosun.fc.projects.creepers.DTO.RedisConfigServiceImpl;
public class SystemInitListener implements ServletContextListener{

 private Logger logger;

public SystemInitListener() {
    super();
}
public void loadJobs(ServletContextEvent sce){
    // 加载可执行的批处理任务列表
    logger.info("《===============STEP1：加载所有批处理任务===============》");
    CreepersJobServiceImpl creepersJobService = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(CreepersJobServiceImpl.class);
    List<CreepersJobDTO> taskList = creepersJobService.getTaskList();
    logger.info("初始化加载定时任务......");
    for (CreepersJobDTO job : taskList) {
        try {
            creepersJobService.addJob(job);
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    logger.info("《===============STEP1：加载批处理任务完毕===============》");
}


@Override
public void contextInitialized(ServletContextEvent sce){
    try {
        // 初始化批处理任务
        this.loadJobs(sce);
        // 初始化缓存
        loadCache(sce);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public void loadCache(ServletContextEvent sce){
    // 加载缓存
    logger.info("《===============STEP2：加载所有批处理任务===============》");
    RedisConfigServiceImpl redisConfigServiceImpl = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(RedisConfigServiceImpl.class);
    redisConfigServiceImpl.refresh();
    logger.info("《===============STEP2：加载所有缓存完毕===============》");
}


@Override
public void contextDestroyed(ServletContextEvent sce){
// TODO Auto-generated method stub
}


}