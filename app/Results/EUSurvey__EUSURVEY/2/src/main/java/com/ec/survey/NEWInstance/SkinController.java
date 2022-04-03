package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SkinController {

 private Skin skin;

 private Skin skin;


@GetMapping
("/equals")
public boolean equals(@RequestParam(name = "obj") Object obj){
  return skin.equals(obj);
}


}