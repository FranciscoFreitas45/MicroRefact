package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QiniuPropertiesController {

 private QiniuProperties qiniuproperties;


@GetMapping
("/getUrl")
public String getUrl(){
  return qiniuproperties.getUrl();
}


}