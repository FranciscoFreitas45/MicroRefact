import cn.offway.athena.domain.PhBannerHistory;
import java.util.List;
public interface PhBannerHistoryService {


public List<PhBannerHistory> findList(String id)


public List<PhBannerHistory> save(List<PhBannerHistory> entities)


public PhBannerHistory findOne(Long id)


public void delete(Long id)


}