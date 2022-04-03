package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QuickTypeController {

 private QuickType quicktype;

 private QuickType quicktype;


@PutMapping
("/setCreater")
public void setCreater(@RequestParam(name = "creater") String creater){
quicktype.setCreater(creater);
}


@PutMapping
("/setCreatetime")
public void setCreatetime(@RequestParam(name = "createtime") Date createtime){
quicktype.setCreatetime(createtime);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
quicktype.setName(name);
}


@PutMapping
("/setDescription")
public void setDescription(@RequestParam(name = "description") String description){
quicktype.setDescription(description);
}


@PutMapping
("/setInx")
public void setInx(@RequestParam(name = "inx") int inx){
quicktype.setInx(inx);
}


}