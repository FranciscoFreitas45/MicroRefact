package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.Club;
@RestController
@CrossOrigin
public class ClubClubRankingController {

@Autowired
 private ClubClubRankingService clubclubrankingservice;


@GetMapping
("/ClubRanking/{id}/Club/getClubId")
public Club getClubId(@PathVariable(name="id") Integer clubIdv2){
return clubclubrankingservice.getClubId(clubIdv2);
}


@PutMapping
("/ClubRanking/{id}/Club/setClubId")
public void setClubId(@PathVariable(name="id") Integer clubIdv2,@RequestBody Club clubId){
clubclubrankingservice.setClubId(clubIdv2,clubId);
}


}