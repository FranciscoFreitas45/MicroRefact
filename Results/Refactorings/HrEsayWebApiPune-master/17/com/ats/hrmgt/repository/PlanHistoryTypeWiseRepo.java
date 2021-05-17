import com.ats.hrmgt.model.PlanHistoryTypeWise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface PlanHistoryTypeWiseRepo extends JpaRepository<PlanHistoryTypeWise, Integer> {


@Query(value = "select *,ifnull((select count('') as count  from t_route_plan_detail pd,t_route_plan_header ph where ph.plan_date between" + " :fromDate and :toDate and pd.driver_id=:empId and ph.plan_head_id=pd.plan_head_id and pd.type_id=m_route_type.type_id),0) as count," + "ifnull((select sum(pd.incentive) as count  from t_route_plan_detail pd,t_route_plan_header ph where ph.plan_date " + "between :fromDate and :toDate and pd.driver_id=:empId and ph.plan_head_id=pd.plan_head_id and pd.type_id=m_route_type.type_id),0) as incentive," + "ifnull((select sum(pd.km) as count  from t_route_plan_detail pd,t_route_plan_header ph where ph.plan_date between :fromDate and :toDate " + "and pd.driver_id=:empId and ph.plan_head_id=pd.plan_head_id and pd.type_id=m_route_type.type_id),0) as km from m_route_type where del_status=1", nativeQuery = true)
public List<PlanHistoryTypeWise> getPlanHistoryByEmpId(int empId,String fromDate,String toDate)


}