package com.easyshopping.job;
 import javax.annotation.Resource;
import com.easyshopping.service.OrderService;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component("orderJob")
@Lazy(false)
public class OrderJob {

@Resource(name = "orderServiceImpl")
 private  OrderService orderService;


@Scheduled(cron = "${job.order_release_stock.cron}")
public void releaseStock(){
    orderService.releaseStock();
}


}