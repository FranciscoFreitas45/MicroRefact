import com.ats.hrmgt.model.GetAdvanceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetAdvanceListRepo extends JpaRepository<GetAdvanceList, Integer> {


@Query(value = "select id,emp_id, sum(adv_amount) as adv_amount from tbl_advance where ded_month=:month and ded_year=:year " + "and del_status=1 and is_ded=0 and emp_id in (:empIds) group by emp_id", nativeQuery = true)
public List<GetAdvanceList> getAdvanceList(int month,int year,List<Integer> empIds)


@Query(value = "select\n" + "        id,\n" + "        emp_id,\n" + "        round(adv_amount) as adv_amount \n" + "    from\n" + "        tbl_advance \n" + "    where\n" + "        ded_month=:month\n" + "        and ded_year=:year\n" + "        and del_status=1 \n" + "        and is_ded=1 \n" + "        and emp_id in (:empIds) and ex_int1=0", nativeQuery = true)
public List<GetAdvanceList> getAdvanceListSaparate(int month,int year,List<Integer> empIds)


@Query(value = "select\n" + "        id,\n" + "        emp_id,\n" + "        round(adv_amount) as adv_amount \n" + "    from\n" + "        tbl_advance \n" + "    where\n" + "        ded_month=:month\n" + "        and ded_year=:year\n" + "        and del_status=1 \n" + "        and is_ded=1 \n" + "        and emp_id in (:empIds) and ex_int1=1", nativeQuery = true)
public List<GetAdvanceList> getAbsentDedListSaparate(int month,int year,List<Integer> empIds)


@Query(value = "select id, emp_id, sum(adv_amount) as adv_amount from tbl_advance where del_status=1 and is_ded=0  and emp_id in (:empIds) group by emp_id", nativeQuery = true)
public GetAdvanceList getAdvanceListForFullFinal(List<Integer> empIds)


}