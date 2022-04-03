package com.byr.warehouse.dao;
 import com.byr.warehouse.pojo.ApplyOutPut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
@Repository
public interface ApplyOutPutRepository extends JpaRepository<ApplyOutPut, Long>{


public Page<ApplyOutPut> findApplyOutPutByStatusNot(String Status,Pageable pageable)
;

@Query("SELECT out FROM ApplyOutPut out where out.applyDate >= :startDate and  out.applyDate <= :endDate and status='已确认'")
public List<ApplyOutPut> findBetweenDays(Date startDate,Date endDate)
;

public ApplyOutPut findApplyOutPutById(Integer id)
;

@Query("SELECT out FROM ApplyOutPut out WHERE TO_DAYS(NOW()) - TO_DAYS(out.applyDate) = 0 AND STATUS='已确认'")
public List<ApplyOutPut> getTodayEnsure()
;

public Page<ApplyOutPut> findApplyOutPutByStatus(String Status,Pageable pageable)
;

@Query("SELECT out FROM ApplyOutPut out WHERE TO_DAYS(NOW()) - TO_DAYS(out.applyDate) = 1 AND STATUS='已确认'")
public List<ApplyOutPut> getYestdayApplys()
;

public List<ApplyOutPut> findAllByOutCode(String outCode)
;

}