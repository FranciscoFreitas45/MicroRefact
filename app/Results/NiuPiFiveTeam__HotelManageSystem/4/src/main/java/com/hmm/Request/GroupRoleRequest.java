package com.hmm.Request;
import com.hmm.DTO.GroupRole;
public interface GroupRoleRequest {

   public List<GroupRole> getGroupRoles(Integer dept_id);
   public void setGroupRoles(List<GroupRole> groupRoles,Integer dept_id);
}