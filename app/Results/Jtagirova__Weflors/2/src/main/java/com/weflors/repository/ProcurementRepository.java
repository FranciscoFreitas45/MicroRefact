package com.weflors.repository;
 import com.weflors.entity.ProcurementEntity;
import com.weflors.entity.ProcurementEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.sql.Timestamp;
import java.util.List;
public interface ProcurementRepository extends JpaRepository<ProcurementEntity, ProcurementEntityPK>{


@Query("select procurement from ProcurementEntity procurement where procurement.productId = :productID")
public ProcurementEntity findProcurementByProductID(Integer productID)
;

@Query("select procurement from ProcurementEntity procurement where procurement.productId = :productID and " + "procurement.procurementDate >= :startDatePeriod and procurement.procurementDate <= :endDatePeriod")
public List<ProcurementEntity> findAllProcurementsByProductIDAndDatePeriod(Integer productID,Timestamp startDatePeriod,Timestamp endDatePeriod)
;

@Query("select procurement from ProcurementEntity procurement where procurement.productId = :productID")
public List<ProcurementEntity> findAllProcurementsByProductID(Integer productID)
;

@Query("select procurement from ProcurementEntity procurement where procurement.procurementDate >= :date")
public List<ProcurementEntity> findAllProcurementsByDate(Timestamp date)
;

}