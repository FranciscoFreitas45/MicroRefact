import cn.com.cnc.fcc.domain.RbacElement;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface RbacElementRepository extends JpaRepository<RbacElement, Long> {


}