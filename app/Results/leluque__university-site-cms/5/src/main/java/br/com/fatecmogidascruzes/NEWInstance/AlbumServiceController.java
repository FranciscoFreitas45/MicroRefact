package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AlbumServiceController {

 private AlbumService albumservice;


@GetMapping
("/getByHash")
public Object getByHash(@RequestParam(name = "Object") Object Object){
  return albumservice.getByHash(Object);
}


@GetMapping
("/getEnabled")
public Object getEnabled(@RequestParam(name = "Object") Object Object){
  return albumservice.getEnabled(Object);
}


}