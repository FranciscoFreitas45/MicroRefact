package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CacheServiceController {

 private CacheService cacheservice;


@PutMapping
("/clear")
public void clear(){
cacheservice.clear();
}


}