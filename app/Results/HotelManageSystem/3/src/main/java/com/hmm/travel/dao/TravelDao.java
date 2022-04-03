package com.hmm.travel.dao;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.travel.entity.Travel;
@Repository
public interface TravelDao extends JpaSpecificationExecutor<Travel>, PagingAndSortingRepository<Travel, Long>{


@Query("SELECT MONTH(s.traStartTime) as quarter ,count(*) as travel FROM Employee e, Travel s   " + "WHERE YEAR(s.traStartTime) = ?1 AND  e.userName = ?2 AND s.employ.emp_id = e.emp_id  GROUP BY MONTH(s.traStartTime) ")
public List<Map<Object,Object>> findByyearAndOntudytimetravel(Integer year,String userName)
;

@Query("SELECT COUNT(DISTINCT e.empNo) FROM Employee e INNER JOIN Travel s ON e.emp_id = s.employ.emp_id  " + "where date_format(s.traStartTime,'%Y-%m')=date_format(now(),'%Y-%m')")
public Integer findTatalPersonTravel()
;

@Query("SELECT MONTH(t.traStartTime) as quarter ,COUNT(DISTINCT e.empNo) as travel " + "  FROM  Travel t INNER JOIN Employee e on e.emp_id = t.employ.emp_id " + " WHERE YEAR(t.traStartTime) = ?1  GROUP BY MONTH(t.traStartTime) ")
public List<Map<Object,Object>> findtravel(Integer year)
;

@Query("select SUM(w.allowance)  from Travel w , Employee e  where date_format(w.traStartTime,'%Y-%m')=date_format(now(),'%Y-%m')" + "AND e.userName = ?1 and w.employ.emp_id = e.emp_id ")
public Float findTotalTravelAllowance(String userName)
;

}