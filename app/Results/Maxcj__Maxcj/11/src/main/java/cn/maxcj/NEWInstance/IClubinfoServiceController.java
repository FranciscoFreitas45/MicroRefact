package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IClubinfoServiceController {

 private IClubinfoService iclubinfoservice;


@PutMapping
("/init")
public void init(@RequestParam(name = "deptid") Integer deptid){
iclubinfoservice.init(deptid);
}


@GetMapping
("/getClubInfoByDeptid")
public Clubinfo getClubInfoByDeptid(@RequestParam(name = "deptid") Integer deptid){
  return iclubinfoservice.getClubInfoByDeptid(deptid);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return iclubinfoservice.deleteById(Object);
}


@GetMapping
("/queryClubInfo")
public List<Map<String,Object>> queryClubInfo(@RequestParam(name = "deptid") Integer deptid){
  return iclubinfoservice.queryClubInfo(deptid);
}


}