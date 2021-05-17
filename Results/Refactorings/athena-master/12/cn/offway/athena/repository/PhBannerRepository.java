import cn.offway.athena.domain.PhBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
public interface PhBannerRepository extends JpaRepository<PhBanner, Long> {


@Query(nativeQuery = true, value = "SELECT max(sort) as a FROM ph_banner where status = 1")
public Optional<String> getMaxSort()


@Transactional
@Modifying
@Query(nativeQuery = true, value = "update `ph_banner` set `sort` = `sort` + 1 where `sort` >= ?1 AND `status` = 1")
public void resort(Long sort)


}