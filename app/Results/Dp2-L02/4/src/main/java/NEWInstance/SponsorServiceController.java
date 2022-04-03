package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SponsorServiceController {

 private SponsorService sponsorservice;


@GetMapping
("/loggedSponsor")
public Sponsor loggedSponsor(){
  return sponsorservice.loggedSponsor();
}


@GetMapping
("/reconstructSponsor")
public Sponsor reconstructSponsor(@RequestParam(name = "Sponsor") Sponsor Sponsor,@RequestParam(name = "binding") BindingResult binding){
  return sponsorservice.reconstructSponsor(Sponsor,binding);
}


@GetMapping
("/save")
public Sponsor save(@RequestParam(name = "h") Sponsor h){
  return sponsorservice.save(h);
}


@PutMapping
("/deleteSponsor")
public void deleteSponsor(){
sponsorservice.deleteSponsor();
}


@GetMapping
("/createSponsor")
public Sponsor createSponsor(){
  return sponsorservice.createSponsor();
}


@GetMapping
("/reconstruct")
public Sponsor reconstruct(@RequestParam(name = "formObjectSponsor") FormObjectSponsor formObjectSponsor,@RequestParam(name = "binding") BindingResult binding){
  return sponsorservice.reconstruct(formObjectSponsor,binding);
}


@GetMapping
("/saveCreate")
public Sponsor saveCreate(@RequestParam(name = "bro") Sponsor bro){
  return sponsorservice.saveCreate(bro);
}


}