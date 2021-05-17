import com.ats.hrmgt.model.WeeklyOffShit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface WeeklyOffShitRepository extends JpaRepository<WeeklyOffShit, Integer> {


@Query(value = "select * from tbl_weekoffshift where weekoffshiftdate between :fromDate and :toDate AND del_status=1 and emp_id=:empId", nativeQuery = true)
public List<WeeklyOffShit> getWeeklyOffShitListbetweenweekoffondatebyempId(String fromDate,String toDate,int empId)


@Query(value = "select * from tbl_weekoffshift where weekofffromdate between :fromDate and :toDate AND del_status=1 ", nativeQuery = true)
public List<WeeklyOffShit> weeklyOffShitFromList(String fromDate,String toDate)


@Query(value = "select * from tbl_weekoffshift where weekoffshiftdate between :fromDate and :toDate AND del_status=1 ", nativeQuery = true)
public List<WeeklyOffShit> getWeeklyOffShitList(String fromDate,String toDate)


@Query(value = "select * from tbl_weekoffshift where tbl_weekoffshift.month=:month AND tbl_weekoffshift.year=:year AND tbl_weekoffshift.location_id=:locId AND del_status=1 ", nativeQuery = true)
public List<WeeklyOffShit> getRecord(int month,int year,int locId)


@Query(value = "select * from tbl_weekoffshift where weekofffromdate between :fromDate and :toDate AND del_status=1 and emp_id=:empId", nativeQuery = true)
public List<WeeklyOffShit> getWeeklyOffShitListbetweenweekofffromdatebyempId(String fromDate,String toDate,int empId)


@Query(value = "select * from tbl_weekoffshift where id=:id ", nativeQuery = true)
public WeeklyOffShit shiftWeeklyofById(int id)


}