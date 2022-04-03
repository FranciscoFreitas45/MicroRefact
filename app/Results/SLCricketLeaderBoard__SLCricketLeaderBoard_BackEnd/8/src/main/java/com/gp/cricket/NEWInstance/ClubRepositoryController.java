package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClubRepositoryController {

 private ClubRepository clubrepository;


@GetMapping
("/findClubByClubId")
public Club findClubByClubId(@RequestParam(name = "clubId") Integer clubId){
  return clubrepository.findClubByClubId(clubId);
}


@GetMapping
("/existsById")
public Object existsById(@RequestParam(name = "Object") Object Object){
  return clubrepository.existsById(Object);
}


@GetMapping
("/findClubByManagerId")
public Club findClubByManagerId(@RequestParam(name = "managerId") Integer managerId){
  return clubrepository.findClubByManagerId(managerId);
}


}