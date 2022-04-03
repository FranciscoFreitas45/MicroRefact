package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DropController {

 private JpaDropDao jpadropdao;


@PutMapping
("/setId/{id}")
public void setId(@PathVariable(name = "id") long id,@RequestParam(name = "id") long id){
 jpadropdao.setId(id,id);
}


@PutMapping
("/setTrackingId/{id}")
public void setTrackingId(@PathVariable(name = "id") long id,@RequestParam(name = "trackingId") Long trackingId){
 jpadropdao.setTrackingId(id,trackingId);
}


@PutMapping
("/setChannel/{id}")
public void setChannel(@PathVariable(name = "id") long id,@RequestParam(name = "channel") String channel){
 jpadropdao.setChannel(id,channel);
}


@PutMapping
("/setTitle/{id}")
public void setTitle(@PathVariable(name = "id") long id,@RequestParam(name = "title") String title){
 jpadropdao.setTitle(id,title);
}


@PutMapping
("/setContent/{id}")
public void setContent(@PathVariable(name = "id") long id,@RequestParam(name = "content") String content){
 jpadropdao.setContent(id,content);
}


@PutMapping
("/setDatePublished/{id}")
public void setDatePublished(@PathVariable(name = "id") long id,@RequestParam(name = "dropletDatePub") Date dropletDatePub){
 jpadropdao.setDatePublished(id,dropletDatePub);
}


@PutMapping
("/setOriginalId/{id}")
public void setOriginalId(@PathVariable(name = "id") long id,@RequestParam(name = "originalId") String originalId){
 jpadropdao.setOriginalId(id,originalId);
}


@PutMapping
("/setCommentCount/{id}")
public void setCommentCount(@PathVariable(name = "id") long id,@RequestParam(name = "commentCount") int commentCount){
 jpadropdao.setCommentCount(id,commentCount);
}


@PutMapping
("/setRead/{id}")
public void setRead(@PathVariable(name = "id") long id,@RequestParam(name = "read") Boolean read){
 jpadropdao.setRead(id,read);
}


@PutMapping
("/setOriginalUrl/{id}")
public void setOriginalUrl(@PathVariable(name = "id") long id,@RequestParam(name = "originalUrl") Link originalUrl){
 jpadropdao.setOriginalUrl(id,originalUrl);
}


@PutMapping
("/setIdentity/{id}")
public void setIdentity(@PathVariable(name = "id") long id,@RequestParam(name = "identity") Identity identity){
 jpadropdao.setIdentity(id,identity);
}


@PutMapping
("/setForms/{id}")
public void setForms(@PathVariable(name = "id") long id,@RequestParam(name = "forms") List<DropForm> forms){
 jpadropdao.setForms(id,forms);
}


@PutMapping
("/setBuckets/{id}")
public void setBuckets(@PathVariable(name = "id") long id,@RequestParam(name = "buckets") List<Bucket> buckets){
 jpadropdao.setBuckets(id,buckets);
}


@PutMapping
("/setPlaces/{id}")
public void setPlaces(@PathVariable(name = "id") long id,@RequestParam(name = "places") List<Place> places){
 jpadropdao.setPlaces(id,places);
}


@PutMapping
("/setMedia/{id}")
public void setMedia(@PathVariable(name = "id") long id,@RequestParam(name = "media") List<Media> media){
 jpadropdao.setMedia(id,media);
}


@PutMapping
("/setImage/{id}")
public void setImage(@PathVariable(name = "id") long id,@RequestParam(name = "image") Media image){
 jpadropdao.setImage(id,image);
}


@PutMapping
("/setLinks/{id}")
public void setLinks(@PathVariable(name = "id") long id,@RequestParam(name = "links") List<Link> links){
 jpadropdao.setLinks(id,links);
}


@PutMapping
("/setTags/{id}")
public void setTags(@PathVariable(name = "id") long id,@RequestParam(name = "tags") List<Tag> tags){
 jpadropdao.setTags(id,tags);
}


}