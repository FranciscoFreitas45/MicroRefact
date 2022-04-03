package com.weflors.repository;
 import com.weflors.entity.SaleEntity;
import com.weflors.entity.SaleEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
public interface SaleRepository extends JpaRepository<SaleEntity, SaleEntityPK>{


@Query("select sale from SaleEntity sale where DATE(sale.saleDate) = :localDate  and sale.salePrice <> 0 ORDER BY sale.saleDate DESC")
public List<SaleEntity> findAllSalesForThisDay(LocalDate localDate)
;

@Modifying
@Transactional
@Query("update SaleEntity set details = :details where productId = :productId")
public void updateSaleDetailsById(String details,Integer productId)
;

@Query("select sale from SaleEntity sale where sale.saleDate >= :date")
public List<SaleEntity> findAllSalesByDate(Timestamp date)
;

@Query("select sale from SaleEntity sale where sale.productId = :productID and sale.saleDate >= :startDatePeriod and sale.saleDate <= :endDatePeriod and sale.salePrice <> 0")
public List<SaleEntity> findAllSalesByProductIDAndDatePeriod(Integer productID,Timestamp startDatePeriod,Timestamp endDatePeriod)
;

@Query("select sale from SaleEntity sale where sale.salePrice = :salePrice ORDER BY sale.articul, sale.saleDate DESC")
public List<SaleEntity> findAllSalesBySalePrice(BigDecimal salePrice)
;

@Query("select sale from SaleEntity sale where sale.productId = :productID and sale.salePrice <> 0")
public List<SaleEntity> findAllSalesByProductID(Integer productID)
;

}