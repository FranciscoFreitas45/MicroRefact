import com.ats.hrmgt.model.bonus.PayBonusDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;
public interface PayBonusDetailsRepo extends JpaRepository<PayBonusDetails, Integer> {


@Transactional
@Modifying
@Query("update PayBonusDetails set del_status=0  WHERE pay_id=:bonusId")
public int deleteBonusPay(int bonusId)


@Transactional
@Modifying
@Query(value = "update tblm_pay_bonus_details set is_paid=1 where month=:month and year=:year and del_status=1  and is_paid=0 and emp_id in (:empIds) ", nativeQuery = true)
public int updateBonus(int month,int year,List<Integer> empIds)


public List<PayBonusDetails> findByDelStatus(int i)


@Query(value = "SELECT\n" + "        tblm_pay_bonus_details.pay_id,\n" + "        tblm_pay_bonus_details.emp_id,\n" + "        tblm_pay_bonus_details.cmp_id,\n" + "        tblm_pay_bonus_details.pay_type_id,\n" + "        tblm_pay_bonus_details.pay_rate,\n" + "        tblm_pay_bonus_details.pay_occurence,\n" + "        tblm_pay_bonus_details.pay_total,\n" + "        tblm_pay_bonus_details.pay_remark,\n" + "        CONCAT(         m_employees.first_name,\n" + "        ' ',\n" + "        m_employees.middle_name,\n" + "        ' ',\n" + "        m_employees.surname     ) AS pay_login_name,\n" + "        tblm_pay_bonus_details.pay_login_date_time,\n" + "        tblm_pay_bonus_details.pay_approve_by,\n" + "        tbl_pay_bonus.type_name AS pay_approval_remark,\n" + "        tblm_pay_bonus_details.pay_approval_datetime,\n" + "        tblm_pay_bonus_details.is_paid,\n" + "        tblm_pay_bonus_details.final_status,\n" + "        tblm_pay_bonus_details.month,\n" + "        tblm_pay_bonus_details.year,\n" + "        tblm_pay_bonus_details.del_status,\n" + "        tblm_pay_bonus_details.maker_enter_datetime,\n" + "        tblm_pay_bonus_details.ex_int1,\n" + "        tblm_pay_bonus_details.ex_int2,\n" + "        tblm_pay_bonus_details.ex_var1,\n" + "        tblm_pay_bonus_details.ex_var2 \n" + "    FROM\n" + "        m_employees,\n" + "        tblm_pay_bonus_details,\n" + "        tbl_pay_bonus \n" + "    WHERE\n" + "        tbl_pay_bonus.pay_type_id = tblm_pay_bonus_details.pay_type_id \n" + "        AND m_employees.emp_id = tblm_pay_bonus_details.emp_id \n" + "        AND tblm_pay_bonus_details.del_status = 1 \n" + "        and m_employees.emp_id=:empId", nativeQuery = true)
public List<PayBonusDetails> getAllPayPedingDetailsByEmpId(int empId)


@Query(value = " SELECT\n" + "    tblm_pay_bonus_details.pay_id,\n" + "    tblm_pay_bonus_details.emp_id,\n" + "    tblm_pay_bonus_details.cmp_id,\n" + "    tblm_pay_bonus_details.pay_type_id,\n" + "    tblm_pay_bonus_details.pay_rate,\n" + "    tblm_pay_bonus_details.pay_occurence,\n" + "    tblm_pay_bonus_details.pay_total,\n" + "    tblm_pay_bonus_details.pay_remark,\n" + "    CONCAT(\n" + "        m_employees.first_name,\n" + "        ' ',\n" + "        m_employees.middle_name,\n" + "        ' ',\n" + "        m_employees.surname\n" + "    ) AS pay_login_name,\n" + "    tblm_pay_bonus_details.pay_login_date_time,\n" + "   tblm_pay_bonus_details.pay_approve_by,\n" + "    tbl_pay_bonus.type_name AS pay_approval_remark,\n" + "    tblm_pay_bonus_details.pay_approval_datetime,\n" + "    tblm_pay_bonus_details.is_paid,\n" + "    tblm_pay_bonus_details.final_status,\n" + "    tblm_pay_bonus_details.month,\n" + "    tblm_pay_bonus_details.year,\n" + "    tblm_pay_bonus_details.del_status,\n" + "    tblm_pay_bonus_details.maker_enter_datetime,\n" + "    tblm_pay_bonus_details.ex_int1,\n" + "    tblm_pay_bonus_details.ex_int2,\n" + "    tblm_pay_bonus_details.ex_var1,\n" + "    tblm_pay_bonus_details.ex_var2\n" + "FROM\n" + "    m_employees,\n" + "    tblm_pay_bonus_details,\n" + "    tbl_pay_bonus\n" + "WHERE\n" + "    tbl_pay_bonus.pay_type_id = tblm_pay_bonus_details.pay_type_id AND m_employees.emp_id = tblm_pay_bonus_details.emp_id AND tblm_pay_bonus_details.del_status = 1 AND tblm_pay_bonus_details.is_paid = 0", nativeQuery = true)
public List<PayBonusDetails> getAllUnpaid()


public PayBonusDetails findByPayIdAndDelStatus(int payDetId,int i)


}