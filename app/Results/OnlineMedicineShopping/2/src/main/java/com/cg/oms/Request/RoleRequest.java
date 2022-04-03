package com.cg.oms.Request;
import com.cg.oms.DTO.Role;
public interface RoleRequest {

   public Role getRole(Long roleId);
   public void setRole(Role role,Long roleId);
}