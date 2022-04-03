package com.zis.storage.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.storage.entity.StorageOrder;
public interface StorageOrderDao extends JpaSpecificationExecutor<StorageOrder>, PagingAndSortingRepository<StorageOrder, Integer>, CrudRepository<StorageOrder, Integer>{


@Query("from StorageOrder where orderId in (:ids)")
public List<StorageOrder> findByOrderIds(List<Integer> ids)
;

public StorageOrder findByOutOrderId(Integer outOrderId)
;

@Query("select distinct(o.outOrderId) from StorageOrder o, StorageIoDetail d where o.orderId=d.orderId and d.batchId=:batchId")
public List<Integer> findOutOrderIdsByBatchId(Integer batchId)
;

@Modifying
@Query("update StorageOrder s set s.tradeStatus='sent' where s.tradeStatus='processing' and s.orderId in (select orderId from StorageIoDetail where batchId = :batchId)")
public Integer updateToSentByBatchId(Integer batchId)
;

}