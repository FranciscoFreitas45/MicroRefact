package com.zammc.service.order;
 import com.zammc.domain.order.OrderInfoEntity;
import com.zammc.domain.order.OrderItemEntity;
import com.zammc.page.PageBean;
import java.math.BigDecimal;
import java.util.List;
public interface OrderService {


public void queryOrderPage(OrderInfoEntity orderInfo,PageBean pageBean)
;

public BigDecimal queryGoodsItemPriceSum(OrderInfoEntity orderInfo)
;

public Double queryTotalPrice()
;

public void cancelOrder(OrderInfoEntity orderInfo)
;

public void deleteOrder(OrderInfoEntity orderInfo)
;

public List<OrderItemEntity> queryOrderDetail(OrderInfoEntity orderInfo)
;

public Long queryOrderCount()
;

public void finishOrder(OrderInfoEntity orderInfo)
;

}