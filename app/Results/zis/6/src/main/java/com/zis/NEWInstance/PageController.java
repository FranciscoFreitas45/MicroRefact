package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PageController {

 private Page page;

 private Page page;


@GetMapping
("/isHasPre")
public boolean isHasPre(){
  return page.isHasPre();
}


@GetMapping
("/isHasNext")
public boolean isHasNext(){
  return page.isHasNext();
}


}