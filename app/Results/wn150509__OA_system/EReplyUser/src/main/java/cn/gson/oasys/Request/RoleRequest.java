package cn.gson.oasys.Request;
import cn.gson.oasys.DTO.Role;
public interface RoleRequest {

   public void setRole(Role role,Long roleId);
   public Role getRole(Long roleId);
}