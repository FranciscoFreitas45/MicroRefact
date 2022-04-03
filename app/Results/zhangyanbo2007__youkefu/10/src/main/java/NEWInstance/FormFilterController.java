package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FormFilterController {

 private FormFilter formfilter;


@GetMapping
("/getFiltertype")
public String getFiltertype(){
  return formfilter.getFiltertype();
}


@GetMapping
("/equals")
public Object equals(@RequestParam(name = "Object") Object Object){
  return formfilter.equals(Object);
}


@GetMapping
("/getTableid")
public String getTableid(){
  return formfilter.getTableid();
}


@GetMapping
("/getBatid")
public String getBatid(){
  return formfilter.getBatid();
}


@GetMapping
("/getId")
public String getId(){
  return formfilter.getId();
}


@GetMapping
("/getExecnum")
public int getExecnum(){
  return formfilter.getExecnum();
}


@PutMapping
("/setExecnum")
public void setExecnum(@RequestParam(name = "execnum") int execnum){
formfilter.setExecnum(execnum);
}


@GetMapping
("/getName")
public String getName(){
  return formfilter.getName();
}


@GetMapping
("/getFilternum")
public int getFilternum(){
  return formfilter.getFilternum();
}


@PutMapping
("/setFilternum")
public void setFilternum(@RequestParam(name = "filternum") int filternum){
formfilter.setFilternum(filternum);
}


}