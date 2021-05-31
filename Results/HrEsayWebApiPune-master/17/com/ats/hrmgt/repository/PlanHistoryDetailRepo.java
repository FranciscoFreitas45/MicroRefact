import com.ats.hrmgt.model.PlanHistoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface PlanHistoryDetailRepo extends JpaRepository<PlanHistoryDetail, Integer> {


@Query(value = "select UUID() as id,ifnull((select count('') as count  from t_route_plan_detail pd,t_route_plan_header ph where ph.plan_date between " + ":fromDate and :toDate and pd.driver_id=:empId and ph.plan_head_id=pd.plan_head_id and isoffday_isff=1 ),0) as offdays," + "ifnull((select count('') as count  from t_route_plan_detail pd,t_route_plan_header ph where ph.plan_date between :fromDate and :toDate " + "and pd.driver_id=:empId and ph.plan_head_id=pd.plan_head_id and isoffday_isff=2),0) as ffdays,ifnull((select sum(km) as count  " + "from t_route_plan_detail pd,t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and pd.driver_id=:empId and " + "ph.plan_head_id=pd.plan_head_id  ),0) as km,ifnull((select sum(incentive) as count  from t_route_plan_detail pd,t_route_plan_header ph where " + "ph.plan_date between :fromDate and :toDate and pd.driver_id=:empId and ph.plan_head_id=pd.plan_head_id  ),0) as incentive" + ",ifnull((select concat(first_name,' ',surname) as count  from m_employees where emp_id=:empId),'-') as emp_name", nativeQuery = true)
public PlanHistoryDetail getPlanHistoryByEmpId(int empId,String fromDate,String toDate)


}