import com.ats.hrmgt.model.RoutePlanDetailWithName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface RoutePlanDetailWithNameRepo extends JpaRepository<RoutePlanDetailWithName, Integer> {


@Query(value = "select pd.*,emp.first_name,emp.surname from t_route_plan_detail pd,m_employees emp,t_route_plan_header ph where ph.plan_date=:date " + "and pd.plan_head_id=ph.plan_head_id and emp.emp_id=pd.driver_id and emp.del_status=1 and emp.notice_pay_amount=0", nativeQuery = true)
public List<RoutePlanDetailWithName> getDriverPlanList(String date)


@Query(value = "select\n" + "        pd.plan_detail_id,\n" + "        pd.plan_head_id,\n" + "        pd.route_id,\n" + "        pd.driver_id,\n" + "        pd.isoffday_isff,\n" + "        pd.type_id,\n" + "        pd.route_name,\n" + "        pd.fr_name,\n" + "        pd.fr_ids,\n" + "        pd.late_mark,\n" + "        pd.late_min,\n" + "        pd.start_time,\n" + "        pd.km,\n" + "        pd.incentive,\n" + "        pd.del_status,\n" + "        pd.extra_int1,\n" + "        ph.is_confirm as extra_int2,\n" + "        ph.plan_date as extra_var1,\n" + "        pd.extra_var2,\n" + "        concat(emp.first_name,' ',emp.surname) as first_name,\n" + "        ph.plan_date as surname \n" + "    from\n" + "        t_route_plan_detail pd,\n" + "        m_employees emp,\n" + "        t_route_plan_header ph \n" + "    where\n" + "        ph.plan_date between :fromDate and :toDate \n" + "        and pd.plan_head_id=ph.plan_head_id \n" + "        and emp.emp_id=:empId and emp.emp_id=pd.driver_id \n" + "        and emp.del_status=1 order by ph.plan_date", nativeQuery = true)
public List<RoutePlanDetailWithName> getDriverPlanListByEmpId(String fromDate,String toDate,int empId)


@Query(value = "select\n" + "        pd.plan_detail_id,\n" + "        pd.plan_head_id,\n" + "        pd.route_id,\n" + "        pd.driver_id,\n" + "        pd.isoffday_isff,\n" + "        pd.type_id,\n" + "        pd.route_name,\n" + "        pd.fr_name,\n" + "        pd.fr_ids,\n" + "        pd.late_mark,\n" + "        pd.late_min,\n" + "        pd.start_time,\n" + "        pd.km,\n" + "        pd.incentive,\n" + "        pd.del_status,\n" + "        pd.extra_int1,\n" + "        ph.is_confirm as extra_int2,\n" + "        emp.mobile_no_1 as extra_var1,\n" + "        pd.extra_var2,\n" + "        concat(emp.first_name,\n" + "        ' ',\n" + "        emp.surname) as first_name,\n" + "        ph.plan_date as surname      \n" + "    from\n" + "        t_route_plan_detail pd,\n" + "        m_employees emp,\n" + "        t_route_plan_header ph      \n" + "    where\n" + "        ph.plan_date = :date         \n" + "        and pd.plan_head_id=ph.plan_head_id  \n" + "        and emp.emp_id=pd.driver_id          \n" + "        and emp.del_status=1", nativeQuery = true)
public List<RoutePlanDetailWithName> getDriverListForNextDaySchedule(String date)


}