package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.Dept;
@RestController
@CrossOrigin
public class DeptUserController {

@Autowired
 private DeptUserService deptuserservice;


@PutMapping
("/User/{id}/Dept/setDept")
public void setDept(@PathVariable(name="id") Long deptId,@RequestBody Dept dept){
deptuserservice.setDept(deptId,dept);
}


@GetMapping
("/User/{id}/Dept/getDept")
public Dept getDept(@PathVariable(name="id") Long deptId){
return deptuserservice.getDept(deptId);
}


}