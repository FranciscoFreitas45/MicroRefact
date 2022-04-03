package com.hmm.leave.dao;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.leave.entity.Leave;
@Repository
public interface LeaveRepository extends PagingAndSortingRepository<Leave, Long>, JpaSpecificationExecutor<Leave>{


@Query("select COUNT(*)  from Leave w , Employee e  where date_format(w.startTime,'%Y-%m')=date_format(now(),'%Y-%m')" + "AND e.userName = ?1 and w.employ.emp_id = e.emp_id ")
public Float findTotalLeaveTimes(String userName)
;

@Query("SELECT MONTH(s.startTime) as quarter ,count(*) as leave FROM Employee e, Leave s   " + "WHERE YEAR(s.startTime) = ?1 AND  e.userName = ?2 AND s.employ.emp_id = e.emp_id  GROUP BY MONTH(s.startTime) ")
public List<Map<Object,Object>> findByyearAndOntudytimeleave(Integer year,String userName)
;

@Query("SELECT MONTH(t.startTime) as quarter ,COUNT(DISTINCT e.empNo) as leave " + "  FROM  Leave t INNER JOIN Employee e on e.emp_id = t.employ.emp_id " + " WHERE YEAR(t.startTime) = ?1  GROUP BY MONTH(t.startTime) ")
public List<Map<Object,Object>> findleave(Integer year)
;

@Query("from Leave leave where leave.userId = ?1")
public Page<Leave> findLeave(String userId,Pageable pageable)
;

@Query("SELECT COUNT(DISTINCT e.empNo) FROM Employee e INNER JOIN Leave s ON e.emp_id = s.employ.emp_id  " + "where date_format(s.startTime,'%Y-%m')=date_format(now(),'%Y-%m')")
public Integer findTatalPersonLeave()
;

public void setLeaves(Integer emp_id,Set<Leave> leaves);

public Set<Leave> getLeaves(Integer emp_id);

}