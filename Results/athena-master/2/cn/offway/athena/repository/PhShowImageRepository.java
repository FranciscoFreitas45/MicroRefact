import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.offway.athena.domain.PhShowImage;
public interface PhShowImageRepository extends JpaSpecificationExecutor<PhShowImage> {


public PhShowImage findByOrderNo(String orderNo)


}