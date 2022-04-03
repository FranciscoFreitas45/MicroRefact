package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CarModelController {

 private CarModel carmodel;


@PutMapping
("/setBrandId")
public void setBrandId(@RequestParam(name = "brandId") String brandId){
carmodel.setBrandId(brandId);
}


}