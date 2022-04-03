package com.byr.warehouse.dao;
 import com.byr.warehouse.pojo.ApplyEnter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
@Repository
public interface ApplyEnterRepository extends JpaRepository<ApplyEnter, Long>{


@Query("SELECT enter FROM ApplyEnter enter where enter.enterCode = :enterCode and materialCode =:materialCode")
public List<ApplyEnter> findApplyEnterByEnterCodeAndMaterialCode(String enterCode,String materialCode)
;

public Page<ApplyEnter> findApplyEnterByStatusNot(String Status,Pageable pageable)
;

@Query("SELECT enter FROM ApplyEnter enter where enter.applyDate >= :startDate and  enter.applyDate <= :endDate and enter.status='已确认'")
public List<ApplyEnter> findBetweenDays(Date startDate,Date endDate)
;

public Page<ApplyEnter> findApplyEnterByStatus(String Status,Pageable pageable)
;

@Query("SELECT enter FROM ApplyEnter enter WHERE TO_DAYS(NOW()) - TO_DAYS(enter.applyDate) = 0 AND STATUS='已确认'")
public List<ApplyEnter> getTodayEnsure()
;

public ApplyEnter findApplyEnterByenterId(Integer id)
;

@Query("SELECT number FROM ApplyEnter enter WHERE TO_DAYS(NOW()) - TO_DAYS(enter.applyDate) = 1 AND STATUS='已确认'")
public List<ApplyEnter> getYestdayApplys()
;

@Query("SELECT enter FROM ApplyEnter enter where enter.applyDate <= :applyDate and status ='待审核'")
public List<ApplyEnter> findBeforeDate(Date date)
;

}