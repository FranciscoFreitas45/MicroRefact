package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CallOutTaskController {

 private CallOutTask callouttask;


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
callouttask.setName(name);
}


@PutMapping
("/setBatid")
public void setBatid(@RequestParam(name = "batid") String batid){
callouttask.setBatid(batid);
}


@PutMapping
("/setOrgi")
public void setOrgi(@RequestParam(name = "orgi") String orgi){
callouttask.setOrgi(orgi);
}


@PutMapping
("/setExectype")
public void setExectype(@RequestParam(name = "exectype") String exectype){
callouttask.setExectype(exectype);
}


@PutMapping
("/setFilterid")
public void setFilterid(@RequestParam(name = "filterid") String filterid){
callouttask.setFilterid(filterid);
}


@PutMapping
("/setActid")
public void setActid(@RequestParam(name = "actid") String actid){
callouttask.setActid(actid);
}


@PutMapping
("/setExecnum")
public void setExecnum(@RequestParam(name = "execnum") int execnum){
callouttask.setExecnum(execnum);
}


@PutMapping
("/setOrgan")
public void setOrgan(@RequestParam(name = "organ") String organ){
callouttask.setOrgan(organ);
}


@PutMapping
("/setCreatetime")
public void setCreatetime(@RequestParam(name = "createtime") Date createtime){
callouttask.setCreatetime(createtime);
}


@PutMapping
("/setNamenum")
public void setNamenum(@RequestParam(name = "namenum") int namenum){
callouttask.setNamenum(namenum);
}


@PutMapping
("/setNotassigned")
public void setNotassigned(@RequestParam(name = "notassigned") int notassigned){
callouttask.setNotassigned(notassigned);
}


@PutMapping
("/setAssigned")
public void setAssigned(@RequestParam(name = "assigned") int assigned){
callouttask.setAssigned(assigned);
}


@PutMapping
("/setAssignedorgan")
public void setAssignedorgan(@RequestParam(name = "assignedorgan") int assignedorgan){
callouttask.setAssignedorgan(assignedorgan);
}


@PutMapping
("/setAssignedai")
public void setAssignedai(@RequestParam(name = "assignedai") int assignedai){
callouttask.setAssignedai(assignedai);
}


@PutMapping
("/setAssignedforecast")
public void setAssignedforecast(@RequestParam(name = "assignedforecast") int assignedforecast){
callouttask.setAssignedforecast(assignedforecast);
}


@GetMapping
("/getNamenum")
public int getNamenum(){
  return callouttask.getNamenum();
}


@PutMapping
("/setRenum")
public void setRenum(@RequestParam(name = "renum") int renum){
callouttask.setRenum(renum);
}


@PutMapping
("/setReorgannum")
public void setReorgannum(@RequestParam(name = "reorgannum") int reorgannum){
callouttask.setReorgannum(reorgannum);
}


@GetMapping
("/getId")
public String getId(){
  return callouttask.getId();
}


@GetMapping
("/getAssigned")
public int getAssigned(){
  return callouttask.getAssigned();
}


@GetMapping
("/getNotassigned")
public int getNotassigned(){
  return callouttask.getNotassigned();
}


@GetMapping
("/getAssignedorgan")
public int getAssignedorgan(){
  return callouttask.getAssignedorgan();
}


@GetMapping
("/getAssignedai")
public int getAssignedai(){
  return callouttask.getAssignedai();
}


@GetMapping
("/getAssignedforecast")
public int getAssignedforecast(){
  return callouttask.getAssignedforecast();
}


@GetMapping
("/getRenum")
public int getRenum(){
  return callouttask.getRenum();
}


@GetMapping
("/getReorgannum")
public int getReorgannum(){
  return callouttask.getReorgannum();
}


}