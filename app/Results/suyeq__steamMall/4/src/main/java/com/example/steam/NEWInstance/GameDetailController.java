package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GameDetailController {

 private GameDetail gamedetail;

 private GameDetail gamedetail;


@PutMapping
("/setLabel")
public void setLabel(@RequestParam(name = "label") List<String> label){
gamedetail.setLabel(label);
}


}