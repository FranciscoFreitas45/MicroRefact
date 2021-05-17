import cn.offway.athena.domain.PhBannerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface PhBannerHistoryRepository extends JpaRepository<PhBannerHistory, Long> {


@Query(nativeQuery = true, value = "SELECT count(`banner_id`) as banner_id,`banner`,`banner_id` as id,`banner_img`,`create_time`,`begin_time`,`end_time` FROM ph_banner_history where `banner_id` = ?1 and `banner` like ?2 and `banner_id` in (?3) group by banner_id order by count(`banner_id`) desc")
public List<PhBannerHistory> listRank(long id,String name,List<Long> brandIds)


}