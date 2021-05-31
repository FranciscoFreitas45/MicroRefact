import com.ats.hrmgt.model.TblEmpBankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
public interface TblEmpBankInfoRepo extends JpaRepository<TblEmpBankInfo, Integer> {


public List<TblEmpBankInfo> findByBankIdAndDelStatus(int bankId,int i)


@Transactional
@Modifying
@Query(value = "UPDATE `tbl_emp_bank_info` SET del_status=0 WHERE emp_id=:empId", nativeQuery = true)
public int deleteEmpBankInfo(int empId)


public TblEmpBankInfo findByEmpIdAndDelStatus(int empId,int del)


}