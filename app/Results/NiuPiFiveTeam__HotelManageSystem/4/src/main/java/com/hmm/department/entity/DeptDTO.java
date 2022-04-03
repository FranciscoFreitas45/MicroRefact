package com.hmm.department.entity;
 import org.springframework.beans.BeanUtils;
public class DeptDTO {

 private  Integer dept_id;

 private  String deptNo;

 private  String deptName;

 private  String managerNo;

 private  String managerName;

 private  Integer managerId;

 private  Integer parentId;

 private  String deptParent;

 private  Integer is_parent;


public Integer getManagerId(){
    return managerId;
}


public void setManagerId(Integer managerId){
    this.managerId = managerId;
}


public String getDeptNo(){
    return deptNo;
}


public void entityToDto(Department entity,DeptDTO dto){
    BeanUtils.copyProperties(entity, dto);
}


public void setManagerName(String managerName){
    this.managerName = managerName;
}


public String getManagerName(){
    return managerName;
}


public Integer getIs_parent(){
    return is_parent;
}


public Integer getDept_id(){
    return dept_id;
}


public String getManagerNo(){
    return managerNo;
}


public void setManagerNo(String managerNo){
    this.managerNo = managerNo;
}


public void setIs_parent(Integer is_parent){
    this.is_parent = is_parent;
}


public void setDeptParent(String deptParent){
    this.deptParent = deptParent;
}


public String getDeptName(){
    return deptName;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public String getDeptParent(){
    return deptParent;
}


public void setDept_id(Integer dept_id){
    this.dept_id = dept_id;
}


public void setParentId(Integer parentId){
    this.parentId = parentId;
}


public void setDeptNo(String deptNo){
    this.deptNo = deptNo;
}


public Integer getParentId(){
    return parentId;
}


public void dtoToEntity(DeptDTO dto,Department entity){
    BeanUtils.copyProperties(dto, entity);
}


}