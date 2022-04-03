package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SystemServiceController {

 private SystemService systemservice;


@GetMapping
("/getDataGridReturn")
public Object getDataGridReturn(@RequestParam(name = "Object") Object Object){
  return systemservice.getDataGridReturn(Object);
}


@GetMapping
("/getEntity")
public Object getEntity(@RequestParam(name = "Object") Object Object){
  return systemservice.getEntity(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return systemservice.delete(Object);
}


@PutMapping
("/addLog")
public void addLog(@RequestParam(name = "LogContent") String LogContent,@RequestParam(name = "operatetype") Short operatetype,@RequestParam(name = "loglevel") Short loglevel){
systemservice.addLog(LogContent,operatetype,loglevel);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return systemservice.save(Object);
}


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return systemservice.get(Object);
}


@GetMapping
("/saveOrUpdate")
public Object saveOrUpdate(@RequestParam(name = "Object") Object Object){
  return systemservice.saveOrUpdate(Object);
}


@GetMapping
("/getList")
public Object getList(@RequestParam(name = "Object") Object Object){
  return systemservice.getList(Object);
}


@GetMapping
("/findHql")
public Object findHql(@RequestParam(name = "Object") Object Object){
  return systemservice.findHql(Object);
}


@GetMapping
("/findForJdbc")
public Object findForJdbc(@RequestParam(name = "Object") Object Object){
  return systemservice.findForJdbc(Object);
}


@GetMapping
("/executeSql")
public Object executeSql(@RequestParam(name = "Object") Object Object){
  return systemservice.executeSql(Object);
}


@GetMapping
("/findUniqueByProperty")
public Object findUniqueByProperty(@RequestParam(name = "Object") Object Object){
  return systemservice.findUniqueByProperty(Object);
}


@GetMapping
("/getCountForJdbcParam")
public Object getCountForJdbcParam(@RequestParam(name = "Object") Object Object){
  return systemservice.getCountForJdbcParam(Object);
}


}