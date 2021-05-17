import cn.offway.athena.domain.PhBanner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface PhBannerService {


public Long getMaxSort()


public PhBanner save(PhBanner phBanner)


public PhBanner findOne(Long id)


public void resort(Long sort)


public Page<PhBanner> findAll(String id,String remark,Pageable pageable)


public void delete(Long id)


}