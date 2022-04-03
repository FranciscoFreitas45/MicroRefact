package org.jeecgframework.web.system.sms.util.task;
 import org.jeecgframework.web.system.sms.service.TSSmsServiceI;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("smsSendTask")
public class SmsSendTask implements Job{

@Autowired
 private  TSSmsServiceI tSSmsService;


public void run(){
    long start = System.currentTimeMillis();
    org.jeecgframework.core.util.LogUtil.info("===================推送消息定时任务开始===================");
    try {
        tSSmsService.send();
    } catch (Exception e) {
        e.printStackTrace();
    }
    org.jeecgframework.core.util.LogUtil.info("===================推送消息定时任务结束===================");
    long end = System.currentTimeMillis();
    long times = end - start;
    org.jeecgframework.core.util.LogUtil.info("总耗时" + times + "毫秒");
}


@Override
public void execute(JobExecutionContext jobExecutionContext){
    run();
}


}