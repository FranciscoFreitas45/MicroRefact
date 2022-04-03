package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Department;
import com.hmm.Request.DepartmentRequest;
public class DepartmentRequestImpl implements DepartmentRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setDepartmentes(Department departmentes,Integer dept_idX1TV){
 restTemplate.put("http://4/Employee/{id}/Department/setDepartmentes",departmentes,dept_idX1TV);
 return ;
}


public Department getDepartmentes(Integer dept_idX1TV){
 Department aux = restTemplate.getForObject("http://4/Employee/{id}/Department/getDepartmentes",Department.class,dept_idX1TV);
return aux;
}


}