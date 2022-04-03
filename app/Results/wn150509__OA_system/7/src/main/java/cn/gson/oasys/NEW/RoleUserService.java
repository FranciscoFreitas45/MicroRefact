package cn.gson.oasys.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.dao.roledao.RoleDao;
import cn.gson.oasys.model.entity.role.Role;
@Service
public class RoleUserService {

@Autowired
 private RoleDao roledao;


public void setRole(Long roleId,Role role){
roledao.setRole(roleId,role);
}


public Role getRole(Long roleId){
return roledao.getRole(roleId);
}


}