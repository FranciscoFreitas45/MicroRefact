import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.offway.athena.domain.PhOrderExpressInfo;
public interface PhOrderExpressInfoRepository extends JpaSpecificationExecutor<PhOrderExpressInfo> {


public PhOrderExpressInfo findByExpressOrderNo(String expressOrderNo)


public PhOrderExpressInfo findByMailNo(String mailNo)


public PhOrderExpressInfo findByOrderNoAndType(String orderNo,String type)


}