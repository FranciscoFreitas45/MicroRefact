package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RiverTagTrendController {

 private JpaRiverDao jpariverdao;


@PutMapping
("/setCount/{id}")
public void setCount(@PathVariable(name = "id") long id,@RequestParam(name = "count") long count){
 jpariverdao.setCount(id,count);
}


@PutMapping
("/setRiver/{id}")
public void setRiver(@PathVariable(name = "id") long id,@RequestParam(name = "river") River river){
 jpariverdao.setRiver(id,river);
}


@PutMapping
("/setDatePublished/{id}")
public void setDatePublished(@PathVariable(name = "id") long id,@RequestParam(name = "datePublished") Date datePublished){
 jpariverdao.setDatePublished(id,datePublished);
}


@PutMapping
("/setTag/{id}")
public void setTag(@PathVariable(name = "id") long id,@RequestParam(name = "tag") String tag){
 jpariverdao.setTag(id,tag);
}


@PutMapping
("/setTagType/{id}")
public void setTagType(@PathVariable(name = "id") long id,@RequestParam(name = "tagType") String tagType){
 jpariverdao.setTagType(id,tagType);
}


@PutMapping
("/setHash/{id}")
public void setHash(@PathVariable(name = "id") long id,@RequestParam(name = "hash") String hash){
 jpariverdao.setHash(id,hash);
}


}