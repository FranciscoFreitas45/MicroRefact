package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AdminServiceController {

 private AdminService adminservice;


@PutMapping
("/loggedAsAdmin")
public void loggedAsAdmin(){
adminservice.loggedAsAdmin();
}


@GetMapping
("/getSystem")
public Admin getSystem(){
  return adminservice.getSystem();
}


@GetMapping
("/getAdminByUsername")
public Admin getAdminByUsername(@RequestParam(name = "username") String username){
  return adminservice.getAdminByUsername(username);
}


@GetMapping
("/reconstruct")
public Admin reconstruct(@RequestParam(name = "admin") Admin admin,@RequestParam(name = "binding") BindingResult binding){
  return adminservice.reconstruct(admin,binding);
}


@GetMapping
("/updateAdmin")
public Admin updateAdmin(@RequestParam(name = "admin") Admin admin){
  return adminservice.updateAdmin(admin);
}


@PutMapping
("/unBanSuspiciousActor")
public void unBanSuspiciousActor(@RequestParam(name = "a") Actor a){
adminservice.unBanSuspiciousActor(a);
}


@PutMapping
("/banSuspiciousActor")
public void banSuspiciousActor(@RequestParam(name = "a") Actor a){
adminservice.banSuspiciousActor(a);
}


@PutMapping
("/broadcastMessage")
public void broadcastMessage(@RequestParam(name = "message") Message message){
adminservice.broadcastMessage(message);
}


}