package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.department.entity.Department;
@RestController
@CrossOrigin
public class DepartmentEmployeeController {

@Autowired
 private DepartmentEmployeeService departmentemployeeservice;


@PutMapping
("/Employee/{id}/Department/setDepartmentes")
public void setDepartmentes(@PathVariable(name="id") Integer dept_idX1TV,@RequestBody Department departmentes){
departmentemployeeservice.setDepartmentes(dept_idX1TV,departmentes);
}


@GetMapping
("/Employee/{id}/Department/getDepartmentes")
public Department getDepartmentes(@PathVariable(name="id") Integer dept_idX1TV){
return departmentemployeeservice.getDepartmentes(dept_idX1TV);
}


}