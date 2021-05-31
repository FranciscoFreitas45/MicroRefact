import com.ats.hrmgt.model.DailyDailyInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface DailyDailyInformationRepository extends JpaRepository<DailyDailyInformation, Integer> {


@Query(value = "select\n" + "        uuid() as uuid,\n" + "        dl.emp_id,\n" + "        count(*) as daycount,\n" + "        dl.lv_sumup_id,\n" + "        sp.name_sd,\n" + "        sum(dl.working_hrs) as working_min,\n" + "        ifnull((select sum(tbl_attt_daily_daily.ot_hr) from tbl_attt_daily_daily where tbl_attt_daily_daily.emp_id=dl.emp_id and " + "tbl_attt_daily_daily.freeze_by_supervisor=2 and dl.lv_sumup_id=tbl_attt_daily_daily.lv_sumup_id and tbl_attt_daily_daily.att_date between :fromDate and :toDate),0)  as ot_min,\n" + "        sum(late_mark) as late_mark,\n" + "        sum(late_min) as  late_min,sum(full_night) as  full_night,sum(atsumm_uid) as  mark_compoff,\n" + "        es.sal_basis\n" + "    from\n" + "        tbl_attt_daily_daily dl,\n" + "        tbl_lvm_sumup sp,\n" + "        tbl_emp_salary_info es\n" + "    where\n" + "        dl.att_date between :fromDate and :toDate \n" + "        and dl.lv_sumup_id=sp.id\n" + "        and es.emp_id=dl.emp_id and dl.emp_id=:empId and dl.is_fixed=0 and dl.rec_status='o'\n" + "    group by\n" + "        dl.emp_id,\n" + "        dl.lv_sumup_id ", nativeQuery = true)
public List<DailyDailyInformation> dailyDailyInformationList(String fromDate,String toDate,int empId)


}