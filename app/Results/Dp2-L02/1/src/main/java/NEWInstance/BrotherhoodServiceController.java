package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BrotherhoodServiceController {

 private BrotherhoodService brotherhoodservice;


@PutMapping
("/loggedAsBrotherhood")
public void loggedAsBrotherhood(){
brotherhoodservice.loggedAsBrotherhood();
}


@GetMapping
("/loggedBrotherhood")
public Brotherhood loggedBrotherhood(){
  return brotherhoodservice.loggedBrotherhood();
}


@GetMapping
("/save")
public Brotherhood save(@RequestParam(name = "h") Brotherhood h){
  return brotherhoodservice.save(h);
}


@GetMapping
("/securityAndBrotherhood")
public Brotherhood securityAndBrotherhood(){
  return brotherhoodservice.securityAndBrotherhood();
}


@GetMapping
("/findAll")
public List<Brotherhood> findAll(){
  return brotherhoodservice.findAll();
}


@GetMapping
("/createBrotherhood")
public Brotherhood createBrotherhood(){
  return brotherhoodservice.createBrotherhood();
}


@GetMapping
("/reconstruct")
public Brotherhood reconstruct(@RequestParam(name = "formObjectBrotherhood") FormObjectBrotherhood formObjectBrotherhood,@RequestParam(name = "binding") BindingResult binding){
  return brotherhoodservice.reconstruct(formObjectBrotherhood,binding);
}


@GetMapping
("/saveCreate")
public Brotherhood saveCreate(@RequestParam(name = "bro") Brotherhood bro){
  return brotherhoodservice.saveCreate(bro);
}


@GetMapping
("/reconstructArea")
public Brotherhood reconstructArea(@RequestParam(name = "brotherhood") Brotherhood brotherhood,@RequestParam(name = "binding") BindingResult binding){
  return brotherhoodservice.reconstructArea(brotherhood,binding);
}


@GetMapping
("/updateBrotherhood")
public Brotherhood updateBrotherhood(@RequestParam(name = "brotherhood") Brotherhood brotherhood){
  return brotherhoodservice.updateBrotherhood(brotherhood);
}


@PutMapping
("/deleteBrotherhood")
public void deleteBrotherhood(){
brotherhoodservice.deleteBrotherhood();
}


@GetMapping
("/findOne")
public Brotherhood findOne(@RequestParam(name = "id") int id){
  return brotherhoodservice.findOne(id);
}


}