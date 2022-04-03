package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BuilderController {

 private Builder builder;

 private Builder builder;


@GetMapping
("/getSqlParentLinkColumnName")
public String getSqlParentLinkColumnName(){
  return builder.getSqlParentLinkColumnName();
}


@GetMapping
("/addField")
public Builder addField(@RequestParam(name = "field") DocumentFieldDataBindingDescriptor field){
  return builder.addField(field);
}


@GetMapping
("/getPOInfo")
public POInfo getPOInfo(){
  return builder.getPOInfo();
}


@GetMapping
("/getTableAlias")
public String getTableAlias(){
  return builder.getTableAlias();
}


@GetMapping
("/getTableName")
public String getTableName(){
  return builder.getTableName();
}


@GetMapping
("/build")
public SqlViewBinding build(){
  return builder.build();
}


}