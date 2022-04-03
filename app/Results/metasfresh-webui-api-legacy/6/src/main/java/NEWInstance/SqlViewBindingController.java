package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SqlViewBindingController {

 private SqlViewBinding sqlviewbinding;

 private SqlViewBinding sqlviewbinding;


@GetMapping
("/tableName")
public Builder tableName(@RequestParam(name = "sqlTableName") String sqlTableName){
  return sqlviewbinding.tableName(sqlTableName);
}


}