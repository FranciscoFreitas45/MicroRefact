package com.crontab;
 import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.Serializable;
public class NormalSizeCalculationJob implements Serializable,Job{

 private  Logger LOGGER;

 private  String CXCODE;


@Override
public void execute(JobExecutionContext context){
    LOGGER.info("calculate normal size of trades starts...");
    try {
        // get service from service factory
        OrderNormalSizeService service = (OrderNormalSizeService) ServiceFactory.getInstance().findService(OrderNormalSizeServiceImpl.class.getName());
        String result = service.calculateAllNormalSize();
        if (!"Success".equals(result)) {
            LOGGER.fatal(String.format("%s %s", CXCODE, result));
        }
    } catch (Exception exp) {
        LOGGER.fatal(String.format("%s failed to calculate normal size due to exception raised: %s", CXCODE, exp.getMessage()));
        LOGGER.error("failed to calculate normal size due to exception raised", exp);
    } finally {
        LOGGER.info("end of calculating normal size of trades");
    }
}


}