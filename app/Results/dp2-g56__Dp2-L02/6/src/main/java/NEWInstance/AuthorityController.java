package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AuthorityController {

 private Authority authority;

 private Authority authority;


@GetMapping
("/equals")
public boolean equals(@RequestParam(name = "other") Object other){
  return authority.equals(other);
}


@PutMapping
("/setAuthority")
public void setAuthority(@RequestParam(name = "authority") String authority){
authority.setAuthority(authority);
}


@GetMapping
("/toString")
public String toString(){
  return authority.toString();
}


}