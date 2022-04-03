package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SNSAccountRepositoryController {

 private SNSAccountRepository snsaccountrepository;


@GetMapping
("/findBySnsid")
public SNSAccount findBySnsid(@RequestParam(name = "snsid") String snsid){
  return snsaccountrepository.findBySnsid(snsid);
}


@GetMapping
("/findBySnsidAndOrgi")
public SNSAccount findBySnsidAndOrgi(@RequestParam(name = "snsid") String snsid,@RequestParam(name = "orgi") String orgi){
  return snsaccountrepository.findBySnsidAndOrgi(snsid,orgi);
}


}