package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.department.entity.Department;
@RestController
@CrossOrigin
public class DepartmentGroupRoleController {

@Autowired
 private DepartmentGroupRoleService departmentgrouproleservice;


@GetMapping
("/GroupRole/{id}/Department/getDepartment")
public Department getDepartment(@PathVariable(name="id") Integer dept_idKI54){
return departmentgrouproleservice.getDepartment(dept_idKI54);
}


@PutMapping
("/GroupRole/{id}/Department/setDepartment")
public void setDepartment(@PathVariable(name="id") Integer dept_idKI54,@RequestBody Department department){
departmentgrouproleservice.setDepartment(dept_idKI54,department);
}


}