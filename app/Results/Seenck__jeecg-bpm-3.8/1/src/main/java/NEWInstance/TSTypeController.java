package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TSTypeController {

 private TSType tstype;

 private TSType tstype;


@PutMapping
("/setTSType")
public void setTSType(@RequestParam(name = "TSType") TSType TSType){
tstype.setTSType(TSType);
}


@PutMapping
("/setTSTypegroup")
public void setTSTypegroup(@RequestParam(name = "TSTypegroup") TSTypegroup TSTypegroup){
tstype.setTSTypegroup(TSTypegroup);
}


@PutMapping
("/setTSTypes")
public void setTSTypes(@RequestParam(name = "TSTypes") List<TSType> TSTypes){
tstype.setTSTypes(TSTypes);
}


@PutMapping
("/setOrderNum")
public void setOrderNum(@RequestParam(name = "orderNum") Integer orderNum){
tstype.setOrderNum(orderNum);
}


@PutMapping
("/setTypecode")
public void setTypecode(@RequestParam(name = "typecode") String typecode){
tstype.setTypecode(typecode);
}


@PutMapping
("/setTypename")
public void setTypename(@RequestParam(name = "typename") String typename){
tstype.setTypename(typename);
}


}