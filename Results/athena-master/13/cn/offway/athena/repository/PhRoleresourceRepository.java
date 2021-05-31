import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import cn.offway.athena.domain.PhRoleresource;
public interface PhRoleresourceRepository extends JpaSpecificationExecutor<PhRoleresource> {


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_roleresource where role_id = ?1")
public int deleteByRoleId(Long id)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_roleresource where resource_id in(?1)")
public int deleteByResourceIds(List<Long> ids)


@Query(nativeQuery = true, value = "select resource_id from ph_roleresource where role_id = ?1")
public List<Long> findSourceIdByRoleId(Long roleId)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_roleresource where role_id in(?1)")
public int deleteByRoleIds(List<Long> ids)


}