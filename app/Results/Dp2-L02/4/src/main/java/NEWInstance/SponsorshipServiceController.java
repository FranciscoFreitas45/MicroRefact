package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SponsorshipServiceController {

 private SponsorshipService sponsorshipservice;


@GetMapping
("/getRandomSponsorship")
public Sponsorship getRandomSponsorship(@RequestParam(name = "paradeId") int paradeId){
  return sponsorshipservice.getRandomSponsorship(paradeId);
}


@PutMapping
("/updateSpentMoneyOfSponsorship")
public void updateSpentMoneyOfSponsorship(@RequestParam(name = "paradeId") int paradeId,@RequestParam(name = "sponsorshipId") int sponsorshipId){
sponsorshipservice.updateSpentMoneyOfSponsorship(paradeId,sponsorshipId);
}


@GetMapping
("/findAll")
public List<Sponsorship> findAll(){
  return sponsorshipservice.findAll();
}


@GetMapping
("/isEmpty")
public Object isEmpty(@RequestParam(name = "Object") Object Object){
  return sponsorshipservice.isEmpty(Object);
}


}