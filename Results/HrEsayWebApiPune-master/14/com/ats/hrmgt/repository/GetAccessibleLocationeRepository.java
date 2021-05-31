import com.ats.hrmgt.model.GetAccessibleLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface GetAccessibleLocationeRepository extends JpaRepository<GetAccessibleLocation, Integer> {


@Query(value = "SELECT\n" + "        e.emp_id, \n" + "        u.loc_id as accessible_loc,\n" + "        e.location_id as present_loc\n" + "    from\n" + "        m_employees e,\n" + "        m_user u    \n" + "    where\n" + "        e.emp_id=u.emp_id   \n" + "        and e.emp_id=:empId", nativeQuery = true)
public GetAccessibleLocation getAccessibleLocationAndPresentLocation(int empId)


}