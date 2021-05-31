import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface RouteListRepo extends JpaRepository<RouteList, Integer> {


public List<RouteList> findByDelStatus(int i)


@Query(value = "select * from m_route where id=:id", nativeQuery = true)
public RouteList findByRouteId(int id)


}