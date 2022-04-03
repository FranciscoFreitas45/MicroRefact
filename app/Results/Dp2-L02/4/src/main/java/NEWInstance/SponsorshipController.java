package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SponsorshipController {

 private Sponsorship sponsorship;

 private Sponsorship sponsorship;


@PutMapping
("/setParade")
public void setParade(@RequestParam(name = "parade") Parade parade){
sponsorship.setParade(parade);
}


}