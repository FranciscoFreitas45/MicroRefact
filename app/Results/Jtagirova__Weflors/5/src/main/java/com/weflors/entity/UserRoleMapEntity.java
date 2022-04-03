package com.weflors.entity;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence;
@Entity
@Table(name = "user_role_map", schema = "flowershop", catalog = "postgres")
@IdClass(UserRoleMapEntityPK.class)
public class UserRoleMapEntity {

 private  Integer userId;

 private  Integer roleId;

 private  UserEntity userByUserId;

 private  RoleEntity roleByRoleId;

public UserRoleMapEntity() {
}public UserRoleMapEntity(int roleId, int userId) {
    this.roleId = roleId;
    this.userId = userId;
}
public void setRoleId(Integer roleId){
    this.roleId = roleId;
}


public void setUserByUserId(UserEntity userByUserId){
    this.userByUserId = userByUserId;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    return result;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    UserRoleMapEntity other = (UserRoleMapEntity) obj;
    if (roleId == null) {
        if (other.roleId != null)
            return false;
    } else if (!roleId.equals(other.roleId))
        return false;
    if (userId == null) {
        if (other.userId != null)
            return false;
    } else if (!userId.equals(other.userId))
        return false;
    return true;
}


public void setRoleByRoleId(RoleEntity roleByRoleId){
    this.roleByRoleId = roleByRoleId;
}


@Id
@Column(name = "role_id", nullable = false)
public Integer getRoleId(){
    return roleId;
}


@ManyToOne
@JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false, updatable = false, nullable = false)
@JsonBackReference(value = "role-userrolemap")
public RoleEntity getRoleByRoleId(){
    return roleByRoleId;
}


@ManyToOne
@JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false, nullable = false)
@JsonBackReference(value = "user-userrolemap")
public UserEntity getUserByUserId(){
    return userByUserId;
}


@Id
@Column(name = "user_id", nullable = false)
public Integer getUserId(){
    return userId;
}


public void setUserId(Integer userId){
    this.userId = userId;
}


}