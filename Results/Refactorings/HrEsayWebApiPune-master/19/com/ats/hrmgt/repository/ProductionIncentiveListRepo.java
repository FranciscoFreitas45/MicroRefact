import com.ats.hrmgt.model.ProductionIncentiveList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ProductionIncentiveListRepo extends JpaRepository<ProductionIncentiveList, Integer> {


@Query(value = "select\n" + "        UUID() as id,\n" + "        da.att_date,\n" + "        da.emp_id,\n" + "        da.ot_hr as hrs,\n" + "        CONCAT(FLOOR(da.ot_hr/60),\n" + "        ':',\n" + "        LPAD(MOD(da.ot_hr,\n" + "        60),\n" + "        2,\n" + "        '0')) as show_hrs,\n" + "        ds.tot_othr,da.att_status \n" + "    from\n" + "        tbl_attt_daily_daily da,\n" + "        tbl_attt_summary_daily ds \n" + "    where\n" + "        month(da.att_date)=ds.month \n" + "        and year(da.att_date)=ds.year \n" + "        and da.emp_id in (:empIds) \n" + "        and da.freeze_by_supervisor=2 \n" + "        and da.ot_hr>0  \n" + "        and ds.emp_id=da.emp_id \n" + "        and ds.month=:month \n" + "        and ds.year=:year", nativeQuery = true)
public List<ProductionIncentiveList> getproductionIncentiveList(int month,int year,List<Integer> empIds)


@Query(value = "select\n" + "        UUID() as id,\n" + "        da.att_date,\n" + "        da.emp_id,\n" + "        late_mark as hrs,\n" + "        CONCAT(FLOOR(da.ot_hr/60),\n" + "        ':',\n" + "        LPAD(MOD(da.ot_hr,\n" + "        60),\n" + "        2,\n" + "        '0')) as show_hrs,\n" + "        ds.totlate_days as tot_othr,\n" + "        da.att_status\n" + "    from\n" + "        tbl_attt_daily_daily da,\n" + "        tbl_attt_summary_daily ds \n" + "    where\n" + "        month(da.att_date)=ds.month \n" + "        and year(da.att_date)=ds.year \n" + "        and da.emp_id in (:empIds)  \n" + "        and da.late_mark=1  \n" + "        and ds.emp_id=da.emp_id \n" + "        and ds.month=:month \n" + "        and ds.year=:year \n" + "        order by att_date asc", nativeQuery = true)
public List<ProductionIncentiveList> getLateMarkDedListSaparate(int month,int year,List<Integer> empIds)


@Query(value = "select  UUID() as id,da.att_date,da.emp_id,1 as hrs,0 as show_hrs,0 as tot_othr,da.att_status from tbl_attt_daily_daily da where month(att_date)=:month and " + "year(att_date)=:year and emp_id in (:empIds) and lv_sumup_id in(:status)", nativeQuery = true)
public List<ProductionIncentiveList> getPerformanceIncentiveList(int month,int year,List<Integer> empIds,List<Integer> status)


}