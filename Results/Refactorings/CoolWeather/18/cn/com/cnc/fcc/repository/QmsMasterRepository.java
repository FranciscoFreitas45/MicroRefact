import cn.com.cnc.fcc.domain.QmsMaster;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface QmsMasterRepository extends JpaRepository<QmsMaster, Long> {


}