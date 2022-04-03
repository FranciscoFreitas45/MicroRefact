package org.live.sys.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "sys_group_role")
public class GroupRole extends BaseEntity{

 private  long serialVersionUID;

@ManyToOne
@JoinColumn(name = "role_id")
 private  Role role;

@ManyToOne
@JoinColumn(name = "group_id")
 private  Group group;

public GroupRole() {
}public GroupRole(Role role, Group group) {
    this.role = role;
    this.group = group;
}
public void setGroup(Group group){
    this.group = group;
}


public void setRole(Role role){
    this.role = role;
}


public Group getGroup(){
    return group;
}


public Role getRole(){
    return role;
}


}