import com.ats.hrmgt.model.GetClaimList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetClaimListRepo extends JpaRepository<GetClaimList, Integer> {


@Query(value = "select ca_head_id,emp_id, sum(claim_amount) as claim_amount from claim_apply_header where month=:month and year=:year " + "and claim_status=3 and is_paid=0 and emp_id in (:empIds) group by emp_id", nativeQuery = true)
public List<GetClaimList> getClaimList(int month,int year,List<Integer> empIds)


@Query(value = "select ca_head_id,emp_id, round(claim_amount) as claim_amount from claim_apply_header where month=:month and year=:year " + "and claim_status=3 and is_paid=1 and emp_id in (:empIds) ", nativeQuery = true)
public List<GetClaimList> getClaimListSaparate(int month,int year,List<Integer> empIds)


}