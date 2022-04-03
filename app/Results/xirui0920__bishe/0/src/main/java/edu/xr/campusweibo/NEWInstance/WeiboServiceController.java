package edu.xr.campusweibo.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeiboServiceController {

 private WeiboService weiboservice;


@GetMapping
("/getWeiboNum")
public int getWeiboNum(@RequestParam(name = "id") Long id){
  return weiboservice.getWeiboNum(id);
}


@GetMapping
("/getAllWeibo")
public List<Weibo> getAllWeibo(@RequestParam(name = "id") Long id){
  return weiboservice.getAllWeibo(id);
}


}