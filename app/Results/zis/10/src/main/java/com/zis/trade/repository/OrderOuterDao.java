package com.zis.trade.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.trade.entity.OrderOuter;
public interface OrderOuterDao extends JpaSpecificationExecutor<OrderOuter>, PagingAndSortingRepository<OrderOuter, Integer>{


public List<OrderOuter> findByOrderGroupNumber(String orderGroupNumber)
;

@Query("select outOrderNumber from OrderOuter where orderGroupNumber=:orderGroupNumber")
public List<String> findOutOrderNumbersByOrderId(String orderGroupNumber)
;

@Query("select orderGroupNumber from OrderOuter where outOrderNumber=:outOrderNumber")
public List<String> findOrderGroupNumberByOutOrderNumber(String outOrderNumber)
;

public OrderOuter findByShopIdAndOutOrderNumber(Integer shopId,String outOrderNumber)
;

}