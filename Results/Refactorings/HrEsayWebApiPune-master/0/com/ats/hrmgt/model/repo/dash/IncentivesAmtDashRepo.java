import com.ats.hrmgt.model.dashboard.IncentivesAmtDash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface IncentivesAmtDashRepo extends JpaRepository<IncentivesAmtDash, String> {


@Query(value = "SELECT UUID() as uni_key,ifnull(b.perf_incentive,0) as perf_incentive,\n" + "        ifnull(c.prod_incentive,0) as prod_incentive\n" + "    FROM\n" + "        (select emp_id from m_employees where emp_id=:empId) emp\n" + "        \n" + "        LEFT JOIN\n" + "        (\n" + "            SELECT\n" + "                emp_id,\n" + "                SUM(tbl_advance.adv_amount) AS perf_incentive              \n" + "            FROM\n" + "                tbl_advance            \n" + "            where\n" + "                emp_id=:empId\n" + "                and is_ded=0\n" + "        ) b                           \n" + "            on emp.emp_id=b.emp_id \n" + "         LEFT JOIN\n" + "        (\n" + "            SELECT\n" + "                emp_id,\n" + "                SUM(tbl_loan_main.current_outstanding) AS prod_incentive             \n" + "            FROM\n" + "                tbl_loan_main            \n" + "            where\n" + "                tbl_loan_main.emp_id = :empId \n" + "        AND tbl_loan_main.del_status = 1 \n" + "        ) c                           \n" + "            on emp.emp_id=c.emp_id \n" + "     ", nativeQuery = true)
public IncentivesAmtDash getWeekBirth(int empId)


}