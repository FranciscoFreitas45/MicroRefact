package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MediaThumbnailController {

 private JpaMediaDao jpamediadao;


@PutMapping
("/setSize/{id}")
public void setSize(@PathVariable(name = "id") long id,@RequestParam(name = "size") int size){
 jpamediadao.setSize(id,size);
}


@PutMapping
("/setUrl/{id}")
public void setUrl(@PathVariable(name = "id") long id,@RequestParam(name = "url") String url){
 jpamediadao.setUrl(id,url);
}


}