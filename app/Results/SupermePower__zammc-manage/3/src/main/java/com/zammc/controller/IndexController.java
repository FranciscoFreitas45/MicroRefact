package com.zammc.controller;
 import com.zammc.service.dining.DiningTableService;
import com.zammc.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.zammc.Interface.OrderService;
@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {

@Autowired
 private  DiningTableService diningTableService;

@Autowired
 private  OrderService orderService;


@RequestMapping(value = "/")
public ModelAndView index(){
    ModelAndView modelAndView = new ModelAndView("index/home-page");
    try {
        Long orderCount = orderService.queryOrderCount();
        Double totalPrice = orderService.queryTotalPrice();
        Long freeTableCount = diningTableService.queryFreeTableCount();
        modelAndView.addObject("orderCount", orderCount).addObject("totalPrice", totalPrice == null ? "0.00" : totalPrice).addObject("freeTableCount", freeTableCount);
    } catch (Exception e) {
        log.error("IndexController index Exception\n", e);
    }
    return modelAndView;
}


}