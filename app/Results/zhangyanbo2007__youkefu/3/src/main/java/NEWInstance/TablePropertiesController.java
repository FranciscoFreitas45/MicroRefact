package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TablePropertiesController {

 private TableProperties tableproperties;


@GetMapping
("/isModits")
public boolean isModits(){
  return tableproperties.isModits();
}


@GetMapping
("/isSeldata")
public boolean isSeldata(){
  return tableproperties.isSeldata();
}


@GetMapping
("/isReffk")
public boolean isReffk(){
  return tableproperties.isReffk();
}


@GetMapping
("/isPk")
public boolean isPk(){
  return tableproperties.isPk();
}


@GetMapping
("/isSecfield")
public boolean isSecfield(){
  return tableproperties.isSecfield();
}


@PutMapping
("/setOrgi")
public void setOrgi(@RequestParam(name = "orgi") String orgi){
tableproperties.setOrgi(orgi);
}


@PutMapping
("/setDatatypecode")
public void setDatatypecode(@RequestParam(name = "datatypecode") int datatypecode){
tableproperties.setDatatypecode(datatypecode);
}


@PutMapping
("/setLength")
public void setLength(@RequestParam(name = "length") int length){
tableproperties.setLength(length);
}


@PutMapping
("/setDatatypename")
public void setDatatypename(@RequestParam(name = "datatypename") String datatypename){
tableproperties.setDatatypename(datatypename);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
tableproperties.setName(name);
}


@PutMapping
("/setSysfield")
public void setSysfield(@RequestParam(name = "sysfield") boolean sysfield){
tableproperties.setSysfield(sysfield);
}


}