package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttachServiceController {

 private AttachService attachservice;


@GetMapping
("/updateatt")
public Integer updateatt(@RequestParam(name = "attname") String attname,@RequestParam(name = "attpath") String attpath,@RequestParam(name = "shu") String shu,@RequestParam(name = "size") Long size,@RequestParam(name = "type") String type,@RequestParam(name = "uptime") Date uptime,@RequestParam(name = "attid") Long attid){
  return attachservice.updateatt(attname,attpath,shu,size,type,uptime,attid);
}


}