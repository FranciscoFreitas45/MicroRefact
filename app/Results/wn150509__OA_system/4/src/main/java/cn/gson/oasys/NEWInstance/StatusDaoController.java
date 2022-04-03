package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StatusDaoController {

 private StatusDao statusdao;


@GetMapping
("/findByStatusModel")
public List<SystemStatusList> findByStatusModel(@RequestParam(name = "statusModel") String statusModel){
  return statusdao.findByStatusModel(statusModel);
}


@GetMapping
("/findByStatusModelAndStatusName")
public SystemStatusList findByStatusModelAndStatusName(@RequestParam(name = "statusModel") String statusModel,@RequestParam(name = "statusName") String statusName){
  return statusdao.findByStatusModelAndStatusName(statusModel,statusName);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return statusdao.findOne(Object);
}


@GetMapping
("/findname")
public String findname(@RequestParam(name = "id") Long id){
  return statusdao.findname(id);
}


@GetMapping
("/findcolor")
public String findcolor(@RequestParam(name = "id") Long id){
  return statusdao.findcolor(id);
}


}