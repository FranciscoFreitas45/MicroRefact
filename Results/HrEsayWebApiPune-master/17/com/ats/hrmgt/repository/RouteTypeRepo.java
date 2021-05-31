import com.ats.hrmgt.model.RouteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface RouteTypeRepo extends JpaRepository<RouteType, Integer> {


public List<RouteType> findByDelStatus(int i)


public RouteType findByTypeId(int routeId)


@Transactional
@Modifying
@Query("update RouteType set del_status=0  WHERE type_id=:typeId")
public int deleteRouteType(int typeId)


}