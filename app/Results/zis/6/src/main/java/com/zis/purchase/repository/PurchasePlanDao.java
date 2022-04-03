package com.zis.purchase.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.purchase.bean.PurchasePlan;
import com.zis.purchase.bean.PurchasePlanStatus;
public interface PurchasePlanDao extends JpaSpecificationExecutor<PurchasePlan>, PagingAndSortingRepository<PurchasePlan, Integer>{


@Query(value = "from PurchasePlan where bookId=:bookId and repoId = :repoId")
public List<PurchasePlan> findByBookIdForAll(Integer bookId,Integer repoId)
;

@Query(value = "from PurchasePlan where purchasedAmount > 0 and repoId = :repoId and status='" + PurchasePlanStatus.NORMAL + "'")
public List<PurchasePlan> findForRecalcOnwayStock(Integer repoId)
;

@Modifying
@Query(value = "update PurchasePlan set status='" + PurchasePlanStatus.USELESS + "', version=version+1, gmtModify = now() where status='" + PurchasePlanStatus.NORMAL + "' and bookId=:bookId and repoId = :repoId")
public void updateToUselessByBookId(Integer bookId,Integer repoId)
;

@Query(value = "from PurchasePlan where isbn = :isbn and repoId = :repoId and status='" + PurchasePlanStatus.NORMAL + "'")
public List<PurchasePlan> findByIsbn(String isbn,Integer repoId)
;

@Query(value = "from PurchasePlan where bookId=:bookId and repoId = :repoId and status='" + PurchasePlanStatus.NORMAL + "'")
public PurchasePlan findByBookId(Integer bookId,Integer repoId)
;

}