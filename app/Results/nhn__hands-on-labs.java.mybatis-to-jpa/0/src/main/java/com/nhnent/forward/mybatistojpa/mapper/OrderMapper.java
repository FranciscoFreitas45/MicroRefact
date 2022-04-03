package com.nhnent.forward.mybatistojpa.mapper;
 import com.nhnent.forward.mybatistojpa.model.Order;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface OrderMapper {


public int insertOrder(Order order)
;

public Order getOrder(Long orderId)
;

public int deleteOrder(Long orderId)
;

public List<Order> getOrders(int offset,int limit)
;

public int getOrderCount()
;

}