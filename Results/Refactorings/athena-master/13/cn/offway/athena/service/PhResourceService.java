import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.offway.athena.domain.PhResource;
public interface PhResourceService {


public List<PhResource> findByParentId(Long parentId)


public void deleteResource(String ids)


public Set<String> findUrlsByAdminId(Long adminId)


public PhResource save(PhResource phResource)


public PhResource findOne(Long id)


public Page<PhResource> findByPage(String name,String link,Long parentId,Pageable page)


public List<PhResource> findByParentIdNotNull()


public List<PhResource> findByAdminId(Long adminId)


}