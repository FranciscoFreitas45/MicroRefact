package com.zammc.repository;
 import com.zammc.domain.order.OrderInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
@Repository
public interface OrderRepository extends JpaRepository<OrderInfoEntity, Long>, JpaSpecificationExecutor<OrderInfoEntity>{


@Query(value = "select sum (orderInfo.totalprice) from OrderInfoEntity orderInfo where orderInfo.createTime > :#{#currentTime} and orderInfo.dataStatus = '0'")
public Double queryTotalPrice(Timestamp currentTime)
;

@Query(value = "select count(orderInfo) from OrderInfoEntity orderInfo where orderInfo.createTime > :#{#currentTime} and orderInfo.dataStatus = '0'")
public Long queryOrderCount(Timestamp currentTime)
;

}