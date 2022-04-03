package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.Club;
@RestController
@CrossOrigin
public class ClubPlayerController {

@Autowired
 private ClubPlayerService clubplayerservice;


@GetMapping
("/Player/{id}/Club/getClubId")
public Club getClubId(@PathVariable(name="id") Integer clubIdv2){
return clubplayerservice.getClubId(clubIdv2);
}


@PutMapping
("/Player/{id}/Club/setClubId")
public void setClubId(@PathVariable(name="id") Integer clubIdv2,@RequestBody Club clubId){
clubplayerservice.setClubId(clubIdv2,clubId);
}


}