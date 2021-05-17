import com.ats.hrmgt.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface HolidayRepo extends JpaRepository<Holiday, Integer> {


public List<Holiday> findByExInt1AndDelStatus(int hoCatId,int i)


@Transactional
@Modifying
@Query("update Holiday set del_status=0  WHERE cal_yr_id=:yearId and ex_int1=:catid")
public int deleteHolidayByGroup(int yearId,int catid)


@Query(value = "select * from m_holiday where cal_yr_id=:yearId and ex_int1=:catId and del_status=1", nativeQuery = true)
public List<Holiday> getHolidayByYearIdAndCateId(int yearId,int catId)


@Query(value = "select w.* from m_holiday w,m_employees e where  FIND_IN_SET(e.location_id,w.loc_id)  and e.emp_id=:empId and w.del_status=1 and " + "w.cal_yr_id=(select cal_yr_id from dm_cal_year where is_current=1) " + "and holiday_fromdt>=:fromDate and holiday_todt<=:toDate", nativeQuery = true)
public List<Holiday> getHolidayByEmpIdAndFromDateTodate(int empId,String fromDate,String toDate)


public Holiday findByHolidayIdAndDelStatus(int holidayId,int i)


@Query(value = "select * from m_holiday where holiday_fromdt in (:dates) and ex_int1=:catId and del_status=1", nativeQuery = true)
public List<Holiday> getHolidayListByDates(List<String> dates,int catId)


@Query(value = "select * from m_holiday where holiday_fromdt>=:date and del_status=1 and ex_int1=:catId and cal_yr_id=:yearId and ex_int3=2 " + "and holiday_fromdt not in (select holidate from t_optional_holiday where emp_id=:empId and (status=0 or status=1) and year_id=cal_yr_id)", nativeQuery = true)
public List<Holiday> getHolidayListforoptionalHoliday(String date,int yearId,int catId,int empId)


@Transactional
@Modifying
@Query("update Holiday set del_status=0  WHERE holiday_id=:holidayId")
public int deleteHoliday(int holidayId)


@Query(value = "select * from m_holiday where holiday_fromdt between :fromDate and :toDate and m_holiday.cal_yr_id=(select cal_yr_id from dm_cal_year where is_current=1) " + "and del_status=1 and ex_int3=1", nativeQuery = true)
public List<Holiday> getholidaybetweendate(String fromDate,String toDate)


}