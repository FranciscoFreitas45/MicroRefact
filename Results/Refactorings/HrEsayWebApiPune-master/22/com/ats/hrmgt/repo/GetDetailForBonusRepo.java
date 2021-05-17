import com.ats.hrmgt.model.GetDetailForBonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface GetDetailForBonusRepo extends JpaRepository<GetDetailForBonus, Integer> {


@Query(value = "select\n" + "        coalesce(id,0) as id,\n" + "        coalesce(emp_id,:empId) as emp_id,\n" + "        coalesce(sum(present_days),0) as presentdays ,\n" + "        coalesce(sum(weekly_off),0)  as weeklyoff ,\n" + "        coalesce(sum(paid_holiday),0) as holiday,\n" + "        coalesce((select\n" + "            value \n" + "        from\n" + "            m_setting \n" + "        where\n" + "            setting_id=23),\n" + "        0) as bonus_per,\n" + "        coalesce((select\n" + "            sf.basic \n" + "        from\n" + "            tbl_emp_salary_info sf  \n" + "        where\n" + "            sf.emp_id=tbl_attt_summary_daily.emp_id ),\n" + "        0) as basic,\n" + "        coalesce( (select\n" + "            es.allowance_value \n" + "        from\n" + "            emp_sal_allowance es \n" + "        where\n" + "            es.emp_id=tbl_attt_summary_daily.emp_id  \n" + "            and es.allowance_id=1),\n" + "        0) as allowance_value \n" + "    from\n" + "        tbl_attt_summary_daily  \n" + "    where\n" + "        emp_id=:empId \n" + "        and month between :fromMonth and :toMonth   \n" + "        and year between :fromYear and :toYear", nativeQuery = true)
public GetDetailForBonus getbonuscalDetails(int empId,int fromMonth,int toMonth,int fromYear,int toYear)


}