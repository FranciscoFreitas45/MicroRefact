package com.zis.purchase.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.purchase.bean.PurchaseDetail;
import com.zis.purchase.bean.PurchaseDetailStatus;
public interface PurchaseDetailDao extends PagingAndSortingRepository<PurchaseDetail, Integer>, JpaSpecificationExecutor<PurchaseDetail>{


@Query(value = "select sum(purchasedAmount - inwarehouseAmount) from PurchaseDetail where status = '" + PurchaseDetailStatus.PURCHASED + "' and bookId = :bookId")
public Integer sumPurchasedAmount(Integer bookId)
;

public List<PurchaseDetail> findByOperatorAndStatus(String operator,String status)
;

public List<PurchaseDetail> findByBatchId(Integer batchId)
;

@Query(value = "from PurchaseDetail where operator=:opr and status=:st and bookId=:bookId")
public List<PurchaseDetail> findByOperatorAndStatusAndBookId(String operator,String status,Integer bookId)
;

}