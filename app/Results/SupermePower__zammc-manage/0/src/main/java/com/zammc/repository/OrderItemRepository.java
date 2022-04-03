package com.zammc.repository;
 import com.zammc.domain.order.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long>, JpaSpecificationExecutor<OrderItemEntity>{


@Query(value = "select sum(subtotal) from order_item where order_id = :orderId", nativeQuery = true)
public BigDecimal queryGoodsItemPriceSum(Long orderId)
;

@Query(value = "select orderItem from OrderItemEntity orderItem where orderItem.orderId = :orderId and orderItem.dataStatus = '0'")
public List<OrderItemEntity> queryOrderItem(Long orderId)
;

}