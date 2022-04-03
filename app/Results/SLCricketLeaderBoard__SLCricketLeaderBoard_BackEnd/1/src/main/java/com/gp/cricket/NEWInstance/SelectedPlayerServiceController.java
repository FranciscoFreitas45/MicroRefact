package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SelectedPlayerServiceController {

 private SelectedPlayerService selectedplayerservice;


@GetMapping
("/saveSelectedPlayer")
public SelectedPlayer saveSelectedPlayer(@RequestParam(name = "player") SelectedPlayer player){
  return selectedplayerservice.saveSelectedPlayer(player);
}


}