package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClubRankingServiceController {

 private ClubRankingService clubrankingservice;


@PutMapping
("/createRankingObject")
public void createRankingObject(@RequestParam(name = "club") Club club){
clubrankingservice.createRankingObject(club);
}


}