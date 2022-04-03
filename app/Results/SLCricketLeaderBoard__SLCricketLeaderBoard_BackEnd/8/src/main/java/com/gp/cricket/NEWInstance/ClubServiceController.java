package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClubServiceController {

 private ClubService clubservice;


@GetMapping
("/getClubData")
public Optional<Club> getClubData(@RequestParam(name = "clubId") Integer clubId){
  return clubservice.getClubData(clubId);
}


}