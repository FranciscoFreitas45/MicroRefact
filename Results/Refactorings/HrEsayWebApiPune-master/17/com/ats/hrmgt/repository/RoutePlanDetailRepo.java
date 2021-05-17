import com.ats.hrmgt.model.RoutePlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface RoutePlanDetailRepo extends JpaRepository<RoutePlanDetail, Integer> {


@Transactional
@Modifying
@Query("update RoutePlanDetail set late_mark=:lateMark,late_min=:lateMin,start_time=:startTime  WHERE plan_detail_id=:planDetailId")
public int changeLateMarkInRoaster(int planDetailId,int lateMark,int lateMin,String startTime)


@Query(value = "select pd.plan_detail_id,pd.plan_head_id,pd.route_id,pd.driver_id,pd.isoffday_isff,pd.type_id,pd.route_name,pd.fr_name,pd.fr_ids,pd.late_mark,pd.late_min," + "pd.start_time,pd.km,pd.incentive,pd.del_status,pd.extra_int1,ph.is_confirm as extra_int2,ph.plan_date as extra_var1,pd.extra_var2 from t_route_plan_header ph," + "t_route_plan_detail pd where plan_date between :fromDate and :toDate and pd.plan_head_id=ph.plan_head_id   ", nativeQuery = true)
public List<RoutePlanDetail> getListForMonthlySheet(String fromDate,String toDate)


@Query(value = "select pd.plan_detail_id,pd.plan_head_id,pd.route_id,pd.driver_id,pd.isoffday_isff,pd.type_id,pd.route_name,pd.fr_name,pd.fr_ids,pd.late_mark,pd.late_min," + "pd.start_time,pd.km,pd.incentive,pd.del_status,pd.extra_int1,ph.is_confirm as extra_int2,ph.plan_date as extra_var1,pd.extra_var2 from t_route_plan_header ph," + "t_route_plan_detail pd where plan_date between :fromDate and :toDate and pd.plan_head_id=ph.plan_head_id  order by pd.driver_id asc,extra_var1 asc  ", nativeQuery = true)
public List<RoutePlanDetail> getListForNotification(String fromDate,String toDate)


@Transactional
@Modifying
@Query("update RoutePlanDetail set route_id=:routeId,isoffday_isff=:isFF,route_name=:rountName,fr_name=:frName,fr_ids=:frId,type_id=:typeId,km=:km,incentive=:incentive" + "  WHERE plan_detail_id=:planDetailId")
public int updateRouteId(int planDetailId,int isFF,int routeId,String rountName,String frName,String frId,int typeId,int km,float incentive)


@Transactional
@Modifying
@Query("update RoutePlanDetail set route_name=:rountName,incentive=:incentive,fr_name=:frNameChange,km=:kmChange WHERE plan_detail_id=:planDetailId")
public int updateRouteName(int planDetailId,String rountName,float incentive,String frNameChange,int kmChange)


}