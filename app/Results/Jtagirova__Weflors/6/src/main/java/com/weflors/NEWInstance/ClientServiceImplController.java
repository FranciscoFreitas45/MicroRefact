package com.weflors.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClientServiceImplController {

 private ClientServiceImpl clientserviceimpl;


@GetMapping
("/findAllClients")
public List<ClientEntity> findAllClients(){
  return clientserviceimpl.findAllClients();
}


@GetMapping
("/getClientByClientID")
public ClientEntity getClientByClientID(@RequestParam(name = "clientID") int clientID){
  return clientserviceimpl.getClientByClientID(clientID);
}


}