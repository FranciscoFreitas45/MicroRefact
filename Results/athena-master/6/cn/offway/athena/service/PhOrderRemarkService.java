import java.util.List;
import cn.offway.athena.domain.PhOrderRemark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface PhOrderRemarkService {


public List<PhOrderRemark> save(List<PhOrderRemark> entities)


public PhOrderRemark findOne(Long id)


public Page<PhOrderRemark> findAllByPage(String id,Pageable page)


public void delete(Long id)


public List<PhOrderRemark> findAllByOrdersId(String id)


}