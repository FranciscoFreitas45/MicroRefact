package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QiniuServiceController {

 private QiniuService qiniuservice;


@GetMapping
("/qiniuDelete")
public boolean qiniuDelete(@RequestParam(name = "name") String name){
  return qiniuservice.qiniuDelete(name);
}


@GetMapping
("/token")
public String token(){
  return qiniuservice.token();
}


}