import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.offway.athena.domain.PhRole;
public interface PhRoleService {


public void save(PhRole phRole,Long[] resourceIds)


public PhRole findOne(Long id)


public Page<PhRole> findByPage(String name,Pageable page)


public void deleteRole(String ids)


public List<PhRole> findAll()


}