package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LabelServiceController {

 private LabelService labelservice;


@GetMapping
("/deleteGameLabelByGameId")
public int deleteGameLabelByGameId(@RequestParam(name = "gameId") long gameId){
  return labelservice.deleteGameLabelByGameId(gameId);
}


@GetMapping
("/findLabelNamesByGameId")
public List<String> findLabelNamesByGameId(@RequestParam(name = "gameId") long gameId){
  return labelservice.findLabelNamesByGameId(gameId);
}


@GetMapping
("/findAllLabel")
public List<Label> findAllLabel(){
  return labelservice.findAllLabel();
}


}