import com.ats.hrmgt.model.TypeWiseRoasterList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface TypeWiseRoasterListRepository extends JpaRepository<TypeWiseRoasterList, Integer> {


@Query(value = "select\n" + "            UUID() as id,pd.driver_id,pd.type_id,count(*) as type_count  \n" + "        from\n" + "            t_route_plan_detail pd,\n" + "            t_route_plan_header ph \n" + "        where\n" + "            ph.plan_date between :fromDate and :toDate  \n" + "            and ph.plan_head_id=pd.plan_head_id and pd.type_id!=0 and pd.driver_id=:empId\n" + "    group by pd.driver_id,pd.type_id", nativeQuery = true)
public List<TypeWiseRoasterList> getRoasterSummeryDetailByEmpId(String fromDate,String toDate,int empId)


@Query(value = "select\n" + "            UUID() as id,pd.driver_id,pd.type_id,count(*) as type_count  \n" + "        from\n" + "            t_route_plan_detail pd,\n" + "            t_route_plan_header ph \n" + "        where\n" + "            ph.plan_date between :fromDate and :toDate  \n" + "            and ph.plan_head_id=pd.plan_head_id and pd.type_id!=0\n" + "    group by pd.driver_id,pd.type_id", nativeQuery = true)
public List<TypeWiseRoasterList> getRoasterSummeryDetail(String fromDate,String toDate)


}