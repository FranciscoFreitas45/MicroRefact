import com.ats.hrmgt.model.TblEmpInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
@Repository
public interface TblEmpInfoRepo extends JpaRepository<TblEmpInfo, Integer> {


@Transactional
@Modifying
@Query(value = "UPDATE `tbl_emp_info` SET del_status=0 WHERE emp_id=:empId", nativeQuery = true)
public int deleteEmployeeInfo(int empId)


public TblEmpInfo findByEmpIdAndDelStatus(int empId,int del)


}