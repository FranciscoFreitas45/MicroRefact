package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FamilyIDController {

 private FamilyID familyid;


@GetMapping
("/equals")
public boolean equals(@RequestParam(name = "other") Object other){
  return familyid.equals(other);
}


@GetMapping
("/toString")
public String toString(){
  return familyid.toString();
}


}