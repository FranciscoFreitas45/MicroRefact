import com.ats.hrmgt.model.EmpSalaryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface EmpSalaryInfoRepo extends JpaRepository<EmpSalaryInfo, Integer> {


@Transactional
@Modifying
@Query(value = "UPDATE `tbl_emp_salary_info` SET del_status=0 WHERE emp_id=:empId", nativeQuery = true)
public int deleteEmpSalInfo(int empId)


@Transactional
@Modifying
@Query(value = "UPDATE tbl_emp_salary_info SET cmp_leaving_date=:leaveDate,leaving_reason=:leaveReason,leaving_reason_esic=:lrEsic," + "leaving_reason_pf=:lrForPF WHERE emp_id=:empId", nativeQuery = true)
public int updateLeaveDatainemployee(int empId,String leaveDate,String leaveReason,String lrEsic,String lrForPF)


@Query(value = "select * from tbl_emp_salary_info  where emp_id in (:empIds)", nativeQuery = true)
public List<EmpSalaryInfo> getSalaryInfoList(List<Integer> empIds)


public EmpSalaryInfo findByEmpIdAndDelStatus(int empId,int del)


@Transactional
@Modifying
@Query(value = "UPDATE tbl_emp_salary_info SET  salary_type_id  =:shiftId WHERE emp_id IN(:empIdList)", nativeQuery = true)
public int assignsalStruct(List<Integer> empIdList,String shiftId)


}