import com.ats.hrmgt.model.HolidayListCatWise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface HolidayListCatWiseRepo extends JpaRepository<HolidayListCatWise, Integer> {


@Query(value = "select UUID() as id, h.cal_yr_id,h.ex_int1 as cat_id,hc.ho_cat_name,hc.ex_int1 as optional_holiday, (select count('') from m_holiday where ex_int1=h.ex_int1 " + "and cal_yr_id=h.cal_yr_id and ex_int3=0 and del_status=1) as na_count,(select count('') from m_holiday where ex_int1=h.ex_int1 and cal_yr_id=h.cal_yr_id and " + "ex_int3=1 and del_status=1) as fixed_count,(select count('') from m_holiday where ex_int1=h.ex_int1 and cal_yr_id=h.cal_yr_id and ex_int3=2 and del_status=1) " + "as optional_count,concat(cy.cal_yr_from_date,' To ',cy.cal_yr_to_date) as year_date from m_holiday h,holiday_category hc,dm_cal_year cy where hc.ho_cat_id=h.ex_int1 " + "and h.cal_yr_id=cy.cal_yr_id and h.del_status=1 group by h.ex_int1,h.cal_yr_id order by h.cal_yr_id asc ,hc.ho_cat_name asc", nativeQuery = true)
public List<HolidayListCatWise> getHolidayCategoryListGroupBy()


}