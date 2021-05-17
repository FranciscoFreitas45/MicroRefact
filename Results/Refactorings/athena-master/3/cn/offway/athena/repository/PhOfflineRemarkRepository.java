import cn.offway.athena.domain.PhOfflineRemark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
public interface PhOfflineRemarkRepository extends JpaRepository<PhOfflineRemark, Long> {


@Query(nativeQuery = true, value = "SELECT content FROM ph_offline_remark where orders_id = ?1 order by create_time desc limit 1")
public Object findLatest(Long id)


}