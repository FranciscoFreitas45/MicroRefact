package io.swagger.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SessionController {

 private SessionRepository sessionrepository;


@PutMapping
("/setStatus/{id}")
public void setStatus(@PathVariable(name = "id") Long id,@RequestParam(name = "status") Boolean status){
 sessionrepository.setStatus(id,status);
}


@PutMapping
("/setUser/{id}")
public void setUser(@PathVariable(name = "id") Long id,@RequestParam(name = "user") User user){
 sessionrepository.setUser(id,user);
}


@PutMapping
("/setSessionId/{id}")
public void setSessionId(@PathVariable(name = "id") Long id,@RequestParam(name = "sessionId") String sessionId){
 sessionrepository.setSessionId(id,sessionId);
}


@PutMapping
("/setIpAddress/{id}")
public void setIpAddress(@PathVariable(name = "id") Long id,@RequestParam(name = "ipAddress") String ipAddress){
 sessionrepository.setIpAddress(id,ipAddress);
}


@PutMapping
("/setMedic/{id}")
public void setMedic(@PathVariable(name = "id") Long id,@RequestParam(name = "medic") Medic medic){
 sessionrepository.setMedic(id,medic);
}


}