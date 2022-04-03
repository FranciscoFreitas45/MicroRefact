package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ApplyController {

 private Apply apply;

 private Apply apply;


@PutMapping
("/setUserid")
public void setUserid(@RequestParam(name = "userid") Integer userid){
apply.setUserid(userid);
}


@PutMapping
("/setDeptid")
public void setDeptid(@RequestParam(name = "deptid") Integer deptid){
apply.setDeptid(deptid);
}


@PutMapping
("/setApplytime")
public void setApplytime(@RequestParam(name = "applytime") Date applytime){
apply.setApplytime(applytime);
}


}