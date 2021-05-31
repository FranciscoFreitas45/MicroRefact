import com.ats.hrmgt.model.PayDeductionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface PayDeductionDetailsRepo extends JpaRepository<PayDeductionDetails, Integer> {


@Query(value = "SELECT\n" + "    tblm_pay_deduction_details.ded_id,\n" + "    tblm_pay_deduction_details.emp_id,\n" + "    tblm_pay_deduction_details.cmp_id,\n" + "    tblm_pay_deduction_details.ded_type_id,\n" + "    tblm_pay_deduction_details.ded_rate,\n" + "    tblm_pay_deduction_details.ded_occurence,\n" + "    tblm_pay_deduction_details.ded_total,\n" + "    tblm_pay_deduction_details.ded_remark,\n" + "    tblm_pay_deduction_details.ded_login_name,\n" + "    tblm_pay_deduction_details.ded_login_date_time,\n" + "    tblm_pay_deduction_details.ded_approved_by,\n" + "    tblm_pay_deduction_details.ded_approval_remark,\n" + "    tblm_pay_deduction_details.ded_approval_datetime,\n" + "    tblm_pay_deduction_details.is_deducted,\n" + "    tblm_pay_deduction_details.final_status,\n" + "    tblm_pay_deduction_details.month,\n" + "    tblm_pay_deduction_details.year,\n" + "    tblm_pay_deduction_details.del_status,\n" + "    m_employees.emp_code AS maker_enter_datetime,\n" + "    tblm_pay_deduction_details.ex_int1,\n" + "    tblm_pay_deduction_details.ex_int2,\n" + "    CONCAT(\n" + "        m_employees.first_name,\n" + "        ' ',\n" + "        m_employees.middle_name,\n" + "        ' ',\n" + "        m_employees.surname\n" + "    ) AS ex_var1,\n" + "    tbl_pay_deduction.type_name AS ex_var2\n" + "FROM\n" + "    tbl_pay_deduction,\n" + "    m_employees,\n" + "    tblm_pay_deduction_details\n" + "WHERE\n" + "    m_employees.emp_id = tblm_pay_deduction_details.emp_id AND tblm_pay_deduction_details.ded_type_id = tbl_pay_deduction.ded_type_id AND tblm_pay_deduction_details.del_status = 1 AND DATE_FORMAT(\n" + "        CONCAT(\n" + "            tblm_pay_deduction_details.year,\n" + "            '-',\n" + "            tblm_pay_deduction_details.month,\n" + "            '-01'\n" + "        ),\n" + "        '%Y-%m-%d'\n" + "    ) >= DATE_FORMAT(\n" + "        CONCAT(:fromYear, '-', :fromMonth, '-01'),\n" + "        '%Y-%m-%d'\n" + "    ) AND DATE_FORMAT(\n" + "        CONCAT(\n" + "            tblm_pay_deduction_details.year,\n" + "            '-',\n" + "            tblm_pay_deduction_details.month,\n" + "            '-01'\n" + "        ),\n" + "        '%Y-%m-%d'\n" + "    ) <= DATE_FORMAT(\n" + "        CONCAT(:toYear, '-', :toMonth, '-01'),\n" + "        '%Y-%m-%d'\n" + "    ) AND tblm_pay_deduction_details.emp_id=:empId AND tblm_pay_deduction_details.is_deducted !=0  ", nativeQuery = true)
public List<PayDeductionDetails> getEmpPayDed(String fromYear,String fromMonth,String toYear,String toMonth,int empId)


@Transactional
@Modifying
@Query(value = "update tblm_pay_deduction_details set is_deducted=1 where month=:month and year=:year and del_status=1  and is_deducted=0 and emp_id in (:empIds) ", nativeQuery = true)
public int updatePayde(int month,int year,List<Integer> empIds)


@Transactional
@Modifying
@Query(value = "UPDATE tblm_pay_deduction_details SET del_status=0 WHERE ded_id=:dedId", nativeQuery = true)
public int deletePayDeductnDetailById(int dedId)


@Query(value = "SELECT\n" + "    tblm_pay_deduction_details.ded_id,\n" + "    tblm_pay_deduction_details.emp_id,\n" + "    tblm_pay_deduction_details.cmp_id,\n" + "    tblm_pay_deduction_details.ded_type_id,\n" + "    tblm_pay_deduction_details.ded_rate,\n" + "    tblm_pay_deduction_details.ded_occurence,\n" + "    tblm_pay_deduction_details.ded_total,\n" + "    tblm_pay_deduction_details.ded_remark,\n" + "    tblm_pay_deduction_details.ded_login_name,\n" + "    tblm_pay_deduction_details.ded_login_date_time,\n" + "    tblm_pay_deduction_details.ded_approved_by,\n" + "    tblm_pay_deduction_details.ded_approval_remark,\n" + "    tblm_pay_deduction_details.ded_approval_datetime,\n" + "    tblm_pay_deduction_details.is_deducted,\n" + "    tblm_pay_deduction_details.final_status,\n" + "    tblm_pay_deduction_details.month,\n" + "    tblm_pay_deduction_details.year,\n" + "    tblm_pay_deduction_details.del_status,\n" + "    m_employees.emp_code AS maker_enter_datetime,\n" + "    tblm_pay_deduction_details.ex_int1,\n" + "    tblm_pay_deduction_details.ex_int2,\n" + "    CONCAT(\n" + "        m_employees.first_name,\n" + "        ' ',\n" + "        m_employees.middle_name,\n" + "        ' ',\n" + "        m_employees.surname\n" + "    ) AS ex_var1,\n" + "    tbl_pay_deduction.type_name AS ex_var2\n" + "FROM\n" + "    tbl_pay_deduction,\n" + "    m_employees,\n" + "    tblm_pay_deduction_details\n" + "WHERE\n" + "    m_employees.emp_id = tblm_pay_deduction_details.emp_id AND tblm_pay_deduction_details.ded_type_id = tbl_pay_deduction.ded_type_id AND tblm_pay_deduction_details.del_status = 1 AND DATE_FORMAT(\n" + "        CONCAT(\n" + "            tblm_pay_deduction_details.year,\n" + "            '-',\n" + "            tblm_pay_deduction_details.month,\n" + "            '-01'\n" + "        ),\n" + "        '%Y-%m-%d'\n" + "    ) >= DATE_FORMAT(\n" + "        CONCAT(:fromYear, '-', :fromMonth, '-01'),\n" + "        '%Y-%m-%d'\n" + "    ) AND DATE_FORMAT(\n" + "        CONCAT(\n" + "            tblm_pay_deduction_details.year,\n" + "            '-',\n" + "            tblm_pay_deduction_details.month,\n" + "            '-01'\n" + "        ),\n" + "        '%Y-%m-%d'\n" + "    ) <= DATE_FORMAT(\n" + "        CONCAT(:toYear, '-', :toMonth, '-01'),\n" + "        '%Y-%m-%d'\n" + "    ) AND  tblm_pay_deduction_details.is_deducted !=0  ", nativeQuery = true)
public List<PayDeductionDetails> getEmpPayDedAllEmp(String fromYear,String fromMonth,String toYear,String toMonth)


}