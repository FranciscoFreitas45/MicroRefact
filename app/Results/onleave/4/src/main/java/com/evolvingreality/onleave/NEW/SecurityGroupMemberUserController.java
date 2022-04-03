package com.evolvingreality.onleave.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.evolvingreality.onleave.model.SecurityGroupMember;
@RestController
@CrossOrigin
public class SecurityGroupMemberUserController {

@Autowired
 private SecurityGroupMemberUserService securitygroupmemberuserservice;


@PutMapping
("/User/{id}/SecurityGroupMember/setGroupMembers")
public void setGroupMembers(@PathVariable(name="id") Long id,@RequestBody List<SecurityGroupMember> groupMembers){
securitygroupmemberuserservice.setGroupMembers(id,groupMembers);
}


@GetMapping
("/User/{id}/SecurityGroupMember/getGroupMembers")
public List<SecurityGroupMember> getGroupMembers(@PathVariable(name="id") Long id){
return securitygroupmemberuserservice.getGroupMembers(id);
}


}