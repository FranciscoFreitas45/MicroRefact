import com.ats.hrmgt.model.BankTrasferReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface BankTrasferReportRepo extends JpaRepository<BankTrasferReport, Integer> {


@Query(value = "select a.*,b.name as bank_name from (select\n" + "        e.emp_id,\n" + "        concat(e.first_name,\n" + "        ' ',\n" + "        e.surname, ' (',e.emp_code,')') as name,\n" + "        et.name as emp_type_name, \n" + "        d.name as depart_name,\n" + "        dg.name as design_name,\n" + "        sc.net_salary,\n" + "        bi.acc_no,\n" + "        bi.bank_id\n" + "    from\n" + "        tbl_salary_calc sc,\n" + "        m_employees e,\n" + "        tbl_mst_emp_types et,\n" + "        m_department d,\n" + "        m_designation dg,  \n" + "        tbl_emp_bank_info bi     \n" + "    where\n" + "        sc.calc_month=:month                   \n" + "        and calc_year=:year                   \n" + "        and e.emp_id=sc.emp_id                   \n" + "        and et.emp_type_id=sc.emp_type                   \n" + "        and d.depart_id=sc.depart_id                   \n" + "        and dg.desig_id=e.designation_id   \n" + "        and e.location_id in (:locId)          \n" + "        and bi.emp_id=e.emp_id and bi.bank_id=:bankId     \n" + "    order by\n" + "        e.emp_id asc) a\n" + "left join(\n" + "select * from m_bank\n" + ")b on b.bank_id=a.bank_id\n" + "order by a.bank_id desc", nativeQuery = true)
public List<BankTrasferReport> getBankTransferReport(int month,int year,List<Integer> locId,int bankId)


}