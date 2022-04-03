package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ImageServiceController {

 private ImageService imageservice;


@GetMapping
("/findImageUrlById")
public String findImageUrlById(@RequestParam(name = "id") long id,@RequestParam(name = "dataSource") String dataSource){
  return imageservice.findImageUrlById(id,dataSource);
}


@GetMapping
("/findGameImageUrlsByGameId")
public List<String> findGameImageUrlsByGameId(@RequestParam(name = "gameId") long gameId){
  return imageservice.findGameImageUrlsByGameId(gameId);
}


@GetMapping
("/addImage")
public Long addImage(@RequestParam(name = "image") Image image){
  return imageservice.addImage(image);
}


@GetMapping
("/findImageById")
public Image findImageById(@RequestParam(name = "id") long id){
  return imageservice.findImageById(id);
}


}