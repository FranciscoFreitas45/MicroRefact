package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CallOutFilterController {

 private CallOutFilter calloutfilter;


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
calloutfilter.setName(name);
}


@PutMapping
("/setExecnum")
public void setExecnum(@RequestParam(name = "execnum") int execnum){
calloutfilter.setExecnum(execnum);
}


@PutMapping
("/setAssigned")
public void setAssigned(@RequestParam(name = "assigned") int assigned){
calloutfilter.setAssigned(assigned);
}


@PutMapping
("/setAssignedorgan")
public void setAssignedorgan(@RequestParam(name = "assignedorgan") int assignedorgan){
calloutfilter.setAssignedorgan(assignedorgan);
}


@PutMapping
("/setAssignedai")
public void setAssignedai(@RequestParam(name = "assignedai") int assignedai){
calloutfilter.setAssignedai(assignedai);
}


@PutMapping
("/setAssignedforecast")
public void setAssignedforecast(@RequestParam(name = "assignedforecast") int assignedforecast){
calloutfilter.setAssignedforecast(assignedforecast);
}


@PutMapping
("/setNotassigned")
public void setNotassigned(@RequestParam(name = "notassigned") int notassigned){
calloutfilter.setNotassigned(notassigned);
}


@PutMapping
("/setRenum")
public void setRenum(@RequestParam(name = "renum") int renum){
calloutfilter.setRenum(renum);
}


@PutMapping
("/setReorgannum")
public void setReorgannum(@RequestParam(name = "reorgannum") int reorgannum){
calloutfilter.setReorgannum(reorgannum);
}


@GetMapping
("/getId")
public String getId(){
  return calloutfilter.getId();
}


@GetMapping
("/getAssigned")
public int getAssigned(){
  return calloutfilter.getAssigned();
}


@GetMapping
("/getNotassigned")
public int getNotassigned(){
  return calloutfilter.getNotassigned();
}


@GetMapping
("/getAssignedorgan")
public int getAssignedorgan(){
  return calloutfilter.getAssignedorgan();
}


@GetMapping
("/getReorgannum")
public int getReorgannum(){
  return calloutfilter.getReorgannum();
}


@GetMapping
("/getAssignedai")
public int getAssignedai(){
  return calloutfilter.getAssignedai();
}


@GetMapping
("/getAssignedforecast")
public int getAssignedforecast(){
  return calloutfilter.getAssignedforecast();
}


@GetMapping
("/getRenum")
public int getRenum(){
  return calloutfilter.getRenum();
}


}