package edu.xr.campusweibo.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FriendServiceController {

 private FriendService friendservice;


@GetMapping
("/getAllFrid")
public List<Friend> getAllFrid(@RequestParam(name = "id") Long id){
  return friendservice.getAllFrid(id);
}


@GetMapping
("/getAllFans")
public List<Friend> getAllFans(@RequestParam(name = "id") Long id){
  return friendservice.getAllFans(id);
}


}