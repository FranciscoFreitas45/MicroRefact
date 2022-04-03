package com.cg.sprint.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MoviesDaoController {

 private MoviesDao moviesdao;


@GetMapping
("/movieNames")
public List<Movies> movieNames(@RequestParam(name = "name") String name){
  return moviesdao.movieNames(name);
}


}