package com.project.stockexchangeappbackend.repository;
 import com.project.stockexchangeappbackend.entity.Transaction;
import com.project.stockexchangeappbackend.util.timemeasuring.DBQueryMeasureTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction>{


@Override
@DBQueryMeasureTime
public Optional<Transaction> findById(Long id)
;

@Override
@DBQueryMeasureTime
public S save(S s)
;

@DBQueryMeasureTime
@Query("SELECT t FROM Transaction t WHERE t.buyingOrder.stock.id = :stockId ORDER BY t.date desc")
public List<Transaction> getTransactionsByStockId(Long stockId)
;

@Override
@DBQueryMeasureTime
public Page<Transaction> findAll(Specification<Transaction> specification,Pageable pageable)
;

}