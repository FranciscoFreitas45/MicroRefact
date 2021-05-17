import com.ats.hrmgt.model.HolidayMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface HolidayMasterRepo extends JpaRepository<HolidayMaster, Integer> {


@Query(value = "	SELECT count(*) FROM holiday_master WHERE DAY(holiday_master.holiday_date)=DAY(:holidayDate) AND MONTH(holiday_master.holiday_date)=MONTH(:holidayDate) AND  del_status=1\n" + "", nativeQuery = true)
public Integer getCountOfHolidayByDate(String holidayDate)


@Query(value = "	SELECT count(*) FROM holiday_master WHERE DAY(holiday_master.holiday_date)=DAY(:holidayDate) AND MONTH(holiday_master.holiday_date)=MONTH(:holidayDate) and holiday_id!=:holidayId and del_status=1\n" + "", nativeQuery = true)
public Integer getCountOfHolidayByDateForEdit(String holidayDate,int holidayId)


public HolidayMaster findByHolidayIdAndDelStatus(int holidayId,int i)


@Query(value = "select count('') as count from m_holiday where del_status=1 and ex_int1=:catId and cal_yr_id=:yearId", nativeQuery = true)
public String getcountofholidaybyyear(int catId,int yearId)


@Query(value = " SELECT\n" + "        ho.holiday_id,\n" + "        ho.holiday_fromdt as holiday_date,\n" + "        ho.ex_var2 as holiday_name,\n" + "        ho.ex_int3 as del_status     \n" + "    FROM\n" + "        m_holiday ho,\n" + "        m_employees e \n" + "    WHERE\n" + "        ho.holiday_fromdt BETWEEN :date AND DATE_ADD(:date, INTERVAL 30 DAY)          \n" + "        AND ho.del_status = 1 \n" + "        and ho.ex_int1=e.holiday_category\n" + "        and e.emp_id=:empId\n" + "        and ho.ex_int3 in (1,2)\n" + "    group by\n" + "        ho.ex_int2     \n" + "    ORDER BY\n" + "        ho.holiday_fromdt ASC", nativeQuery = true)
public List<HolidayMaster> getUserApplicableHoliday(String date,int empId)


@Query(value = "SELECT\n" + "        holiday_id,\n" + "        holiday_fromdt as holiday_date,\n" + "        ex_var2 as holiday_name,\n" + "        0 as del_status\n" + "    FROM\n" + "        m_holiday \n" + "    WHERE\n" + "        m_holiday.holiday_fromdt BETWEEN :currDate AND DATE_ADD(:currDate, INTERVAL 30 DAY) \n" + "        AND m_holiday.del_status = 1\n" + "    group by \n" + "        m_holiday.ex_int2\n" + "    ORDER BY\n" + "        m_holiday.holiday_fromdt ASC", nativeQuery = true)
public List<HolidayMaster> getHolidaysForDash(String currDate)


@Query(value = "select\n" + "        *\n" + "    from\n" + "        holiday_master\n" + "    where\n" + "        holiday_master.del_status=:i order by holiday_date asc", nativeQuery = true)
public List<HolidayMaster> findByDelStatusOrder(int i)


@Transactional
@Modifying
@Query("update HolidayMaster set del_status=0  WHERE holiday_id=:holidayId")
public int deleteHoliday(int holidayId)


}