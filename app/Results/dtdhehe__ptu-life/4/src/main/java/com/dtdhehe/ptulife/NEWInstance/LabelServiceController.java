package com.dtdhehe.ptulife.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LabelServiceController {

 private LabelService labelservice;


@GetMapping
("/save")
public HotLabel save(@RequestParam(name = "hotLabel") HotLabel hotLabel){
  return labelservice.save(hotLabel);
}


}