import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.offway.athena.domain.PhOrderRemark;
import java.util.List;
public interface PhOrderRemarkRepository extends JpaRepository<PhOrderRemark, Long> {


public List<PhOrderRemark> findAllByOrdersId(String id)


}