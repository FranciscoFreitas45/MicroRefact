import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.offway.athena.domain.PhShowImageFilter;
public interface PhShowImageFilterService {


public List<PhShowImageFilter> save(List<PhShowImageFilter> phShowImageFilters)


public PhShowImageFilter findOne(Long id)


public Page<PhShowImageFilter> findByPage(Long brandId,List<Long> brandIds,Date sTime,Date eTime,Pageable page)


public int countByShowImage(String showImage)


}