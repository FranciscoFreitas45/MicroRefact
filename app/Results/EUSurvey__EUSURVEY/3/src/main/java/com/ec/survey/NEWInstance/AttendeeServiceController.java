package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttendeeServiceController {

 private AttendeeService attendeeservice;


@GetMapping
("/getAttributeName")
public AttributeName getAttributeName(@RequestParam(name = "name") String name,@RequestParam(name = "ownerId") int ownerId){
  return attendeeservice.getAttributeName(name,ownerId);
}


@PutMapping
("/add")
public void add(@RequestParam(name = "invitation") Invitation invitation){
attendeeservice.add(invitation);
}


@PutMapping
("/decreaseInvitationAnswer")
public void decreaseInvitationAnswer(@RequestParam(name = "invitationUID") String invitationUID){
attendeeservice.decreaseInvitationAnswer(invitationUID);
}


@GetMapping
("/getInvitationByUniqueId")
public Invitation getInvitationByUniqueId(@RequestParam(name = "uniqueId") String uniqueId){
  return attendeeservice.getInvitationByUniqueId(uniqueId);
}


@PutMapping
("/addTokens")
public void addTokens(@RequestParam(name = "tokens") List<String> tokens,@RequestParam(name = "groupId") Integer groupId){
attendeeservice.addTokens(tokens,groupId);
}


@GetMapping
("/getAttendeesForUser")
public List<Integer> getAttendeesForUser(@RequestParam(name = "userid") int userid){
  return attendeeservice.getAttendeesForUser(userid);
}


@PutMapping
("/delete")
public void delete(@RequestParam(name = "invitation") Invitation invitation){
attendeeservice.delete(invitation);
}


@GetMapping
("/getPassiveShares")
public List<Share> getPassiveShares(@RequestParam(name = "recipientId") int recipientId){
  return attendeeservice.getPassiveShares(recipientId);
}


@PutMapping
("/deleteShare")
public void deleteShare(@RequestParam(name = "id") int id){
attendeeservice.deleteShare(id);
}


@GetMapping
("/getShares")
public List<Share> getShares(@RequestParam(name = "userId") int userId){
  return attendeeservice.getShares(userId);
}


}