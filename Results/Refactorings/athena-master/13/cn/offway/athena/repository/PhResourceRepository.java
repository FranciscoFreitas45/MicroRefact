import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import cn.offway.athena.domain.PhResource;
public interface PhResourceRepository extends JpaRepository<PhResource, Long> {


public List<PhResource> findByParentId(Long parentId)


@Query(nativeQuery = true, value = "select * from ph_resource where id in(?1)")
public List<PhResource> findByIds(List<Long> ids)


@Query(nativeQuery = true, value = "select DISTINCT(ru.link) from ph_resource ru ,ph_roleresource rr,ph_roleadmin ra where ru.id = rr.resource_id and rr.role_id = ra.role_id and ra.admin_id=?1")
public Set<String> findUrlsByAdminId(Long adminId)


public List<PhResource> findByParentIdNotNull()


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_resource where id in(?1)")
public int deleteByIds(List<Long> ids)


@Query(nativeQuery = true, value = "select * from ph_resource where id in (select DISTINCT(ru.id) from ph_resource ru ,ph_roleresource rr,ph_roleadmin ra where ru.id = rr.resource_id and rr.role_id = ra.role_id and ra.admin_id=?1 ) ORDER BY parent_id ASC,sort ASC")
public List<PhResource> findByAdminId(Long adminId)


}