import com.ats.hrmgt.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface LocationRepository extends JpaRepository<Location, Integer> {


@Transactional
@Modifying
@Query("update Location set del_status=0  WHERE loc_id=:locId")
public int deleteLocation(int locId)


public Location findByLocHrContactNumberAndDelStatus(String mobileNo,int i)


public List<Location> findByDelStatus(int i)


public List<Location> findByDelStatusAndCompId(int i,int companyId)


public Location findByLocIdAndDelStatus(int locId,int i)


@Query(value = "  select " + "        * \n" + "    from " + "        m_location " + "    where " + "        loc_id IN (:locIds) ", nativeQuery = true)
public List<Location> getLocationsByIds(List<Location> locIds)


public List<Location> findByDelStatusAndCompIdOrderByLocIdDesc(int i,int companyId)


public List<Location> findByDelStatusAndIsActiveAndLocIdIn(int i,int j,List<Integer> locIds)


public Location findByLocHrContactEmailAndDelStatus(String email,int i)


}