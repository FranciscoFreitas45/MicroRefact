import com.ats.hrmgt.model.report.GetSalaryCalcReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetSalaryCalcReportRepo extends JpaRepository<GetSalaryCalcReport, Integer> {


@Query(value = " SELECT\n" + "    salc.*,\n" + "    CONCAT(\n" + "        emp.first_name,\n" + "        ' ',\n" + "        emp.middle_name,\n" + "        ' ',\n" + "        emp.surname\n" + "    ) AS emp_name,\n" + "    emp.pf_no as company_name,\n" + "    emp.uan as name_sd\n" + "FROM\n" + "    tbl_salary_calc salc\n" + "INNER JOIN m_employees emp ON\n" + "    salc.emp_id = emp.emp_id and emp.location_id=:locId\n" + "LEFT JOIN tbl_mst_sub_company subcomp ON\n" + "    salc.cmp_id = subcomp.company_id \n" + "WHERE\n" + "    salc.pf_status = 1 " + " and date_format(concat(salc.calc_year,'-',salc.calc_month,'-01'),'%Y-%m-%d') between concat(:fromYear,'-',:fromMonth,'-01') and concat(:toYear,'-',:toMonth,'-01')", nativeQuery = true)
public List<GetSalaryCalcReport> getSpecEmpPfForReport(String fromYear,String fromMonth,String toYear,String toMonth,int locId)


@Query(value = "\n" + "\n" + "SELECT\n" + "    salc.*,\n" + "    CONCAT(\n" + "        emp.first_name,\n" + "        ' ',\n" + "        emp.middle_name,\n" + "        ' ',\n" + "        emp.surname\n" + "    ) AS emp_name,\n" + "    company_name,\n" + "    name_sd\n" + "FROM\n" + "    tbl_salary_calc salc\n" + "INNER JOIN m_employees emp ON\n" + "    salc.emp_id = emp.emp_id and emp.location_id=:locId\n" + "INNER JOIN tbl_mst_sub_company subcomp ON\n" + "    salc.cmp_id = subcomp.company_id AND salc.cmp_id =:companyId\n" + "WHERE\n" + "    salc.pt_applicable ='yes' AND(\n" + "        salc.calc_month >=:fromMonth AND salc.calc_year =:fromYear \n" + "    ) OR(\n" + "        salc.calc_month <=:toMonth AND salc.calc_year =:toYear \n" + "    )", nativeQuery = true)
public List<GetSalaryCalcReport> getSpecEmpPTForReportComp(int companyId,String fromYear,String fromMonth,String toYear,String toMonth,int locId)


@Query(value = " SELECT\n" + "    salc.*,\n" + "    CONCAT(\n" + "        emp.first_name,\n" + "        ' ',\n" + "        emp.middle_name,\n" + "        ' ',\n" + "        emp.surname\n" + "    ) AS emp_name,\n" + "    emp.pf_no as company_name,\n" + "    emp.uan as name_sd\n" + "FROM\n" + "    t_arear_header salc\n" + "INNER JOIN m_employees emp ON\n" + "    salc.emp_id = emp.emp_id and emp.location_id=:locId\n" + "LEFT JOIN tbl_mst_sub_company subcomp ON\n" + "    salc.cmp_id = subcomp.company_id \n" + "WHERE\n" + "    salc.pf_status = 1 " + " and date_format(concat(salc.calc_year,'-',salc.calc_month,'-01'),'%Y-%m-%d') between concat(:fromYear,'-',:fromMonth,'-01') and concat(:toYear,'-',:toMonth,'-01')", nativeQuery = true)
public List<GetSalaryCalcReport> getArrearsSpecEmpPfForReport(String fromYear,String fromMonth,String toYear,String toMonth,int locId)


@Query(value = "\n" + "\n" + "SELECT\n" + "    salc.*,\n" + "    CONCAT(\n" + "        emp.first_name,\n" + "        ' ',\n" + "        emp.middle_name,\n" + "        ' ',\n" + "        emp.surname\n" + "    ) AS emp_name,\n" + "    emp.pf_no as company_name,\n" + "    emp.uan as name_sd\n" + "FROM\n" + "    tbl_salary_calc salc\n" + "INNER JOIN m_employees emp ON\n" + "    salc.emp_id = emp.emp_id and emp.location_id=:locId\n" + "INNER JOIN tbl_mst_sub_company subcomp ON\n" + "    salc.cmp_id = subcomp.company_id AND salc.cmp_id =:companyId\n" + "WHERE\n" + "    salc.pf_status = 1 " + "and date_format(concat(salc.calc_year,'-',salc.calc_month,'-01'),'%Y-%m-%d') between concat(:fromYear,'-',:fromMonth,'-01') and concat(:toYear,'-',:toMonth,'-01')", nativeQuery = true)
public List<GetSalaryCalcReport> getSpecEmpPfForReportComp(int companyId,String fromYear,String fromMonth,String toYear,String toMonth,int locId)


@Query(value = "SELECT\n" + "        salc.*,\n" + "        CONCAT(         emp.first_name,\n" + "        ' ',\n" + "        emp.middle_name,\n" + "        ' ',\n" + "        emp.surname     ) AS emp_name,\n" + "        inf.dob AS company_name,\n" + "        dep.name as name_sd \n" + "    FROM\n" + "        tbl_salary_calc salc \n" + "    LEFT JOIN\n" + "        tbl_emp_info inf \n" + "            ON     inf.emp_id = salc.emp_id  \n" + "    INNER JOIN\n" + "        m_employees emp \n" + "            ON     salc.emp_id = emp.emp_id \n" + "            AND salc.cmp_id=:companyId\n" + "            AND emp.location_id=:locId  \n" + "    INNER JOIN\n" + "        m_department dep \n" + "            ON     dep.depart_id = emp.depart_id  \n" + "    WHERE\n" + "        salc.mlwf > 0 \n" + "        AND ((\n" + "            salc.calc_month >=:fromMonth\n" + "            AND salc.calc_year =:fromYear      \n" + "        ) \n" + "        OR(\n" + "            salc.calc_month <=:toMonth \n" + "            AND salc.calc_year =:toYear      \n" + "        )) \n" + "    order by name_sd,emp_name", nativeQuery = true)
public List<GetSalaryCalcReport> getMlwfRep(String fromYear,String fromMonth,String toYear,String toMonth,int companyId,int locId)


@Query(value = "SELECT\n" + "    salc.*,\n" + "    CONCAT(\n" + "        emp.first_name,\n" + "        ' ',\n" + "        emp.middle_name,\n" + "        ' ',\n" + "        emp.surname\n" + "    ) AS emp_name,\n" + "    inf.dob AS company_name,\n" + "    inf.middle_name name_sd\n" + "FROM\n" + "    tbl_salary_calc salc\n" + "LEFT JOIN tbl_emp_info inf ON\n" + "    inf.emp_id = salc.emp_id AND inf.middle_name_relation = 'father'\n" + "INNER JOIN m_employees emp ON\n" + "    salc.emp_id = emp.emp_id AND emp.location_id=:locId \n" + "WHERE\n" + "   salc.mlwf > 0 AND  (\n" + "        salc.calc_month >=:fromMonth AND salc.calc_year =:fromYear \n" + "    ) OR(\n" + "        salc.calc_month <=:toMonth AND salc.calc_year =:toYear \n" + "    ) ", nativeQuery = true)
public List<GetSalaryCalcReport> getMlwfRepAllCmp(String fromYear,String fromMonth,String toYear,String toMonth,int locId)


@Query(value = "SELECT\n" + "    salc.*,\n" + "    CONCAT(\n" + "        emp.first_name,\n" + "        ' ',\n" + "        emp.middle_name,\n" + "        ' ',\n" + "        emp.surname\n" + "    ) AS emp_name,\n" + "    emp.pf_no as company_name,\n" + "    emp.uan as name_sd\n" + "FROM\n" + "    tbl_salary_calc salc\n" + "INNER JOIN m_employees emp ON\n" + "    salc.emp_id = emp.emp_id\n" + "LEFT JOIN tbl_mst_sub_company subcomp ON\n" + "    salc.cmp_id = subcomp.company_id \n" + "WHERE\n" + "    salc.pf_status = 1 AND  salc.emp_id=:empId  AND (\n" + "        salc.calc_month >= :fromMonth AND salc.calc_year = :fromYear\n" + "    ) OR(\n" + "        salc.calc_month <= :toMonth AND salc.calc_year = :toYear\n" + "    )  ", nativeQuery = true)
public List<GetSalaryCalcReport> getSpecEmpPfStat(String fromYear,String fromMonth,String toYear,String toMonth,int empId)


@Query(value = "\n" + "\n" + "SELECT\n" + "    salc.*,\n" + "    CONCAT(\n" + "        emp.first_name,\n" + "        ' ',\n" + "        emp.middle_name,\n" + "        ' ',\n" + "        emp.surname\n" + "    ) AS emp_name,\n" + "    emp.pf_no as company_name,\n" + "    emp.uan as name_sd\n" + "FROM\n" + "    t_arear_header salc\n" + "INNER JOIN m_employees emp ON\n" + "    salc.emp_id = emp.emp_id and emp.location_id=:locId\n" + "INNER JOIN tbl_mst_sub_company subcomp ON\n" + "    salc.cmp_id = subcomp.company_id AND salc.cmp_id =:companyId\n" + "WHERE\n" + "    salc.pf_status = 1 " + "and date_format(concat(salc.calc_year,'-',salc.calc_month,'-01'),'%Y-%m-%d') between concat(:fromYear,'-',:fromMonth,'-01') and concat(:toYear,'-',:toMonth,'-01')", nativeQuery = true)
public List<GetSalaryCalcReport> getArrearsSpecEmpPfForReportComp(int companyId,String fromYear,String fromMonth,String toYear,String toMonth,int locId)


}