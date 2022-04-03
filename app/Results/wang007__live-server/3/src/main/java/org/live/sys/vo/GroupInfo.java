package org.live.sys.vo;
 import org.live.sys.entity.Group;
import org.live.sys.entity.Role;
import java.util.List;
public class GroupInfo {

 private  Group group;

 private  List<Role> roles;

public GroupInfo() {
}public GroupInfo(Group group, List<Role> roles) {
    this.group = group;
    this.roles = roles;
}
public void setGroup(Group group){
    this.group = group;
}


public Group getGroup(){
    return group;
}


public void setRoles(List<Role> roles){
    this.roles = roles;
}


public List<Role> getRoles(){
    return roles;
}


}