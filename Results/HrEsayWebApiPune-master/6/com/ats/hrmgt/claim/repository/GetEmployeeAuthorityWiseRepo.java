import com.ats.hrmgt.model.claim.GetEmployeeAuthorityWise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetEmployeeAuthorityWiseRepo extends JpaRepository<GetEmployeeAuthorityWise, Integer> {


@Query(value = " SELECT l.emp_id FROM leave_authority l WHERE l.fin_auth_emp_id=:empId", nativeQuery = true)
public List<GetEmployeeAuthorityWise> getEmpIdListForFinalAuth(int empId)


@Query(value = " SELECT l.emp_id FROM leave_authority l WHERE l.ini_auth_emp_id=:empId OR l.fin_auth_emp_id=:empId", nativeQuery = true)
public List<GetEmployeeAuthorityWise> getEmpIdList(int empId)


@Query(value = " SELECT ca.emp_id FROM claim_authority ca WHERE l.ca_fin_auth_emp_id=:empId", nativeQuery = true)
public List<GetEmployeeAuthorityWise> getClaimIdListForFinalAuth(int empId)


@Query(value = " SELECT ca.emp_id FROM claim_authority ca WHERE ca.ca_ini_auth_emp_id=:empId ", nativeQuery = true)
public List<GetEmployeeAuthorityWise> getClaimIdListForInitialAuth(int empId)


@Query(value = " SELECT l.emp_id FROM leave_authority l WHERE l.ini_auth_emp_id=:empId ", nativeQuery = true)
public List<GetEmployeeAuthorityWise> getEmpIdListForInitialAuth(int empId)


@Query(value = " SELECT l.emp_id FROM claim_authority l WHERE l.ca_ini_auth_emp_id =:empId OR l.ca_fin_auth_emp_id=:empId", nativeQuery = true)
public List<GetEmployeeAuthorityWise> getEmpIdListInClaimAuth(int empId)


}