package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CalculateServiceController {

 private CalculateService calculateservice;


@PutMapping
("/formulaCalculate")
public void formulaCalculate(){
calculateservice.formulaCalculate();
}


}