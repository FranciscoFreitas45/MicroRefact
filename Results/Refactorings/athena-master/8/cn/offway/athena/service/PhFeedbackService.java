import cn.offway.athena.domain.PhFeedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;
public interface PhFeedbackService {


public PhFeedback findByBrandId(Long id)


public List<PhFeedback> save(List<PhFeedback> entities)


public PhFeedback findOne(Long id)


public void delete(Long id)


public Page<PhFeedback> findAll(Pageable pageable,String brandId,List<Long> brandIds,String starName,Date sTime,Date eTime)


}