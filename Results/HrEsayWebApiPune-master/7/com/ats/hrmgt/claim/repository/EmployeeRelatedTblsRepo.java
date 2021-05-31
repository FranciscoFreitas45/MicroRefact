import com.ats.hrmgt.model.EmployeeRelatedTbls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface EmployeeRelatedTblsRepo extends JpaRepository<EmployeeRelatedTbls, Integer> {


@Query(value = "SELECT\n" + "        emp.emp_id,\n" + "        emp.emp_code,\n" + "        empinfo.emp_info_id,\n" + "        sal.salary_info_id,\n" + "        bank.bank_info_id,\n" + "        nom.nominee_id,\n" + "        GROUP_CONCAT(DISTINCT salall.emp_sal_allowance_id) AS emp_sal_allowance_id,\n" + "        GROUP_CONCAT(salall.allowance_id) AS allowance_id,\n" + "        GROUP_CONCAT(DISTINCT doc.doc_id) AS doc_id,\n" + "        u.user_id \n" + "    FROM\n" + "         m_employees emp \n" + "    LEFT JOIN\n" + "        tbl_emp_salary_info sal \n" + "            ON     emp.emp_id = sal.emp_id \n" + "    LEFT JOIN\n" + "        tbl_emp_info empinfo \n" + "            ON     emp.emp_id = empinfo.emp_id \n" + "    LEFT JOIN\n" + "        tbl_emp_bank_info bank \n" + "            ON     emp.emp_id = bank.emp_id \n" + "    LEFT JOIN\n" + "        tbl_emp_nominees nom \n" + "            ON     emp.emp_id = nom.emp_id \n" + "    LEFT JOIN\n" + "        emp_sal_allowance salall \n" + "            ON     emp.emp_id = salall.emp_id \n" + "    LEFT JOIN\n" + "        emp_docs doc \n" + "            ON     emp.emp_id = doc.emp_id \n" + "    LEFT JOIN\n" + "        m_user u \n" + "            ON     emp.emp_id = u.emp_id \n" + "    WHERE\n" + "        emp.emp_code = :empCode and emp.del_status=1", nativeQuery = true)
public EmployeeRelatedTbls getAllEmpRelatedInfo(String empCode)


}