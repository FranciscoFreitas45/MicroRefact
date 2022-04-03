package com.hmm.department.entity;
 import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.hmm.employee.entity.Employee;
import com.hmm.userRole.entity.GroupRole;
import com.hmm.Request.GroupRoleRequest;
import com.hmm.Request.Impl.GroupRoleRequestImpl;
import com.hmm.DTO.GroupRole;
import com.hmm.Request.EmployeeRequest;
import com.hmm.Request.Impl.EmployeeRequestImpl;
import com.hmm.DTO.Employee;
@Entity
@Table(name = "t_dept")
public class Department {

 private  Integer dept_id;

 private  String deptNo;

 private  String deptName;

 private  String managerNo;

 private  String managerName;

 private  Integer managerId;

 private  Integer is_parent;

 private  Set<Employee> employ;

 private  Department departmentParent;

 private  List<Department> childDepartment;

 private  List<GroupRole> groupRoles;

@Transient
 private GroupRoleRequest grouprolerequest = new GroupRoleRequestImpl();;

@Transient
 private EmployeeRequest employeerequest = new EmployeeRequestImpl();;


@OneToMany(targetEntity = Department.class, cascade = CascadeType.MERGE, mappedBy = "departmentParent", fetch = FetchType.LAZY)
public List<Department> getChildDepartment(){
    return childDepartment;
}


public Integer getManagerId(){
    return managerId;
}


public void setManagerId(Integer managerId){
    this.managerId = managerId;
}


public String getDeptNo(){
    return deptNo;
}


@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
@JoinColumn(name = "parent_id")
public Department getDepartmentParent(){
    return departmentParent;
}


public void setEmployee(Set<Employee> employ){
employeerequest.setEmployee(employ,this.dept_id);
 this.employ = employ;
}



public void setManagerName(String managerName){
    this.managerName = managerName;
}


public Integer getIs_parent(){
    return is_parent;
}


public String getManagerName(){
    return managerName;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Integer getDept_id(){
    return dept_id;
}


public void setIs_parent(Integer is_parent){
    this.is_parent = is_parent;
}


public String getManagerNo(){
    return managerNo;
}


public void setManagerNo(String managerNo){
    this.managerNo = managerNo;
}


@Transient
public List<GroupRole> getGroupRoles(){
  this.groupRoles = grouprolerequest.getGroupRoles(this.dept_id);
return this.groupRoles;
}}



public void setDepartmentParent(Department departmentParent){
    this.departmentParent = departmentParent;
}


public String getDeptName(){
    return deptName;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setDept_id(Integer dept_id){
    this.dept_id = dept_id;
}


@Transient
public Set<Employee> getEmployee(){
  this.employ = employeerequest.getEmployee(this.dept_id);
return this.employ;
}}



public void setDeptNo(String deptNo){
    this.deptNo = deptNo;
}


public void setGroupRoles(List<GroupRole> groupRoles){
grouprolerequest.setGroupRoles(groupRoles,this.dept_id);
 this.groupRoles = groupRoles;
}



public void setChildDepartment(List<Department> childDepartment){
    this.childDepartment = childDepartment;
}


}