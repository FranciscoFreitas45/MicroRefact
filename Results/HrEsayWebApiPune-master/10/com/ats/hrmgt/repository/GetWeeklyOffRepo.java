import com.ats.hrmgt.model.GetWeeklyOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetWeeklyOffRepo extends JpaRepository<GetWeeklyOff, Integer> {


@Query(value = "     SELECT\n" + "        c.company_name,\n" + "        w.*,\n" + "        '' as loc_name \n" + "        ,wcat.wo_cat_name as week_off_cat\n" + "    FROM\n" + "        weekly_off w,\n" + "        m_company c,weekoff_category wcat\n" + "    WHERE\n" + "        w.del_status=1 \n" + "        AND c.company_id=w.company_id   AND c.company_id=:companyId \n" + "        AND   wcat.wo_cat_id=w.ex_int1 order by wcat.wo_cat_name asc,w.wo_id", nativeQuery = true)
public List<GetWeeklyOff> getListByCompanyId(int companyId)


@Query(value = "     SELECT\n" + "        c.company_name,\n" + "        w.*,\n" + "        '' as loc_name \n" + "        ,wcat.wo_cat_name as week_off_cat\n" + "    FROM\n" + "        weekly_off w,\n" + "        m_company c  ,weekoff_category wcat\n" + "    WHERE\n" + "        w.del_status=1 \n" + "        AND c.company_id=w.company_id AND c.company_id=1 AND wcat.wo_cat_id=w.ex_int1 and w.ex_int1=:catId", nativeQuery = true)
public List<GetWeeklyOff> getWeeklyOffListByCatId(int catId)


@Query(value = "SELECT\n" + "        '' as company_name,\n" + "        w.*,\n" + "        '' as loc_name          ,\n" + "        '' as week_off_cat     \n" + "    FROM\n" + "        weekly_off w,  \n" + "        m_employees e     \n" + "    WHERE\n" + "        w.del_status=1  \n" + "        AND e.weekend_category=w.ex_int1 \n" + "        and e.emp_id=:empId", nativeQuery = true)
public List<GetWeeklyOff> getWeeklyOffListByEmpIdDashboard(int empId)


}