package br.com.wtag.lottery.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BetsRepositoryController {

 private BetsRepository betsrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return betsrepository.save(Object);
}


@GetMapping
("/findByEmailOrderByRegisteredAsc")
public List<Bets> findByEmailOrderByRegisteredAsc(@RequestParam(name = "email") String email){
  return betsrepository.findByEmailOrderByRegisteredAsc(email);
}


}