package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DelphiMedianController {

 private DelphiMedian delphimedian;

 private DelphiMedian delphimedian;


@PutMapping
("/setMaxDistanceExceeded")
public void setMaxDistanceExceeded(@RequestParam(name = "maxDistanceExceeded") boolean maxDistanceExceeded){
delphimedian.setMaxDistanceExceeded(maxDistanceExceeded);
}


}