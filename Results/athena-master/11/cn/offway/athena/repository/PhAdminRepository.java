import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import cn.offway.athena.domain.PhAdmin;
public interface PhAdminRepository extends JpaSpecificationExecutor<PhAdmin> {


public PhAdmin findByUsername(String username)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_admin where id in(?1)")
public int deleteByIds(List<Long> ids)


}