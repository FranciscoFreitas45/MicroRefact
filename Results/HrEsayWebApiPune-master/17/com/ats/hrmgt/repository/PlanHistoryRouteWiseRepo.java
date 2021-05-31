import com.ats.hrmgt.model.PlanHistoryRouteWise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface PlanHistoryRouteWiseRepo extends JpaRepository<PlanHistoryRouteWise, Integer> {


@Query(value = "select\n" + "        id,\n" + "        route_id,\n" + "        route_name,\n" + "        ifnull((select\n" + "            count('') as count  \n" + "        from\n" + "            t_route_plan_detail pd,\n" + "            t_route_plan_header ph \n" + "        where\n" + "            ph.plan_date between :fromDate and :toDate \n" + "            and pd.driver_id=:empId \n" + "            and ph.plan_head_id=pd.plan_head_id \n" + "            and pd.route_id=m_route.route_id),\n" + "        0) as count,\n" + "        ifnull((select\n" + "            sum(pd.incentive) as count  \n" + "        from\n" + "            t_route_plan_detail pd,\n" + "            t_route_plan_header ph \n" + "        where\n" + "            ph.plan_date between :fromDate and :toDate \n" + "            and pd.driver_id=:empId \n" + "            and ph.plan_head_id=pd.plan_head_id \n" + "            and pd.route_id=m_route.route_id),\n" + "        0) as incentive,\n" + "        ifnull((select\n" + "            sum(pd.km) as count  \n" + "        from\n" + "            t_route_plan_detail pd,\n" + "            t_route_plan_header ph \n" + "        where\n" + "            ph.plan_date between :fromDate and :toDate \n" + "            and pd.driver_id=:empId \n" + "            and ph.plan_head_id=pd.plan_head_id \n" + "            and pd.route_id=m_route.route_id),\n" + "        0) as km \n" + "    from \n" + "        m_route\n" + "    where\n" + "        del_status=1", nativeQuery = true)
public List<PlanHistoryRouteWise> getPlanHistoryByEmpId(int empId,String fromDate,String toDate)


}