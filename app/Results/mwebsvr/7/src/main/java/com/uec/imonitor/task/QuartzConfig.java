package com.uec.imonitor.task;
 import java.io.IOException;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import com.uec.imonitor.common.util.CommonUtil;
@Configuration
public class QuartzConfig {

 private Log log;

@Value("${inews.quartz.properity.config}")
 private  String quartzPropertity;


@Bean(name = "quartzProperties")
public Properties getQuartzProperties(){
    Properties properties = PropertiesLoaderUtils.loadAllProperties(quartzPropertity + ".properties");
    return properties;
}


@Bean(name = "schedulerFactoryBean")
public SchedulerFactoryBean getSchedulerFactoryBean(){
    SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
    // schedulerFactoryBean.setStartupDelay(5);//单位秒秒，应用程序启动5秒后在执行定时任务，延迟5秒启动定时任务
    // 通过applicationContextSchedulerContextKey属性配置spring上下文
    schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
    // 设置quartz配置
    schedulerFactoryBean.setQuartzProperties(this.getQuartzProperties());
    log.info("***********************");
    log.info(CommonUtil.toJson(this.getQuartzProperties()));
    return schedulerFactoryBean;
}


}