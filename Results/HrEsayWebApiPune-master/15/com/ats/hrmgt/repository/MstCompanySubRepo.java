import com.ats.hrmgt.model.MstCompanySub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface MstCompanySubRepo extends JpaRepository<MstCompanySub, Integer> {


@Transactional
@Modifying
@Query("update MstCompanySub set is_active=:stat  WHERE company_id=:compId")
public int activateSubComp(int compId,int stat)


@Transactional
@Modifying
@Query("delete from MstCompanySub where company_id=:compId")
public int deleteSubComp(int compId)


public List<MstCompanySub> findByIsActive(int i)


public MstCompanySub findByCompanyId(int companyId)


@Query(value = "select * from tbl_mst_sub_company order by company_id desc", nativeQuery = true)
public List<MstCompanySub> findAllorderby()


public MstCompanySub findByCompanyIdAndDelStatus(int companyId,int i)


}