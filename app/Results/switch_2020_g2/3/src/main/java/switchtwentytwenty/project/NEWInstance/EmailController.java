package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmailController {

 private Email email;


@GetMapping
("/equals")
public boolean equals(@RequestParam(name = "object") Object object){
  return email.equals(object);
}


}