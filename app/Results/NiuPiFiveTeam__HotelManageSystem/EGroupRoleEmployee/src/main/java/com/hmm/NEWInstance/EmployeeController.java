package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeeController {

 private EmployeeDao employeedao;


@PutMapping
("/setEmpName/{id}")
public void setEmpName(@PathVariable(name = "id") Integer id,@RequestParam(name = "empName") String empName){
 employeedao.setEmpName(id,empName);
}


@PutMapping
("/setEmpSex/{id}")
public void setEmpSex(@PathVariable(name = "id") Integer id,@RequestParam(name = "empSex") String empSex){
 employeedao.setEmpSex(id,empSex);
}


@PutMapping
("/setJobtype/{id}")
public void setJobtype(@PathVariable(name = "id") Integer id,@RequestParam(name = "jobtype") String jobtype){
 employeedao.setJobtype(id,jobtype);
}


@PutMapping
("/setTel/{id}")
public void setTel(@PathVariable(name = "id") Integer id,@RequestParam(name = "tel") String tel){
 employeedao.setTel(id,tel);
}


@PutMapping
("/setAddress/{id}")
public void setAddress(@PathVariable(name = "id") Integer id,@RequestParam(name = "address") String address){
 employeedao.setAddress(id,address);
}


@PutMapping
("/setIdcard/{id}")
public void setIdcard(@PathVariable(name = "id") Integer id,@RequestParam(name = "idcard") String idcard){
 employeedao.setIdcard(id,idcard);
}


@PutMapping
("/setEntryDate/{id}")
public void setEntryDate(@PathVariable(name = "id") Integer id,@RequestParam(name = "entryDate") Date entryDate){
 employeedao.setEntryDate(id,entryDate);
}


@PutMapping
("/setIntroduce/{id}")
public void setIntroduce(@PathVariable(name = "id") Integer id,@RequestParam(name = "introduce") String introduce){
 employeedao.setIntroduce(id,introduce);
}


@PutMapping
("/setGroupRoles/{id}")
public void setGroupRoles(@PathVariable(name = "id") Integer id,@RequestParam(name = "groupRoles") List<GroupRole> groupRoles){
 employeedao.setGroupRoles(id,groupRoles);
}


@PutMapping
("/setDepartmentes/{id}")
public void setDepartmentes(@PathVariable(name = "id") Integer id,@RequestParam(name = "departmentes") Department departmentes){
 employeedao.setDepartmentes(id,departmentes);
}


@PutMapping
("/setEmpNo/{id}")
public void setEmpNo(@PathVariable(name = "id") Integer id,@RequestParam(name = "empNo") String empNo){
 employeedao.setEmpNo(id,empNo);
}


@PutMapping
("/setUserName/{id}")
public void setUserName(@PathVariable(name = "id") Integer id,@RequestParam(name = "userName") String userName){
 employeedao.setUserName(id,userName);
}


@PutMapping
("/setEmpImage/{id}")
public void setEmpImage(@PathVariable(name = "id") Integer id,@RequestParam(name = "empImage") String empImage){
 employeedao.setEmpImage(id,empImage);
}


}