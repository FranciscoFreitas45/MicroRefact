package com.evolvingreality.onleave.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.evolvingreality.onleave.model.SecurityGroupMember;
public interface SecurityGroupMemberRepository extends JpaRepository<SecurityGroupMember, Long>{


public void setGroupMembers(Long id,List<SecurityGroupMember> groupMembers);

public List<SecurityGroupMember> getGroupMembers(Long id);

}