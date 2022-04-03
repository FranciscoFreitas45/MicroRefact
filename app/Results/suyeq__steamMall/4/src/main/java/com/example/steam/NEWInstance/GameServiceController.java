package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GameServiceController {

 private GameService gameservice;


@GetMapping
("/findGameById")
public GameDetail findGameById(@RequestParam(name = "id") long id){
  return gameservice.findGameById(id);
}


@GetMapping
("/updateGamePosterImage")
public int updateGamePosterImage(@RequestParam(name = "gameId") long gameId,@RequestParam(name = "posterImage") long posterImage){
  return gameservice.updateGamePosterImage(gameId,posterImage);
}


@GetMapping
("/findOneGameById")
public Game findOneGameById(@RequestParam(name = "id") long id,@RequestParam(name = "key") String key){
  return gameservice.findOneGameById(id,key);
}


@GetMapping
("/findAllGame")
public List<Game> findAllGame(){
  return gameservice.findAllGame();
}


}