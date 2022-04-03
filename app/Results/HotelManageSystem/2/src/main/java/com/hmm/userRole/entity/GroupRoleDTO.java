package com.hmm.userRole.entity;
 import org.springframework.beans.BeanUtils;
public class GroupRoleDTO {

 private  Integer groupTable_id;

 private  String groupName;

 private  String groupId;

 private  String deptName;

 private  Float money;


public String getGroupName(){
    return groupName;
}


public void setGroupName(String groupName){
    this.groupName = groupName;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setMoney(Float money){
    this.money = money;
}


public void setGroupId(String groupId){
    this.groupId = groupId;
}


public void entityToDto(GroupRole entity,GroupRoleDTO dto){
    BeanUtils.copyProperties(entity, dto);
}


public void setGroupTable_id(Integer groupTable_id){
    this.groupTable_id = groupTable_id;
}


public Float getMoney(){
    return money;
}


public Integer getGroupTable_id(){
    return groupTable_id;
}


public String getDeptName(){
    return deptName;
}


public String getGroupId(){
    return groupId;
}


public void dtoToEntity(GroupRoleDTO dto,GroupRole entity){
    BeanUtils.copyProperties(dto, entity);
}


}