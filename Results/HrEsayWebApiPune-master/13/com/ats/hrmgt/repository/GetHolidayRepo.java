import com.ats.hrmgt.model.GetHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetHolidayRepo extends JpaRepository<GetHoliday, Integer> {


@Query(value = "SELECT\n" + "        h.holiday_id,\n" + "        h.holiday_fromdt,\n" + "        h.holiday_todt,\n" + "        h.holiday_remark,\n" + "        h.ex_var1,\n" + "        h.ex_var2,\n" + "        h.cal_yr_id,\n" + "        h.company_id,\n" + "        h.loc_id,\n" + "        c.company_name,\n" + "        l.loc_name,\n" + "        cy.cal_yr_from_date,\n" + "        cy.cal_yr_to_date,\n" + "        hc.ho_cat_name,hc.ho_cat_id,h.ex_int3 as hotype\n" + "    FROM\n" + "        m_holiday h ,\n" + "        m_company c,\n" + "        m_location l,\n" + "        dm_cal_year cy,\n" + "        holiday_category hc\n" + "    WHERE\n" + "        h.company_id=:companyId    \n" + "        AND h.del_status=1 \n" + "        AND h.is_active=1 \n" + "        AND h.company_id=c.company_id \n" + "        AND l.loc_id=h.loc_id \n" + "        AND cy.cal_yr_id=h.cal_yr_id \n" + "        AND c.del_status=1 \n" + "        AND l.del_status=1 \n" + "        and hc.ho_cat_id = h.ex_int1 order by hc.ho_cat_id,h.holiday_fromdt ", nativeQuery = true)
public List<GetHoliday> getHolidayListByCompany(int companyId)


}