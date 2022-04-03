package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SqlLookupDescriptorController {

 private SqlLookupDescriptor sqllookupdescriptor;

 private SqlLookupDescriptor sqllookupdescriptor;


@GetMapping
("/builder")
public Builder builder(){
  return sqllookupdescriptor.builder();
}


@GetMapping
("/setCtxTableName")
public Builder setCtxTableName(@RequestParam(name = "tableName") String tableName){
  return sqllookupdescriptor.setCtxTableName(tableName);
}


@GetMapping
("/setCtxColumnName")
public Builder setCtxColumnName(@RequestParam(name = "columnName") String columnName){
  return sqllookupdescriptor.setCtxColumnName(columnName);
}


}