package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AdminServiceController {

 private AdminService adminservice;


@GetMapping
("/loggedAdmin")
public Admin loggedAdmin(){
  return adminservice.loggedAdmin();
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
("/loggedAsAdmin")
public void loggedAsAdmin(){
adminservice.loggedAsAdmin();
}


}