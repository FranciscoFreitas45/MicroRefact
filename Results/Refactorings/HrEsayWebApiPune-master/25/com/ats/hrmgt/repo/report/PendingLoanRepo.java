import com.ats.hrmgt.model.report.PendingLoanReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface PendingLoanRepo extends JpaRepository<PendingLoanReport, Integer> {


@Query(value = "SELECT \n" + "	 UUID() as id, emp.emp_code,\n" + " 	 emp.first_name,\n" + "    emp.middle_name,\n" + "    emp.surname,\n" + "    desig.name AS designation,\n" + "    dept.name AS depatarment,\n" + "	 loan.loan_amt,\n" + "    loan.loan_emi,\n" + "    loan.current_totpaid,\n" + "    loan.current_outstanding,   \n" + "    loan.loan_repay_start,\n" + "    loan.loan_repay_end\n" + "	\n" + "FROM \n" + "	 tbl_loan_main loan,\n" + "	 m_employees emp,\n" + "    m_designation desig,\n" + "    m_department dept\n" + "    \n" + "WHERE	\n" + "	 emp.emp_id = loan.emp_id AND\n" + "    emp.designation_id=desig.desig_id AND\n" + "    emp.depart_id = dept.depart_id AND loan.loan_status LIKE 'Active' AND\n" + "    loan.del_status=1 AND emp.del_status=1 and emp.location_id=:locId\n" + "    ORDER BY loan.loan_repay_start DESC", nativeQuery = true)
public List<PendingLoanReport> getEmpPendingLoanDetails(int locId)


}