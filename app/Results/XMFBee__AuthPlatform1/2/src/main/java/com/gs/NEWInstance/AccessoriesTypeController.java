package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AccessoriesTypeController {

 private AccessoriesType accessoriestype;


@PutMapping
("/setAccTypeName")
public void setAccTypeName(@RequestParam(name = "accTypeName") String accTypeName){
accessoriestype.setAccTypeName(accTypeName);
}


}