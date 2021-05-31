import com.ats.hrmgt.model.dashboard.LoanAdvDashDet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface LoanAdvDashDetDashRepo extends JpaRepository<LoanAdvDashDet, String> {


@Query(value = " SELECT\n" + "        UUID() AS uni_key,\n" + "        (     SELECT\n" + "            COUNT(DISTINCT tbl_loan_main.emp_id)              \n" + "        FROM\n" + "            tbl_loan_main,\n" + "            m_employees e           \n" + "        WHERE\n" + "            :currDate BETWEEN tbl_loan_main.loan_repay_start AND tbl_loan_main.loan_repay_end              \n" + "            AND tbl_loan_main.del_status = 1              \n" + "            AND tbl_loan_main.skip_id = 0              \n" + "            AND tbl_loan_main.current_outstanding > 0 \n" + "            and e.emp_id=tbl_loan_main.emp_id \n" + "            and e.location_id=:locId) AS emp,\n" + "        0 AS skip_id,\n" + "        ROUND(ifnull((     SELECT\n" + "            SUM(             CASE                  \n" + "                WHEN advance.current_outstanding < advance.loan_emi_intrest THEN advance.current_outstanding                  \n" + "                ELSE advance.loan_emi_intrest                      \n" + "            END )          \n" + "        FROM\n" + "            tbl_loan_main advance ,\n" + "            m_employees e         \n" + "        WHERE\n" + "            :currDate BETWEEN advance.loan_repay_start AND advance.loan_repay_end              \n" + "            AND advance.del_status = 1              \n" + "            AND advance.skip_id = 0              \n" + "            AND advance.current_outstanding > 0 \n" + "            and e.emp_id=advance.emp_id \n" + "            and e.location_id=:locId),0),0) AS adv_tot,\n" + "        0 AS skip_tott", nativeQuery = true)
public LoanAdvDashDet getLoanDetails(String currDate,int locId)


@Query(value = "SELECT\n" + "        UUID() AS uni_key,\n" + "        COUNT(DISTINCT adv.emp_id) AS emp, 0 AS skip_id, 0 AS skip_tott, ROUND(ifnull(SUM(adv.adv_amount),0),2) AS adv_tot \n" + "    FROM\n" + "        tbl_advance adv,m_employees e \n" + "    WHERE\n" + "        adv.ded_month = :month \n" + "        AND adv.ded_year = :year \n" + "        AND adv.del_status = 1 and e.emp_id=adv.emp_id and e.location_id=:locId", nativeQuery = true)
public LoanAdvDashDet getAdvnceDetails(String year,String month,int locId)


}