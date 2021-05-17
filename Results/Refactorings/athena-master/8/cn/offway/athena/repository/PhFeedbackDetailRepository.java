import cn.offway.athena.domain.PhFeedbackDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
public interface PhFeedbackDetailRepository extends JpaSpecificationExecutor<PhFeedbackDetail> {


@Transactional
@Modifying
@Query(nativeQuery = true, value = "DELETE FROM `ph_feedback_detail` WHERE (`pid` = ?1)")
public void deleteByPid(Long pid)


}