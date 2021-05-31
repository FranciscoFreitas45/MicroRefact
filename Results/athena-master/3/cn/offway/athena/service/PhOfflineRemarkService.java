import cn.offway.athena.domain.PhOfflineRemark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface PhOfflineRemarkService {


public String findLatest(Long id)


public List<PhOfflineRemark> save(List<PhOfflineRemark> entities)


public PhOfflineRemark findOne(Long id)


public Page<PhOfflineRemark> findAllByPage(String id,Pageable page)


public void delete(Long id)


}