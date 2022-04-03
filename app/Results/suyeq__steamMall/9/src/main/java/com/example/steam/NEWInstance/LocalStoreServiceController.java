package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LocalStoreServiceController {

 private LocalStoreService localstoreservice;


@GetMapping
("/get")
public T get(@RequestParam(name = "key") LocalStoreKey key,@RequestParam(name = "tClass") Class<T> tClass,@RequestParam(name = "page") String page){
  return localstoreservice.get(key,tClass,page);
}


@PutMapping
("/set")
public void set(@RequestParam(name = "key") LocalStoreKey key,@RequestParam(name = "value") T value,@RequestParam(name = "page") String page){
localstoreservice.set(key,value,page);
}


}