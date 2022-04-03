package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GunsPropertiesController {

 private GunsProperties gunsproperties;


@GetMapping
("/getFileUploadPath")
public String getFileUploadPath(){
  return gunsproperties.getFileUploadPath();
}


}