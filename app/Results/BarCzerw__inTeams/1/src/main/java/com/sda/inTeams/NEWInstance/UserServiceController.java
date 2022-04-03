package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/getByIdOrThrow")
public User getByIdOrThrow(@RequestParam(name = "userId") long userId){
  return userservice.getByIdOrThrow(userId);
}


@GetMapping
("/getAll")
public List<User> getAll(){
  return userservice.getAll();
}


@GetMapping
("/createFromRegister")
public User createFromRegister(@RequestParam(name = "registerDTO") RegisterDto registerDTO){
  return userservice.createFromRegister(registerDTO);
}


@GetMapping
("/getAllMembersOfTeam")
public List<User> getAllMembersOfTeam(@RequestParam(name = "teamId") long teamId){
  return userservice.getAllMembersOfTeam(teamId);
}


@GetMapping
("/getByInvitationCodeOrThrow")
public User getByInvitationCodeOrThrow(@RequestParam(name = "invitationCode") String invitationCode){
  return userservice.getByInvitationCodeOrThrow(invitationCode);
}


}