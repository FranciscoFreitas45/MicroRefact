package com.circle.dao.order;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.order.Order;
import com.circle.pojo.order.OrderDetail;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface IOrderDao {


public boolean setOrderPayStatus(int payStatus,String orderId,String tradeNo)
;

public Order queryOrderByOrderNo(String orderNo)
;

public boolean saveOrder(Order order)
;

public boolean saveOrderDetail(OrderDetail orderDetail)
;

public boolean reciveGood(String order_id)
;

public List<Map<String,Object>> queryOrderList(Page page,int userId)
;

public Order queryOrderById(String id)
;

public List<Map<String,Object>> queryOrderDetailList(String orderId)
;

public Map<String,Object> queryOrderDetailById(String id)
;

public List<OrderDetail> queryOrdersDetailById(String id,Page page)
;

public void batchSaveOrderDetail(List<OrderDetail> orderDetailList)
;

}