import com.ats.hrmgt.model.CountOfAssignPending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface CountOfAssignPendingRepository extends JpaRepository<CountOfAssignPending, Integer> {


@Query(value = "select\n" + "        uuid() as id,\n" + "        coalesce((select\n" + "            count('') \n" + "        from\n" + "            m_employees \n" + "        where\n" + "            emp_type=0 \n" + "            and del_status=1 and location_id in (:locationId)),\n" + "        0) as emp_type_count,\n" + "        coalesce(0) as shift_count,\n" + "        coalesce((select\n" + "            count('') \n" + "        from\n" + "            m_employees \n" + "        where\n" + "            location_id=0 \n" + "            and del_status=1 and location_id in (:locationId)),\n" + "        0) as location_count,\n" + "        coalesce((select\n" + "            count('') \n" + "        from\n" + "            m_employees \n" + "        where\n" + "            holiday_category=0 \n" + "            and del_status=1 and location_id in (:locationId)),\n" + "        0) as holiday_count,\n" + "        coalesce((select\n" + "            count('') \n" + "        from\n" + "            m_employees \n" + "        where\n" + "            weekend_category=0 \n" + "            and del_status=1 and location_id in (:locationId)),\n" + "        0) as weekend_count", nativeQuery = true)
public CountOfAssignPending getCountOfAssignForAttendance(int locationId)


}