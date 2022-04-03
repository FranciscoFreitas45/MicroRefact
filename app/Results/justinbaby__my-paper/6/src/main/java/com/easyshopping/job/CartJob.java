package com.easyshopping.job;
 import javax.annotation.Resource;
import com.easyshopping.service.CartService;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component("cartJob")
@Lazy(false)
public class CartJob {

@Resource(name = "cartServiceImpl")
 private  CartService cartService;


@Scheduled(cron = "${job.cart_evict_expired.cron}")
public void evictExpired(){
    cartService.evictExpired();
}


}