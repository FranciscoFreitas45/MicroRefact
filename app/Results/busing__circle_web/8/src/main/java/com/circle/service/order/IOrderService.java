package com.circle.service.order;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.circle.Circle;
import com.circle.pojo.order.Order;
import com.circle.pojo.order.OrderDetail;
import com.circle.pojo.user.User;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface IOrderService {


public boolean setOrderPayStatus(int payStatus,String orderId,String tradeNo)
;

public Order queryOrderByOrderNo(String orderNo)
;

public void addMOrder(Order order,List<OrderDetail> orderDetailList)
;

public boolean reciveGood(String order_id,User user)
;

public List<Map<String,Object>> queryOrderList(Page page,int userId)
;

public Order queryOrderById(String id)
;

public Map<String,Object> queryOrderDetailById(String id)
;

public Map<String,List<Map<String,Object>>> queryOrderDetailList(String orderId)
;

public List<Order> submitOrder(List<Circle> circleList,Map<String,List<Map<String,Object>>> orderList,User user,String cartIds,Map<String,String> remarkMap,Order order)
;

public List<Map<String,Object>> queryMOrderDetailList(String orderId)
;

public List<OrderDetail> queryOrdersDetailById(String id,Page page)
;

}