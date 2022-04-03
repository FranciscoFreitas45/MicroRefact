package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FileListdaoController {

 private FileListdao filelistdao;


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return filelistdao.count(Object);
}


}