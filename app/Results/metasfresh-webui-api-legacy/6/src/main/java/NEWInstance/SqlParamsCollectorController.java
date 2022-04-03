package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SqlParamsCollectorController {

 private SqlParamsCollector sqlparamscollector;

 private SqlParamsCollector sqlparamscollector;


@GetMapping
("/toList")
public List<Object> toList(){
  return sqlparamscollector.toList();
}


@GetMapping
("/placeholder")
public String placeholder(@RequestParam(name = "sqlValue") Object sqlValue){
  return sqlparamscollector.placeholder(sqlValue);
}


@PutMapping
("/collect")
public void collect(@RequestParam(name = "from") SqlParamsCollector from){
sqlparamscollector.collect(from);
}


}