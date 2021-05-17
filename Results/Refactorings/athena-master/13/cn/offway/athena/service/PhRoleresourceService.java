import java.util.List;
import cn.offway.athena.domain.PhRoleresource;
public interface PhRoleresourceService {


public List<Long> findSourceIdByRoleId(Long roleId)


public PhRoleresource save(PhRoleresource phRoleresource)


public PhRoleresource findOne(Long id)


}