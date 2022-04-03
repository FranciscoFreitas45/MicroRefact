package com.evolvingreality.onleave.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.evolvingreality.onleave.repository.SecurityGroupMemberRepository;
import com.evolvingreality.onleave.model.SecurityGroupMember;
@Service
public class SecurityGroupMemberUserService {

@Autowired
 private SecurityGroupMemberRepository securitygroupmemberrepository;


public void setGroupMembers(Long id,List<SecurityGroupMember> groupMembers){
securitygroupmemberrepository.setGroupMembers(id,groupMembers);
}


public List<SecurityGroupMember> getGroupMembers(Long id){
return securitygroupmemberrepository.getGroupMembers(id);
}


}