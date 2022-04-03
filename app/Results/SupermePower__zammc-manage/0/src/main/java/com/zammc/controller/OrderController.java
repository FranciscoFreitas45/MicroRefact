package com.zammc.controller;
 import com.zammc.domain.order.OrderInfoEntity;
import com.zammc.domain.order.OrderItemEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.math.BigDecimal;
import java.util.List;
@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

@Autowired
 private  OrderService orderService;


@RequestMapping(value = "/queryOrderPage")
public ModelAndView queryOrderPage(OrderInfoEntity orderInfo,PageBean pageBean){
    ModelAndView modelAndView = new ModelAndView("order/order-list");
    try {
        orderService.queryOrderPage(orderInfo, pageBean);
        modelAndView.addObject("page", pageBean).addObject("order", orderInfo);
    } catch (Exception e) {
        log.error("OrderController queryOrderPage Exception \n", e);
    }
    return modelAndView;
}


@RequestMapping(value = "/cancelOrder")
@ResponseBody
public Message cancelOrder(OrderInfoEntity orderInfo){
    Message message = null;
    try {
        orderService.cancelOrder(orderInfo);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "取消成功");
    } catch (Exception e) {
        log.error("OrderController cancelOrder orderId -> {} Exception \n", orderInfo.getOrderId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "取消失败");
    }
    return message;
}


@RequestMapping(value = "/deleteOrder")
@ResponseBody
public Message deleteOrder(OrderInfoEntity orderInfo){
    Message message = null;
    try {
        orderService.deleteOrder(orderInfo);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "删除成功");
    } catch (Exception e) {
        log.error("OrderController deleteOrder orderId -> {} Exception \n", orderInfo.getOrderId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "删除失败");
    }
    return message;
}


@RequestMapping(value = "/queryOrderDetail")
public ModelAndView queryOrderDetail(OrderInfoEntity orderInfo){
    ModelAndView modelAndView = new ModelAndView("order/order-detail");
    try {
        List<OrderItemEntity> orderItemEntities = orderService.queryOrderDetail(orderInfo);
        BigDecimal orderTotalPrice = orderService.queryGoodsItemPriceSum(orderInfo);
        modelAndView.addObject("orderItem", orderItemEntities).addObject("totalPrice", orderTotalPrice);
    } catch (Exception e) {
        log.error("OrderController queryOrderDetail orderId -> {} Exception \n", orderInfo.getOrderId(), e);
    }
    return modelAndView;
}


@RequestMapping(value = "/finishOrder")
@ResponseBody
public Message finishOrder(OrderInfoEntity orderInfo){
    Message message = null;
    try {
        orderService.finishOrder(orderInfo);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "操作成功");
    } catch (Exception e) {
        log.error("OrderController finishOrder orderId -> {} Exception \n", orderInfo.getOrderId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


}