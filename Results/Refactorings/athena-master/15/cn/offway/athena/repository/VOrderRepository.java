import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.offway.athena.domain.VOrder;
import java.lang.String;
import java.util.List;
public interface VOrderRepository extends JpaRepository<VOrder, Long> {


public VOrder findByOrderNo(String orderNo)


}