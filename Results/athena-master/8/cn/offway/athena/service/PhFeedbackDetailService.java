import cn.offway.athena.domain.PhFeedbackDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;
public interface PhFeedbackDetailService {


public Page<PhFeedbackDetail> findByPid(Long pid,String starName,Date sTime,Date eTime,Pageable pageable)


public List<PhFeedbackDetail> save(List<PhFeedbackDetail> entities)


public PhFeedbackDetail findOne(Long id)


public void delByPid(Long pid)


public Long checkStarName(Long pid,String starName)


public void delete(Long id)


}