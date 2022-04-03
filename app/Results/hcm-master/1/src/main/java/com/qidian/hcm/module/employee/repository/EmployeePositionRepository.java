package com.qidian.hcm.module.employee.repository;
 import com.qidian.hcm.module.employee.entity.EmployeePosition;
import com.qidian.hcm.module.employee.enums.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long>, JpaSpecificationExecutor<EmployeePosition>{


public void deleteAllByEmployeeId(Long employeeId)
;

public List<EmployeePosition> findAllByEmployeeIdOrderByStartDateDesc(Long id)
;

@Query(value = "SELECT ep FROM EmployeePosition ep INNER JOIN Employee e ON ep.employeeId = e.id " + "and e.status <> :status ")
public List<EmployeePosition> findAllByExcludeEmployeeStatus(EmployeeStatus employeeStatus)
;

public List<EmployeePosition> findAllByEmployeeId(Long employeeId)
;

public List<EmployeePosition> findAllByPositionIdIn(List<Long> positionIds)
;

public List<EmployeePosition> findAllByEmployeeIdIn(List<Long> employeeIds)
;

@Modifying
@Query(value = "update EmployeePosition set leaderId = :leaderId where leaderId = :employeeId")
public void updateLeaderId(Long employeeId,Long leaderId)
;

@Query(value = "SELECT ep FROM EmployeePosition ep INNER JOIN Employee e ON ep.employeeId = e.id " + "and e.status = :status ")
public List<EmployeePosition> findAllByEmployeeStatus(EmployeeStatus employeeStatus)
;

}