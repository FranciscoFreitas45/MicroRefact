package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.userRole.entity.GroupRole;
@RestController
@CrossOrigin
public class GroupRoleDepartmentController {

@Autowired
 private GroupRoleDepartmentService grouproledepartmentservice;


@GetMapping
("/Department/{id}/GroupRole/getGroupRoles")
public List<GroupRole> getGroupRoles(@PathVariable(name="id") Integer dept_id){
return grouproledepartmentservice.getGroupRoles(dept_id);
}


@PutMapping
("/Department/{id}/GroupRole/setGroupRoles")
public void setGroupRoles(@PathVariable(name="id") Integer dept_id,@RequestBody List<GroupRole> groupRoles){
grouproledepartmentservice.setGroupRoles(dept_id,groupRoles);
}


}