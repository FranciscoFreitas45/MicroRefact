package com.evolvingreality.onleave.Request;
import com.evolvingreality.onleave.DTO.SecurityGroupMember;
public interface SecurityGroupMemberRequest {

   public void setGroupMembers(List<SecurityGroupMember> groupMembers,Long id);
   public List<SecurityGroupMember> getGroupMembers(Long id);
}