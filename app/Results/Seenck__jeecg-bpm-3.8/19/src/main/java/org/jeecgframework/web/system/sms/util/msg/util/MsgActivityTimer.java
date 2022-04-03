package org.jeecgframework.web.system.sms.util.msg.util;
 import org.jeecgframework.core.util.LogUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
public class MsgActivityTimer extends QuartzJobBean{


public void executeInternal(JobExecutionContext arg0){
    LogUtil.info("×××××××××××××开始链路检查××××××××××××××");
    int count = 0;
    boolean result = MsgContainer.activityTestISMG();
    while (!result) {
        count++;
        result = MsgContainer.activityTestISMG();
        if (count >= (MsgConfig.getConnectCount() - 1)) {
            // 如果再次链路检查次数超过两次则终止连接
            break;
        }
    }
    LogUtil.info("×××××××××××××链路检查结束××××××××××××××");
}


}