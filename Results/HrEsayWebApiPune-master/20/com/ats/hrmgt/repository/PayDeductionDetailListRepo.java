import com.ats.hrmgt.model.PayDeductionDetailList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface PayDeductionDetailListRepo extends JpaRepository<PayDeductionDetailList, Integer> {


@Query(value = "SELECT\n" + "        emp.emp_id,\n" + "        payDeductDetail.is_deducted as emp_code,\n" + "        CONCAT(emp.first_name,\n" + "        ' ',\n" + "        emp.middle_name,\n" + "        ' ',\n" + "        emp.surname) AS emp_name,\n" + "        payDeductDetail.ded_id,\n" + "        payDeductDetail.ded_rate,\n" + "        payDeductDetail.ded_remark,\n" + "        payDeductDetail.ded_occurence,\n" + "        payDeductDetail.ded_total,\n" + "        payDeductDetail.month,\n" + "        payDeductDetail.year,\n" + "        payDeductType.type_name,\n" + "        payDeductType.ded_type_id           \n" + "    FROM\n" + "        tblm_pay_deduction_details payDeductDetail,\n" + "        m_employees emp,\n" + "        tbl_pay_deduction payDeductType           \n" + "    WHERE\n" + "        payDeductDetail.emp_id=emp.emp_id                   \n" + "        AND       payDeductDetail.ded_type_id=payDeductType.ded_type_id          \n" + "        AND     payDeductDetail.del_status=1          \n" + "        and emp.emp_id=:empId    \n" + "    Order By\n" + "        payDeductDetail.ded_id Desc", nativeQuery = true)
public List<PayDeductionDetailList> getAllEmpPayDeductDetailByEmpId(int empId)


@Query(value = "SELECT\n" + "	emp.emp_id,emp.emp_code,\n" + "	CONCAT(emp.first_name,' ',emp.middle_name,' ',emp.surname) AS emp_name,\n" + "    payDeductDetail.ded_id,\n" + "    payDeductDetail.ded_rate,  payDeductDetail.ded_remark,\n" + "    payDeductDetail.ded_occurence,\n" + "    payDeductDetail.ded_total,\n" + "    payDeductDetail.month,\n" + "    payDeductDetail.year,    \n" + "    payDeductType.type_name,  payDeductType.ded_type_id\n" + "    \n" + "FROM\n" + "    tblm_pay_deduction_details payDeductDetail,\n" + "    m_employees emp,\n" + "    tbl_pay_deduction payDeductType\n" + "    \n" + "WHERE\n" + "    payDeductDetail.emp_id=emp.emp_id AND\n" + "    payDeductDetail.ded_type_id=payDeductType.ded_type_id AND\n" + "    payDeductDetail.del_status=1 AND\n" + "    payDeductDetail.ded_id=:dedId", nativeQuery = true)
public PayDeductionDetailList getEmpPayDeductionById(int dedId)


@Query(value = "SELECT\n" + "	 emp.emp_id, emp.emp_code, CONCAT(emp.first_name,' ',emp.middle_name,' ',emp.surname) AS emp_name,\n" + "    payDeductDetail.ded_id,\n" + "    payDeductDetail.ded_rate,  payDeductDetail.ded_remark,\n" + "    payDeductDetail.ded_occurence,\n" + "    payDeductDetail.ded_total,\n" + "    payDeductDetail.month,\n" + "    payDeductDetail.year,    \n" + "    payDeductType.type_name,  payDeductType.ded_type_id\n" + "    \n" + "FROM\n" + "    tblm_pay_deduction_details payDeductDetail,\n" + "    m_employees emp,\n" + "    tbl_pay_deduction payDeductType\n" + "    \n" + "WHERE\n" + "    payDeductDetail.emp_id=emp.emp_id AND  payDeductDetail.is_deducted=0 AND  \n" + "    payDeductDetail.ded_type_id=payDeductType.ded_type_id AND\n" + "    payDeductDetail.del_status=1 Order By payDeductDetail.ded_id Desc", nativeQuery = true)
public List<PayDeductionDetailList> getEmpPayDeductDetail()


@Query(value = "SELECT\n" + "	 emp.emp_id, emp.emp_code, CONCAT(emp.first_name,' ',emp.middle_name,' ',emp.surname) AS emp_name,\n" + "    payDeductDetail.ded_id,\n" + "    payDeductDetail.ded_rate,  payDeductDetail.ded_remark,\n" + "    payDeductDetail.ded_occurence,\n" + "    payDeductDetail.ded_total,\n" + "    payDeductDetail.month,\n" + "    payDeductDetail.year,    \n" + "    payDeductType.type_name,  payDeductType.ded_type_id\n" + "    \n" + "FROM\n" + "    tblm_pay_deduction_details payDeductDetail,\n" + "    m_employees emp,\n" + "    tbl_pay_deduction payDeductType\n" + "    \n" + "WHERE\n" + "    payDeductDetail.emp_id=emp.emp_id AND  payDeductDetail.is_deducted=0 AND  \n" + "    payDeductDetail.ded_type_id=payDeductType.ded_type_id AND\n" + "    payDeductDetail.del_status=1 and emp.location_id in (:locId) Order By payDeductDetail.ded_id Desc", nativeQuery = true)
public List<PayDeductionDetailList> getAllEmpPayDeductDetailLocId(List<Integer> locId)


}