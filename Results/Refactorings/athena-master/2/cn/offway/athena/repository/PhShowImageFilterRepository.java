import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.offway.athena.domain.PhShowImageFilter;
public interface PhShowImageFilterRepository extends JpaSpecificationExecutor<PhShowImageFilter> {


public int countByShowImage(String showImage)


}