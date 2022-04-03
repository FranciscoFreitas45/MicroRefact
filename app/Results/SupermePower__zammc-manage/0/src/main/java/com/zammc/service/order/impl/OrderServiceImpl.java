package com.zammc.service.order.impl;
 import com.zammc.common.DateUtil;
import com.zammc.domain.order.OrderInfoEntity;
import com.zammc.domain.order.OrderItemEntity;
import com.zammc.page.PageBean;
import com.zammc.repository.OrderItemRepository;
import com.zammc.repository.OrderRepository;
import com.zammc.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.zammc.DTO.PageBean;
@Service
public class OrderServiceImpl implements OrderService{

@Autowired
 private  OrderRepository orderRepository;

@Autowired
 private  OrderItemRepository orderItemRepository;


@Override
public void queryOrderPage(OrderInfoEntity orderInfo,PageBean pageBean){
    int page = pageBean.getPageNum() - 1;
    int size = pageBean.getPageSize();
    List<Sort.Order> orders = new ArrayList<>();
    orders.add(new Sort.Order(Sort.Direction.DESC, "updateTime"));
    Sort sort = new Sort(orders);
    Pageable pageable = new PageRequest(page, size, sort);
    Page<OrderInfoEntity> orderInfoPage = orderRepository.findAll(OrderInfoSpecification.where(orderInfo), pageable);
    pageBean.setPageSize(orderInfoPage.getSize());
    pageBean.setPageNum(orderInfoPage.getNumber() + 1);
    pageBean.setTotalPage(orderInfoPage.getTotalPages());
    pageBean.setTotalRecorder(orderInfoPage.getTotalElements());
    pageBean.setContent(orderInfoPage.getContent());
}


@Override
public BigDecimal queryGoodsItemPriceSum(OrderInfoEntity orderInfo){
    return orderItemRepository.queryGoodsItemPriceSum(orderInfo.getOrderId());
}


@Override
public Double queryTotalPrice(){
    return orderRepository.queryTotalPrice(DateUtil.getCurrentTime(new Timestamp(System.currentTimeMillis())));
}


@Override
public void cancelOrder(OrderInfoEntity orderInfo){
    OrderInfoEntity one = orderRepository.findOne(orderInfo.getOrderId());
    one.setPayStatus((byte) 2);
    orderRepository.saveAndFlush(one);
}


@Override
public void deleteOrder(OrderInfoEntity orderInfo){
    OrderInfoEntity one = orderRepository.findOne(orderInfo.getOrderId());
    if (one != null && one.getDataStatus() == 0) {
        one.setDataStatus((byte) 1);
        orderRepository.saveAndFlush(one);
    }
}


@Override
public List<OrderItemEntity> queryOrderDetail(OrderInfoEntity orderInfo){
    return orderItemRepository.queryOrderItem(orderInfo.getOrderId());
}


@Override
public Long queryOrderCount(){
    return orderRepository.queryOrderCount(DateUtil.getCurrentTime(new Timestamp(System.currentTimeMillis())));
}


@Override
public void finishOrder(OrderInfoEntity orderInfo){
    OrderInfoEntity one = orderRepository.findOne(orderInfo.getOrderId());
    if (one != null && one.getDataStatus() == 0) {
        one.setPayStatus((byte) 1);
        orderRepository.saveAndFlush(one);
    }
}


}