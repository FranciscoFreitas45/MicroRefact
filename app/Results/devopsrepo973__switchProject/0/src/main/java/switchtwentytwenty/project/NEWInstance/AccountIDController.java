package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AccountIDController {

 private AccountID accountid;


@GetMapping
("/equals")
public boolean equals(@RequestParam(name = "o") Object o){
  return accountid.equals(o);
}


@GetMapping
("/toString")
public String toString(){
  return accountid.toString();
}


}