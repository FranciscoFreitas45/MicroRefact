package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Client;
@RestController
@CrossOrigin
public class ClientAccountController {

@Autowired
 private ClientAccountService clientaccountservice;


@GetMapping
("/Account/{id}/Client/getClients")
public Set<Client> getClients(@PathVariable(name="id") long id){
return clientaccountservice.getClients(id);
}


@PutMapping
("/Account/{id}/Client/setClients")
public void setClients(@PathVariable(name="id") long id,@RequestBody Set<Client> clients){
clientaccountservice.setClients(id,clients);
}


}