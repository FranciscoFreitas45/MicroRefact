package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.userRole.dao.GroupRoleDao;
import com.hmm.userRole.entity.GroupRole;
@Service
public class GroupRoleDepartmentService {

@Autowired
 private GroupRoleDao grouproledao;


public List<GroupRole> getGroupRoles(Integer dept_id){
return grouproledao.getGroupRoles(dept_id);
}


public void setGroupRoles(Integer dept_id,List<GroupRole> groupRoles){
grouproledao.setGroupRoles(dept_id,groupRoles);
}


}