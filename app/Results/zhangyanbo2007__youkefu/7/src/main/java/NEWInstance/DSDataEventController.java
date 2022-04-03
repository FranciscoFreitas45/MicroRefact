package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DSDataEventController {

 private DSDataEvent dsdataevent;


@GetMapping
("/getDSData")
public DSData getDSData(){
  return dsdataevent.getDSData();
}


@GetMapping
("/getTablename")
public String getTablename(){
  return dsdataevent.getTablename();
}


@GetMapping
("/getOrgi")
public String getOrgi(){
  return dsdataevent.getOrgi();
}


@PutMapping
("/setOrgi")
public void setOrgi(@RequestParam(name = "orgi") String orgi){
dsdataevent.setOrgi(orgi);
}


}