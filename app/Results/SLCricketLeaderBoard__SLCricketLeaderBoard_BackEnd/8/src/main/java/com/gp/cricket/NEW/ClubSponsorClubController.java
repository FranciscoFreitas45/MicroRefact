package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.Club;
@RestController
@CrossOrigin
public class ClubSponsorClubController {

@Autowired
 private ClubSponsorClubService clubsponsorclubservice;


@PutMapping
("/SponsorClub/{id}/Club/setClubId")
public void setClubId(@PathVariable(name="id") Integer clubIdv2,@RequestBody Club clubId){
clubsponsorclubservice.setClubId(clubIdv2,clubId);
}


@GetMapping
("/SponsorClub/{id}/Club/getClubId")
public Club getClubId(@PathVariable(name="id") Integer clubIdv2){
return clubsponsorclubservice.getClubId(clubIdv2);
}


}