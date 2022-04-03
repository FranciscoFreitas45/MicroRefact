package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TSTypegroupController {

 private TSTypegroup tstypegroup;

 private TSTypegroup tstypegroup;


@PutMapping
("/setTSTypes")
public void setTSTypes(@RequestParam(name = "TSTypes") List<TSType> TSTypes){
tstypegroup.setTSTypes(TSTypes);
}


@PutMapping
("/setTypegroupcode")
public void setTypegroupcode(@RequestParam(name = "typegroupcode") String typegroupcode){
tstypegroup.setTypegroupcode(typegroupcode);
}


@PutMapping
("/setTypegroupname")
public void setTypegroupname(@RequestParam(name = "typegroupname") String typegroupname){
tstypegroup.setTypegroupname(typegroupname);
}


}