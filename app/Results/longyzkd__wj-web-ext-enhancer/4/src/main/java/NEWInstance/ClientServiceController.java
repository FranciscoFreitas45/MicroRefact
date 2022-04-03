package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClientServiceController {

 private ClientService clientservice;


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return clientservice.find(Object);
}


@GetMapping
("/findClientContacts")
public Page<ClientContact> findClientContacts(@RequestParam(name = "page") Page<ClientContact> page,@RequestParam(name = "clientId") String clientId){
  return clientservice.findClientContacts(page,clientId);
}


@GetMapping
("/unique")
public Object unique(@RequestParam(name = "Object") Object Object){
  return clientservice.unique(Object);
}


@GetMapping
("/uniqueEntity")
public Object uniqueEntity(@RequestParam(name = "Object") Object Object){
  return clientservice.uniqueEntity(Object);
}


@PutMapping
("/delThemCascade")
public void delThemCascade(@RequestParam(name = "string") String string,@RequestParam(name = "ids") List<String> ids){
clientservice.delThemCascade(string,ids);
}


@GetMapping
("/add")
public Object add(@RequestParam(name = "Object") Object Object){
  return clientservice.add(Object);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "c") ClientContact c){
clientservice.update(c);
}


@PutMapping
("/save")
public void save(@RequestParam(name = "clientId") String clientId,@RequestParam(name = "data") ClientContact data){
clientservice.save(clientId,data);
}


}