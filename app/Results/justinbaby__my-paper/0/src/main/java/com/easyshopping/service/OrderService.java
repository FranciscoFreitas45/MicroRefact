package com.easyshopping.service;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Admin;
import com.easyshopping.entity.Cart;
import com.easyshopping.entity.CouponCode;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Order;
import com.easyshopping.entity.Order.OrderStatus;
import com.easyshopping.entity.Order.PaymentStatus;
import com.easyshopping.entity.Order.ShippingStatus;
import com.easyshopping.entity.Payment;
import com.easyshopping.entity.PaymentMethod;
import com.easyshopping.entity.Receiver;
import com.easyshopping.entity.Refunds;
import com.easyshopping.entity.Returns;
import com.easyshopping.entity.Shipping;
import com.easyshopping.entity.ShippingMethod;
public interface OrderService extends BaseService<Order, Long>{


public void cancel(Order order,Admin operator)
;

public Long count(OrderStatus orderStatus,PaymentStatus paymentStatus,ShippingStatus shippingStatus,Boolean hasExpired)
;

public Integer getSalesVolume(Date beginDate,Date endDate)
;

public BigDecimal getSalesAmount(Date beginDate,Date endDate)
;

public void update(Order order,Admin operator)
;

public Page<Order> findPage(OrderStatus orderStatus,PaymentStatus paymentStatus,ShippingStatus shippingStatus,Boolean hasExpired,Pageable pageable)
;

public void refunds(Order order,Refunds refunds,Admin operator)
;

public void confirm(Order order,Admin operator)
;

public Long waitingShippingCount(Member member)
;

public void shipping(Order order,Shipping shipping,Admin operator)
;

public Order build(Cart cart,Receiver receiver,PaymentMethod paymentMethod,ShippingMethod shippingMethod,CouponCode couponCode,boolean isInvoice,String invoiceTitle,boolean useBalance,String memo)
;

public Order findBySn(String sn)
;

public List<Order> findList(Member member,Integer count,List<Filter> filters,List<com.easyshopping.Order> orders)
;

public Long waitingPaymentCount(Member member)
;

public Order create(Cart cart,Receiver receiver,PaymentMethod paymentMethod,ShippingMethod shippingMethod,CouponCode couponCode,boolean isInvoice,String invoiceTitle,boolean useBalance,String memo,Admin operator)
;

public void payment(Order order,Payment payment,Admin operator)
;

public void returns(Order order,Returns returns,Admin operator)
;

public void complete(Order order,Admin operator)
;

public void releaseStock()
;

}