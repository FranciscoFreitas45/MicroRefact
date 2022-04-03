package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AnchorRepositoryController {

 private AnchorRepository anchorrepository;


@GetMapping
("/findAnchorByUser")
public Anchor findAnchorByUser(@RequestParam(name = "user") MobileUser user){
  return anchorrepository.findAnchorByUser(user);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return anchorrepository.save(Object);
}


}