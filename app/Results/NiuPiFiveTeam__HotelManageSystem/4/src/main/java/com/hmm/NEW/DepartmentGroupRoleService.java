package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.department.dao.DeptDao;
import com.hmm.department.entity.Department;
@Service
public class DepartmentGroupRoleService {

@Autowired
 private DeptDao deptdao;


public Department getDepartment(Integer dept_idKI54){
return deptdao.getDepartment(dept_idKI54);
}


public void setDepartment(Integer dept_idKI54,Department department){
deptdao.setDepartment(dept_idKI54,department);
}


}