package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LinkController {

 private JpaLinkDao jpalinkdao;


@PutMapping
("/setUrl/{id}")
public void setUrl(@PathVariable(name = "id") long id,@RequestParam(name = "url") String url){
 jpalinkdao.setUrl(id,url);
}


@PutMapping
("/setHash/{id}")
public void setHash(@PathVariable(name = "id") long id,@RequestParam(name = "hash") String hash){
 jpalinkdao.setHash(id,hash);
}


@PutMapping
("/setId/{id}")
public void setId(@PathVariable(name = "id") long id,@RequestParam(name = "id") long id){
 jpalinkdao.setId(id,id);
}


}