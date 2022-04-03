package com.hmm.DTO;
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


@OneToMany(targetEntity = Department.class, cascade = CascadeType.MERGE, mappedBy = "departmentParent", fetch = FetchType.LAZY)
public List<Department> getChildDepartment(){
    return childDepartment;
}


public Integer getManagerId(){
    return managerId;
}


public String getDeptNo(){
    return deptNo;
}


@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
@JoinColumn(name = "parent_id")
public Department getDepartmentParent(){
    return departmentParent;
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


public String getManagerNo(){
    return managerNo;
}


@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "department")
public List<GroupRole> getGroupRoles(){
    return groupRoles;
}


public String getDeptName(){
    return deptName;
}


@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "departmentes")
public Set<Employee> getEmployee(){
    return employ;
}


}