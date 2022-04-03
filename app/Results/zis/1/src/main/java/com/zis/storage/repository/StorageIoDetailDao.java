package com.zis.storage.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.storage.entity.StorageIoDetail;
public interface StorageIoDetailDao extends PagingAndSortingRepository<StorageIoDetail, Integer>, JpaSpecificationExecutor<StorageIoDetail>{


public Page<StorageIoDetail> findByProductIdAndPosIdAndDetailStatusIn(Integer productId,Integer posId,List<String> detailStatus,Pageable page)
;

public List<StorageIoDetail> findByBatchIdAndDetailStatus(Integer batchId,String detailStatus)
;

public List<StorageIoDetail> findByBatchIdAndSkuIdAndPosId(Integer batchId,Integer skuId,Integer posId)
;

public List<StorageIoDetail> findByRepoIdAndIoDetailTypeAndDetailStatusInAndBatchIdIn(Integer repoId,String ioDetailType,List<String> DetailStatusList,List<Integer> batchIds)
;

@Query(nativeQuery = true, value = "select * from storage_io_detail where io_detail_type='outwarehouse' and detail_status='waiting' and batch_id=:batchId order by pos_label asc limit 1")
public StorageIoDetail findNextRecordForPickup(Integer batchId)
;

@Query(nativeQuery = true, value = "select * from storage_io_detail where io_detail_type='outwarehouse' and detail_status='processing' and batch_id=:batchId and operator=:operator order by gmt_modify asc limit 1")
public StorageIoDetail findProcessingRecordByBatchIdAndOperator(Integer batchId,Integer operator)
;

public List<StorageIoDetail> findByOrderId(Integer orderId)
;

public Page<StorageIoDetail> findBySkuIdAndRepoIdAndDetailStatusIn(Integer skuId,Integer repoId,List<String> detailStatus,Pageable page)
;

public Page<StorageIoDetail> findByProductIdAndDetailStatusIn(Integer productId,List<String> detailStatus,Pageable page)
;

@Modifying
@Query("update StorageIoDetail set detailStatus='cancel' where batchId=:batchId")
public int batchCancel(Integer batchId)
;

public List<StorageIoDetail> findByBatchId(Integer batchId)
;

}