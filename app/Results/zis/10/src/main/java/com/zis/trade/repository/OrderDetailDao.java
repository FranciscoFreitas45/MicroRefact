package com.zis.trade.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.trade.entity.OrderDetail;
public interface OrderDetailDao extends PagingAndSortingRepository<OrderDetail, Integer>, JpaSpecificationExecutor<OrderDetail>{


public List<OrderDetail> findByOrderIdAndStatus(Integer orderId,String status)
;

@Modifying
// @Query("update OrderDetail set status = 'invalid', version=version+1, updateTime=now() where status='valid' and orderId=:orderId")
@Query("update OrderDetail set status = 'invalid', version=version+1, updateTime=now() where status='valid' and order.id=:orderId")
public int updateStatusToInvalidByOrderId(Integer orderId)
;

}