import cn.offway.athena.domain.PhWardrobeAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface PhWardrobeAuditService {


public List<PhWardrobeAudit> save(List<PhWardrobeAudit> entities)


public PhWardrobeAudit findOne(Long id)


public Page<PhWardrobeAudit> listAll(String brandId,String goodsName,String goodsId,String state,List<Long> brandIds,Pageable pageable)


public void delete(Long id)


}