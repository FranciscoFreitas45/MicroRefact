package com.crontab;
 import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
public class QuartzServlet extends HttpServlet{

 private  Logger LOGGER;


@Override
public void init(){
    super.init();
    // init normal as needed
    new Thread(new NormalSizeInitializationTask()).start();
}


@Override
public void run(){
    // 1. calc first if needed
    OrderNormalSizeService calcService = (OrderNormalSizeService) ServiceFactory.getInstance().findService(OrderNormalSizeServiceImpl.class.getName());
    try {
        calcService.calculateNormalSizeAsNeeded();
    } catch (Exception exp) {
        LOGGER.error("calc size failed", exp);
    }
    // 2. schedule job if needed
    QuartzService qrtzService = (QuartzService) ServiceFactory.getInstance().findService(QuartzServiceImpl.class.getName());
    try {
        LOGGER.info("schedule jobs begins ...");
        qrtzService.scheduleJobs();
        LOGGER.info("schedule jobs done");
    } catch (Exception exp) {
        LOGGER.error("schedule jobs from database failed", exp);
    }
}


}