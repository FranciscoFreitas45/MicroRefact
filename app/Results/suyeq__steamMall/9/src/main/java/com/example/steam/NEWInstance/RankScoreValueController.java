package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RankScoreValueController {

 private RankScoreValue rankscorevalue;

 private RankScoreValue rankscorevalue;


@PutMapping
("/setScore")
public void setScore(@RequestParam(name = "score") long score){
rankscorevalue.setScore(score);
}


@PutMapping
("/setValue")
public void setValue(@RequestParam(name = "value") T value){
rankscorevalue.setValue(value);
}


}