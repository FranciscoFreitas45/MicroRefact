import com.ats.hrmgt.model.GetLeaveEncashDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface GetLeaveEncashDetailRepository extends JpaRepository<GetLeaveEncashDetail, Integer> {


@Query(value = "select c.*,lt.lv_title from t_encash c,leave_type lt where c.del_status=1 and " + "lt.lv_type_id=c.lv_type_id and c.year_id=:yearId and c.emp_id=:empId order by id desc", nativeQuery = true)
public List<GetLeaveEncashDetail> getLeaveEncashDetailByEmpId(int empId,int yearId)


}