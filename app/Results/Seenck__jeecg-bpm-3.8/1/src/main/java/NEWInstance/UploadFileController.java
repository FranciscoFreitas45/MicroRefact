package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UploadFileController {

 private UploadFile uploadfile;

 private UploadFile uploadfile;


@PutMapping
("/setExtend")
public void setExtend(@RequestParam(name = "extend") String extend){
uploadfile.setExtend(extend);
}


@PutMapping
("/setTitleField")
public void setTitleField(@RequestParam(name = "titleField") String titleField){
uploadfile.setTitleField(titleField);
}


@PutMapping
("/setRealPath")
public void setRealPath(@RequestParam(name = "realPath") String realPath){
uploadfile.setRealPath(realPath);
}


@PutMapping
("/setObject")
public void setObject(@RequestParam(name = "object") Object object){
uploadfile.setObject(object);
}


@PutMapping
("/setByteField")
public void setByteField(@RequestParam(name = "byteField") String byteField){
uploadfile.setByteField(byteField);
}


@PutMapping
("/setRename")
public void setRename(@RequestParam(name = "rename") boolean rename){
uploadfile.setRename(rename);
}


@PutMapping
("/setBasePath")
public void setBasePath(@RequestParam(name = "basePath") String basePath){
uploadfile.setBasePath(basePath);
}


@GetMapping
("/isRename")
public boolean isRename(){
  return uploadfile.isRename();
}


}