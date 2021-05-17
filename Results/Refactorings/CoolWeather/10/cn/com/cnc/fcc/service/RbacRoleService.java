import java.util.List;
import org.springframework.stereotype.Service;
import cn.com.cnc.fcc.domain.RbacRole;
import cn.com.cnc.fcc.service.dto.RbacRoleDTO;
import cn.com.cnc.fcc.service.dto.RbacRightDTO;
@Service
public interface RbacRoleService {


public Integer updateRole(String selectListVal,RbacRole rbacRole)


public List<RbacRoleDTO> getRoleRightInfo(Integer roleId)


public List<RbacRightDTO> getRightList()


public Integer createRole(String selectListVal,RbacRole rbacRole)


}