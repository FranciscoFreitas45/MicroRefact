import com.ats.hrmgt.model.TotalOT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface TotalOTRepository extends JpaRepository<TotalOT, Integer> {


@Query(value = "select\n" + "        UUID() AS id,\n" + "        e.depart_id,\n" + "        concat(ds.month,\n" + "        '-',\n" + "        ds.year) as month,\n" + "        sum(tot_othr)/60 as ot,\n" + "        concat(ds.year,\n" + "        '-',\n" + "        ds.month,\n" + "        '-01') as date_mo,\n" + "        dept.name     \n" + "    from\n" + "        tbl_attt_summary_daily ds,\n" + "        m_employees e,\n" + "        m_department dept      \n" + "    where\n" + "        date_format(concat(ds.year,'-',ds.month,'-01'),'%Y-%m')>=date_format(date_sub(:defaltdt,Interval 5 month),'%Y-%m')          \n" + "        and e.emp_id=ds.emp_id         \n" + "        and e.location_id=:locId         \n" + "        and e.del_status=1         \n" + "        and dept.depart_id=e.depart_id         \n" + "        and dept.del_status=1   and TIMESTAMPDIFF(MONTH,concat(ds.year,'-',ds.month,'-01'),:defaltdt)>=0  \n" + "    group by \n" + "        e.depart_id      \n" + "    order by \n" + "        e.depart_id asc", nativeQuery = true)
public List<TotalOT> deptList(int locId,String defaltdt)


@Query(value = "select\n" + "        UUID() AS id,\n" + "        e.depart_id,\n" + "        concat(ds.month,\n" + "        '-',\n" + "        ds.year) as month,\n" + "        sum(tot_othr)/60 as ot,\n" + "        concat(ds.year,'-',ds.month,\n" + "        '-01') as date_mo,\n" + "        dept.name\n" + "    from\n" + "        tbl_attt_summary_daily ds,m_employees e,m_department dept \n" + "    where\n" + "        date_format(concat(ds.year,'-',ds.month,'-01'),'%Y-%m')>=date_format(date_sub(:defaltdt,Interval 5 month),'%Y-%m') \n" + "        and e.emp_id=ds.emp_id\n" + "        and e.location_id=:locId\n" + "        and e.del_status=1\n" + "        and dept.depart_id=e.depart_id\n" + "        and dept.del_status=1 and TIMESTAMPDIFF(MONTH,concat(ds.year,'-',ds.month,'-01'),:defaltdt)>=0\n" + "    group by month,e.depart_id \n" + "    order by month asc,e.depart_id asc", nativeQuery = true)
public List<TotalOT> totalOtPrevioussixMonth(int locId,String defaltdt)


}