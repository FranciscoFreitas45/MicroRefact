package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IUserServiceController {

 private IUserService iuserservice;


@GetMapping
("/selectById")
public Object selectById(@RequestParam(name = "Object") Object Object){
  return iuserservice.selectById(Object);
}


@GetMapping
("/getByAccount")
public User getByAccount(@RequestParam(name = "account") String account){
  return iuserservice.getByAccount(account);
}


@GetMapping
("/selectUsers")
public List<Map<String,Object>> selectUsers(@RequestParam(name = "dataScope") DataScope dataScope,@RequestParam(name = "name") String name,@RequestParam(name = "beginTime") String beginTime,@RequestParam(name = "endTime") String endTime,@RequestParam(name = "deptid") Integer deptid){
  return iuserservice.selectUsers(dataScope,name,beginTime,endTime,deptid);
}


@GetMapping
("/isSheLian")
public Integer isSheLian(@RequestParam(name = "userId") Integer userId){
  return iuserservice.isSheLian(userId);
}


@GetMapping
("/updateById")
public Object updateById(@RequestParam(name = "Object") Object Object){
  return iuserservice.updateById(Object);
}


}