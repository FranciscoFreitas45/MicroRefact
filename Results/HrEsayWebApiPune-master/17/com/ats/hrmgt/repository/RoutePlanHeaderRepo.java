import com.ats.hrmgt.model.RoutePlanHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface RoutePlanHeaderRepo extends JpaRepository<RoutePlanHeader, Integer> {


@Query(value = "select * from t_route_plan_header where plan_date=:date", nativeQuery = true)
public RoutePlanHeader getdateexitrecord(String date)


}