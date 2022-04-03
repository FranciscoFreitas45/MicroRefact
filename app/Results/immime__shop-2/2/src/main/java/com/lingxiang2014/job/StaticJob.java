package com.lingxiang2014.job;
 import javax.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.lingxiang2014.service.StaticService;
@Component("staticJob")
@Lazy(false)
public class StaticJob {

@Resource(name = "staticServiceImpl")
 private  StaticService staticService;


@Scheduled(cron = "${job.static_build.cron}")
public void build(){
    staticService.buildAll();
}


}