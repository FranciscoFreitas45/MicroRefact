package cn.com.cnc.fcc.service;
 import java.util.List;
import org.springframework.stereotype.Service;
import cn.com.cnc.fcc.domain.RbacUser;
import cn.com.cnc.fcc.service.dto.RbacUserDTO;
@Service
public interface RbacUserService {


public List<RbacUserDTO> getUserRoleInfo(Integer userId)
;

public List<RbacUserDTO> getRoleList()
;

public Integer updateUser(String selectListVal,RbacUser rbacUser)
;

public Integer createUser(String selectListVal,RbacUser rbacUser)
;

}