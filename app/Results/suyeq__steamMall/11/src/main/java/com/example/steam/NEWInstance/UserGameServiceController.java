package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserGameServiceController {

 private UserGameService usergameservice;


@GetMapping
("/findUserGameListByEmail")
public List<UserGame> findUserGameListByEmail(@RequestParam(name = "email") String email){
  return usergameservice.findUserGameListByEmail(email);
}


@GetMapping
("/findGamesIdByEmail")
public List<Long> findGamesIdByEmail(@RequestParam(name = "email") String email){
  return usergameservice.findGamesIdByEmail(email);
}


@GetMapping
("/isContains")
public boolean isContains(@RequestParam(name = "email") String email,@RequestParam(name = "gameId") long gameId){
  return usergameservice.isContains(email,gameId);
}


@GetMapping
("/findThreeRecentGameVoListByEmail")
public List<RecentGameVo> findThreeRecentGameVoListByEmail(@RequestParam(name = "email") String email){
  return usergameservice.findThreeRecentGameVoListByEmail(email);
}


@GetMapping
("/findOneUserGameByEmailAndGameId")
public UserGame findOneUserGameByEmailAndGameId(@RequestParam(name = "email") String email,@RequestParam(name = "gameId") long gameId){
  return usergameservice.findOneUserGameByEmailAndGameId(email,gameId);
}


}