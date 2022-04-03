package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.Club;
@RestController
@CrossOrigin
public class ClubTournamentClubController {

@Autowired
 private ClubTournamentClubService clubtournamentclubservice;


@PutMapping
("/TournamentClub/{id}/Club/setClubId")
public void setClubId(@PathVariable(name="id") Integer clubIdv2,@RequestBody Club clubId){
clubtournamentclubservice.setClubId(clubIdv2,clubId);
}


@GetMapping
("/TournamentClub/{id}/Club/getClubId")
public Club getClubId(@PathVariable(name="id") Integer clubIdv2){
return clubtournamentclubservice.getClubId(clubIdv2);
}


}