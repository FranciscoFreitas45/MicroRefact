import com.ats.hrmgt.model.CountData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface CountDataRepository extends JpaRepository<CountData, Integer> {


@Query(value = "select UUID() as id,\n" + "(select count('') from tbl_attt_daily_daily d, m_employees e where d.att_date = :date and d.lv_sumup_id in (5,13,14,15,20,21,18) and e.emp_id=d.emp_id and e.location_id in (:locId)) as present,\n" + "(select count('') from tbl_attt_daily_daily d, m_employees e where d.att_date = :date and d.lv_sumup_id in (22) and e.emp_id=d.emp_id and e.location_id in (:locId)) as absent,\n" + "(select count('') from tbl_attt_daily_daily d, m_employees e where d.att_date = :date and d.lv_sumup_id in (7,11) and e.emp_id=d.emp_id and e.location_id in (:locId)) as leavecount,\n" + "(select count('') from tbl_attt_daily_daily d, m_employees e where d.att_date = :date and late_mark=1 and e.emp_id=d.emp_id and e.location_id in (:locId)) as latemark," + "(select\n" + "            count('') \n" + "        from\n" + "            tbl_attt_daily_daily d,\n" + "            m_employees e \n" + "        where\n" + "            d.att_date =:date \n" + "            and d.lv_sumup_id in (\n" + "               6\n" + "            ) \n" + "            and e.emp_id=d.emp_id \n" + "            and e.location_id in (:locId)) as holiday,\n" + "        (select\n" + "            count('') \n" + "        from\n" + "            tbl_attt_daily_daily d,\n" + "            m_employees e \n" + "        where\n" + "            d.att_date =:date \n" + "            and d.lv_sumup_id in (\n" + "                12\n" + "            ) \n" + "            and e.emp_id=d.emp_id \n" + "            and e.location_id in (:locId)) as weeklyoff", nativeQuery = true)
public CountData countData(int locId,String date)


}