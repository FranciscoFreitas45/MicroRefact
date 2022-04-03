package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.department.dao.DeptDao;
import com.hmm.department.entity.Department;
@Service
public class DepartmentEmployeeService {

@Autowired
 private DeptDao deptdao;


public void setDepartmentes(Integer dept_idX1TV,Department departmentes){
deptdao.setDepartmentes(dept_idX1TV,departmentes);
}


public Department getDepartmentes(Integer dept_idX1TV){
return deptdao.getDepartmentes(dept_idX1TV);
}


}