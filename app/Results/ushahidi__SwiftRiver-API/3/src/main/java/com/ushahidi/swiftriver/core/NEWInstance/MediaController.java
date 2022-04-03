package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MediaController {

 private JpaMediaDao jpamediadao;


@PutMapping
("/setId/{id}")
public void setId(@PathVariable(name = "id") long id,@RequestParam(name = "id") long id){
 jpamediadao.setId(id,id);
}


@PutMapping
("/setUrl/{id}")
public void setUrl(@PathVariable(name = "id") long id,@RequestParam(name = "url") String url){
 jpamediadao.setUrl(id,url);
}


@PutMapping
("/setType/{id}")
public void setType(@PathVariable(name = "id") long id,@RequestParam(name = "type") String type){
 jpamediadao.setType(id,type);
}


@PutMapping
("/setThumbnails/{id}")
public void setThumbnails(@PathVariable(name = "id") long id,@RequestParam(name = "thumbnails") List<MediaThumbnail> thumbnails){
 jpamediadao.setThumbnails(id,thumbnails);
}


}