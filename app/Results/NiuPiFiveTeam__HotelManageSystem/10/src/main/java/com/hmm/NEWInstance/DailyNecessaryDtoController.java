package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DailyNecessaryDtoController {

 private DailyNecessaryDto dailynecessarydto;

 private DailyNecessaryDto dailynecessarydto;


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
dailynecessarydto.setName(name);
}


@PutMapping
("/setNumber")
public void setNumber(@RequestParam(name = "number") int number){
dailynecessarydto.setNumber(number);
}


@PutMapping
("/setShow")
public void setShow(@RequestParam(name = "show") String show){
dailynecessarydto.setShow(show);
}


}