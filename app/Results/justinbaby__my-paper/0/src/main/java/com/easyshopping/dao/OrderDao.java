package com.easyshopping.dao;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Order;
import com.easyshopping.entity.Order.OrderStatus;
import com.easyshopping.entity.Order.PaymentStatus;
import com.easyshopping.entity.Order.ShippingStatus;
public interface OrderDao extends BaseDao<Order, Long>{


public Long waitingShippingCount(Member member)
;

public Order findBySn(String sn)
;

public List<Order> findList(Member member,Integer count,List<Filter> filters,List<com.easyshopping.Order> orders)
;

public Long waitingPaymentCount(Member member)
;

public Long count(OrderStatus orderStatus,PaymentStatus paymentStatus,ShippingStatus shippingStatus,Boolean hasExpired)
;

public Integer getSalesVolume(Date beginDate,Date endDate)
;

public BigDecimal getSalesAmount(Date beginDate,Date endDate)
;

public Page<Order> findPage(OrderStatus orderStatus,PaymentStatus paymentStatus,ShippingStatus shippingStatus,Boolean hasExpired,Pageable pageable)
;

public void releaseStock()
;

}