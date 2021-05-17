import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import cn.offway.athena.domain.PhRoleadmin;
public interface PhRoleadminRepository extends JpaRepository<PhRoleadmin, Long> {


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_roleadmin where admin_id =?1")
public int deleteByAdminId(Long id)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_roleadmin where role_id in(?1)")
public int deleteByRoleIds(List<Long> ids)


@Query(nativeQuery = true, value = "select role_id from ph_roleadmin where admin_id =?1")
public List<Long> findRoleIdByAdminId(Long adminId)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_roleadmin where admin_id in(?1)")
public int deleteByAdminIds(List<Long> ids)


}