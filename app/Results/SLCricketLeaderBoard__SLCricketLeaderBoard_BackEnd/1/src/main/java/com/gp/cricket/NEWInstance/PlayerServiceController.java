package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PlayerServiceController {

 private PlayerService playerservice;


@GetMapping
("/getPlayer")
public Player getPlayer(@RequestParam(name = "playerId") Integer playerId){
  return playerservice.getPlayer(playerId);
}


}