package com.hmm.Work.dao;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.Work.entity.Work;
import com.hmm.calendars.entity.SchedulEvent;
import com.hmm.employee.entity.Employee;
@Repository
public interface WorkDao extends JpaSpecificationExecutor<Work>, PagingAndSortingRepository<Work, Long>{


@Query("SELECT MONTH(s.workDate) as quarter ,sum(s.worktime) as worktime FROM Employee e, Work s   " + "WHERE YEAR(s.workDate) = ?1 AND s.lackCard = 1 AND e.userName = ?2 AND s.employ.emp_id = e.emp_id  GROUP BY MONTH(s.workDate) ")
public List<Map<Object,Object>> findByyearAndOntudytimeworktime(Integer year,String userName)
;

@Query("SELECT MONTH(s.workDate) as quarter ,COUNT(DISTINCT e.empNo) as leaveEarly FROM Work s INNER JOIN Employee e on e.emp_id = s.employ.emp_id  " + "WHERE YEAR(s.workDate) = ?1 AND s.leaveEarly = 1  GROUP BY MONTH(s.workDate) ")
public List<Map<Object,Object>> findleaveEarly(Integer year)
;

@Query("SELECT MONTH(s.workDate) as quarter ,COUNT(*) as leaveEarly FROM Employee e, Work s   " + "WHERE YEAR(s.workDate) = ?1 AND s.leaveEarly = 1 AND e.userName = ?2 AND s.employ.emp_id = e.emp_id  GROUP BY MONTH(s.workDate) ")
public List<Map<Object,Object>> findByyearAndOntudytimeleaveEary(Integer year,String userName)
;

@Query("SELECT COUNT(DISTINCT e.empNo) FROM Employee e INNER JOIN Work s ON e.emp_id = s.employ.emp_id " + "where date_format(s.workDate,'%Y-%m')=date_format(now(),'%Y-%m') and  s.offdutytime is NOT NULL AND s.ontudytime IS NOT NULL")
public Integer findExactlyPerson()
;

@Query("SELECT COUNT(*) from Employee e, Work w where DATE_FORMAT(w.workDate,'%Y-%m') = date_format(now(),'%Y-%m') " + "AND e.userName = ?1 AND w.employ.emp_id = e.emp_id AND w.lackCard = 1")
public Integer findByEmployAndOntudytimelackCard(String userName)
;

@Query("SELECT MONTH(s.workDate) as quarter ,COUNT(*) as late FROM Employee e, Work s  " + "WHERE YEAR(s.workDate) = ?1 AND s.lackCard = 1 AND e.userName = ?2 AND s.employ.emp_id = e.emp_id  GROUP BY MONTH(s.workDate) ")
public List<Map<Object,Object>> findByyearAndOntudytimelate(Integer year,String userName)
;

@Query("SELECT COUNT(*) from Employee e, Work w where DATE_FORMAT(w.workDate,'%Y-%m') = date_format(now(),'%Y-%m') " + "AND e.userName = ?1 AND w.employ.emp_id = e.emp_id AND w.leaveEarly = 1")
public Integer findByEmployAndOntudytimeleaveEarly(String userName)
;

@Query("SELECT COUNT(DISTINCT e.empNo) FROM Employee e INNER JOIN Work s ON e.emp_id = s.employ.emp_id " + "where date_format(s.workDate,'%Y-%m')=date_format(now(),'%Y-%m') and s.late = 1")
public Integer findTatalPersonLate()
;

@Query("select month(s.workDate) as quarter , count(DISTINCT e.empNo) as late from  Work s INNER JOIN Employee e on e.emp_id = s.employ.emp_id  where  year(s.workDate)=?1   and  s.late=1   group by month(s.workDate)")
public List<Map<Object,Object>> findlate(Integer year)
;

@Query("SELECT COUNT(DISTINCT e.empNo) FROM Employee e INNER JOIN Work s ON e.emp_id = s.employ.emp_id " + "where date_format(s.workDate,'%Y-%m')=date_format(now(),'%Y-%m') and s.normal = 0")
public Integer findTatalPersonNomal()
;

@Query("SELECT COUNT(DISTINCT e.empNo) FROM Employee e INNER JOIN Work s ON e.emp_id = s.employ.emp_id " + "where date_format(s.workDate,'%Y-%m')=date_format(now(),'%Y-%m') and s.leaveEarly = 1")
public Integer findTatalPersonleaveEarly()
;

@Query("SELECT COUNT(*) from Employee e, Work w where DATE_FORMAT(w.workDate,'%Y-%m') = date_format(now(),'%Y-%m') " + "AND e.userName = ?1 AND w.employ.emp_id = e.emp_id AND w.normal = 1")
public Integer findByEmployAndOntudytimenormal(String userName)
;

@Query("SELECT MONTH(s.workDate) as quarter ,sum(s.overtime) as worktime FROM Employee e, Work s   " + "WHERE YEAR(s.workDate) = ?1 AND s.lackCard = 1 AND e.userName = ?2 AND s.employ.emp_id = e.emp_id  GROUP BY MONTH(s.workDate) ")
public List<Map<Object,Object>> findByyearAndOntudytimeOvertime(Integer year,String userName)
;

@Query("select SUM(w.overtime)  from Work w , Employee e  where date_format(w.workDate,'%Y-%m')=date_format(now(),'%Y-%m')" + "AND e.userName = ?1 and w.employ.emp_id = e.emp_id")
public Float findattenceTotalovertime(String userbname)
;

@Query("SELECT COUNT(*) from Employee e, Work w where DATE_FORMAT(w.workDate,'%Y-%m') = date_format(now(),'%Y-%m') " + "AND e.userName = ?1 AND w.employ.emp_id = e.emp_id AND w.late = 1")
public Integer findByEmployAndOntudytimelate(String userName)
;

public Work findByWorkDateAndEmploy(Date workDate,Employee employee)
;

@Query("select SUM(w.worktime)  from Work w , Employee e  where date_format(w.workDate,'%Y-%m')=date_format(now(),'%Y-%m')" + "AND e.userName = ?1 and w.employ.emp_id = e.emp_id ")
public Float findattenceTotalworktime(String userbname)
;

@Query("SELECT COUNT(DISTINCT e.empNo) FROM Employee e INNER JOIN Work s ON e.emp_id = s.employ.emp_id " + "where date_format(s.workDate,'%Y-%m')=date_format(now(),'%Y-%m') and s.overtime <> 0")
public Integer findTatalPersonOvertime()
;

@Query("SELECT MONTH(s.workDate) as quarter ,COUNT(DISTINCT e.empNo) as lackcard FROM Work s INNER JOIN Employee e on e.emp_id = s.employ.emp_id " + "WHERE YEAR(s.workDate) = ?1 AND s.lackCard = 1  GROUP BY MONTH(s.workDate) ")
public List<Map<Object,Object>> findlackcard(Integer year)
;

@Query("SELECT MONTH(s.workDate) as quarter ,COUNT(*) as lackcard FROM Employee e, Work s   " + "WHERE YEAR(s.workDate) = ?1 AND s.lackCard = 1 AND e.userName = ?2 AND s.employ.emp_id = e.emp_id  GROUP BY MONTH(s.workDate) ")
public List<Map<Object,Object>> findByyearAndOntudytimelackcard(Integer year,String userName)
;

public Set<Work> getWorks(Integer emp_id);

public void setWorks(Integer emp_id,Set<Work> works);

}