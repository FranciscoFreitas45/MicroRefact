package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RuleController {

 private JpaRuleDao jparuledao;


@PutMapping
("/setDateAdded/{id}")
public void setDateAdded(@PathVariable(name = "id") long id,@RequestParam(name = "dateAdded") Date dateAdded){
 jparuledao.setDateAdded(id,dateAdded);
}


}