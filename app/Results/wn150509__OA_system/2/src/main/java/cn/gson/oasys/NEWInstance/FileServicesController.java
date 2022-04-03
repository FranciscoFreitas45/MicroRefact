package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FileServicesController {

 private FileServices fileservices;


@GetMapping
("/savefile")
public Object savefile(@RequestParam(name = "file") MultipartFile file,@RequestParam(name = "user") User user,@RequestParam(name = "nowpath") FilePath nowpath,@RequestParam(name = "isfile") boolean isfile){
  return fileservices.savefile(file,user,nowpath,isfile);
}


@GetMapping
("/updateatt")
public Integer updateatt(@RequestParam(name = "file") MultipartFile file,@RequestParam(name = "user") User user,@RequestParam(name = "nowpath") FilePath nowpath,@RequestParam(name = "attid") long attid){
  return fileservices.updateatt(file,user,nowpath,attid);
}


@GetMapping
("/get")
public Attachment get(@RequestParam(name = "filePath") String filePath){
  return fileservices.get(filePath);
}


}