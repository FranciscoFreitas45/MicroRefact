import com.ats.hrmgt.model.EmpInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface EmpInfoRepository extends JpaRepository<EmpInfo, Integer> {


@Query(value = "SELECT e.*,emp_sal.cmp_joining_date, emp_sal.sal_basis, emp_sal.salary_type_id  FROM m_employees e, tbl_emp_salary_info emp_sal " + "where e.emp_id=emp_sal.emp_id and e.del_status=1", nativeQuery = true)
public List<EmpInfo> getEmpListAll()


@Query(value = "SELECT e.*,emp_sal.cmp_joining_date, emp_sal.sal_basis, emp_sal.salary_type_id  FROM m_employees e, tbl_emp_salary_info emp_sal " + "where e.emp_id=emp_sal.emp_id and e.emp_id not in (select emp_id from tbl_attt_daily_daily  where att_date between :fromDate " + "and :toDate group by emp_id) and e.del_status=1 and (emp_sal.cmp_leaving_date IS NULL or emp_sal.cmp_leaving_date='' or emp_sal.cmp_leaving_date=1970-00-00 or  date_format(emp_sal.cmp_leaving_date,'%Y-%m')>=date_format(:fromDate,'%Y-%m'))", nativeQuery = true)
public List<EmpInfo> getEmpList(String fromDate,String toDate)


@Query(value = " SELECT\n" + "        e.*,\n" + "        emp_sal.cmp_joining_date,\n" + "        emp_sal.sal_basis,\n" + "        emp_sal.salary_type_id  \n" + "    FROM\n" + "        m_employees e,\n" + "        tbl_emp_salary_info emp_sal \n" + "    where\n" + "        e.emp_id=emp_sal.emp_id \n" + "        and e.emp_id not in (\n" + "            select\n" + "                emp_id \n" + "            from\n" + "                t_shift_assign_daily  \n" + "            where\n" + "                shift_date between :fromDate and :toDate \n" + "            group by\n" + "                emp_id\n" + "        ) \n" + "        and e.del_status=1 \n" + "        and (\n" + "            emp_sal.cmp_leaving_date IS NULL \n" + "            or emp_sal.cmp_leaving_date='' \n" + "            or emp_sal.cmp_leaving_date=1970-00-00 \n" + "            or  date_format(emp_sal.cmp_leaving_date,'%Y-%m')>=date_format(:fromDate,'%Y-%m')\n" + "        )", nativeQuery = true)
public List<EmpInfo> getEmpListForAssignShift(String fromDate,String toDate)


@Query(value = "SELECT e.*,emp_sal.cmp_joining_date, emp_sal.sal_basis, emp_sal.salary_type_id  FROM m_employees e, tbl_emp_salary_info emp_sal " + "where e.emp_id=emp_sal.emp_id and e.emp_id not in (select emp_id from tbl_attt_daily_daily  where att_date between :fromDate " + "and :toDate group by emp_id) and e.del_status=1 and (emp_sal.cmp_leaving_date IS NULL or emp_sal.cmp_leaving_date='' or emp_sal.cmp_leaving_date=1970-00-00 or  date_format(emp_sal.cmp_leaving_date,'%Y-%m')>=date_format(:fromDate,'%Y-%m')) and e.location_id in (:locId)", nativeQuery = true)
public List<EmpInfo> getEmpListLocId(String fromDate,String toDate,List<Integer> locId)


@Query(value = "SELECT\n" + "        e.*,\n" + "        emp_sal.cmp_joining_date,\n" + "        emp_sal.sal_basis,\n" + "        emp_sal.salary_type_id  \n" + "    FROM\n" + "        m_employees e,\n" + "        tbl_emp_salary_info emp_sal,\n" + "        leave_authority la\n" + "    where\n" + "        e.emp_id=emp_sal.emp_id \n" + "        and e.del_status=1 and la.emp_id=e.emp_id           \n" + "        and la.ini_auth_emp_id=:userId \n" + "        and (\n" + "            emp_sal.cmp_leaving_date IS NULL \n" + "            or emp_sal.cmp_leaving_date='' \n" + "            or emp_sal.cmp_leaving_date=1970-00-00 \n" + "            or  date_format(emp_sal.cmp_leaving_date,'%Y-%m')>=date_format(:fromDate,'%Y-%m')\n" + "        )", nativeQuery = true)
public List<EmpInfo> getEmpListForHod(String fromDate,int userId)


@Query(value = "SELECT e.*,emp_sal.cmp_joining_date, emp_sal.sal_basis, emp_sal.salary_type_id  FROM m_employees e, tbl_emp_salary_info emp_sal " + "where e.emp_id=emp_sal.emp_id and e.del_status=1 and (emp_sal.cmp_leaving_date IS NULL or emp_sal.cmp_leaving_date='' or emp_sal.cmp_leaving_date=1970-00-00 or  date_format(emp_sal.cmp_leaving_date,'%Y-%m')>=date_format(:fromDate,'%Y-%m')) and e.location_id in (:locId)", nativeQuery = true)
public List<EmpInfo> getEmpListAlllocId(String fromDate,List<Integer> locId)


@Query(value = "SELECT e.*,emp_sal.cmp_joining_date, emp_sal.sal_basis, emp_sal.salary_type_id  FROM m_employees e, tbl_emp_salary_info emp_sal " + "where e.emp_id=emp_sal.emp_id and e.del_status=1 and e.designation_id=:design and (emp_sal.cmp_leaving_date IS NULL or emp_sal.cmp_leaving_date='' or emp_sal.cmp_leaving_date=1970-00-00 or  date_format(emp_sal.cmp_leaving_date,'%Y-%m')>=date_format(:fromDate,'%Y-%m'))", nativeQuery = true)
public List<EmpInfo> getEmpListAllForRoaster(String fromDate,int design)


}