import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import cn.offway.athena.domain.PhRole;
public interface PhRoleRepository extends JpaRepository<PhRole, Long> {


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_role where id in(?1)")
public int deleteByRoleIds(List<Long> ids)


}