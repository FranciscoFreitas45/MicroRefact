import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import cn.offway.athena.domain.PhBrandadmin;
public interface PhBrandadminRepository extends JpaRepository<PhBrandadmin, Long> {


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_brandadmin where admin_id =?1")
public int deleteByAdminId(Long id)


@Query(nativeQuery = true, value = "select brand_id from ph_brandadmin where admin_id =?1")
public List<Long> findBrandIdByAdminId(Long adminId)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_brandadmin where admin_id in(?1)")
public int deleteByAdminIds(List<Long> ids)


}