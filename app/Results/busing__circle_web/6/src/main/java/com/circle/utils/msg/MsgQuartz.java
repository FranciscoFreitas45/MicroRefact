package com.circle.utils.msg;
 import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import com.circle.pojo.msg.MessageBean;
import com.circle.service.msg.IMsgService;
public class MsgQuartz {

 private  Logger log;

@Resource
 private IMsgService msgService;


@Scheduled(cron = "0 0/1 * * * ?")
public void orderGroupLogQuartzTask(){
    log.info("开始执行定时器......");
    try {
        List<MessageBean> list = new ArrayList<MessageBean>();
        // 一次插入的条数
        int rows = 0;
        while (rows < 100) {
            MessageBean msg = MsgQueue.GROUP_QUEUE.poll();
            if (msg != null) {
                list.add(msg);
            } else {
                break;
            }
            rows++;
        }
        if (list != null && list.size() > 0) {
            log.info("从队列提取到消息数：" + list.size());
            msgService.batchAddMsgInfo(list);
        }
        log.info("定时器执行结束......");
    } catch (Exception e) {
        log.error("执行定时器发生异常：" + e.getMessage());
    }
}


}