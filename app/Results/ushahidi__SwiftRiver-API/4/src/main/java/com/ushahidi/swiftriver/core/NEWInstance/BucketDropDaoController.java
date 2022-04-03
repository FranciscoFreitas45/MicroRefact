package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BucketDropDaoController {

 private BucketDropDao bucketdropdao;


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return bucketdropdao.delete(Object);
}


@PutMapping
("/increaseVeracity")
public void increaseVeracity(@RequestParam(name = "bucketDrop") BucketDrop bucketDrop){
bucketdropdao.increaseVeracity(bucketDrop);
}


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return bucketdropdao.create(Object);
}


@GetMapping
("/findTag")
public BucketDropTag findTag(@RequestParam(name = "bucketDrop") BucketDrop bucketDrop,@RequestParam(name = "tag") Tag tag){
  return bucketdropdao.findTag(bucketDrop,tag);
}


@PutMapping
("/addTag")
public void addTag(@RequestParam(name = "bucketDrop") BucketDrop bucketDrop,@RequestParam(name = "tag") Tag tag){
bucketdropdao.addTag(bucketDrop,tag);
}


@GetMapping
("/deleteTag")
public boolean deleteTag(@RequestParam(name = "bucketDrop") BucketDrop bucketDrop,@RequestParam(name = "tag") Tag tag){
  return bucketdropdao.deleteTag(bucketDrop,tag);
}


@GetMapping
("/findLink")
public BucketDropLink findLink(@RequestParam(name = "bucketDrop") BucketDrop bucketDrop,@RequestParam(name = "link") Link link){
  return bucketdropdao.findLink(bucketDrop,link);
}


@PutMapping
("/addLink")
public void addLink(@RequestParam(name = "bucketDrop") BucketDrop bucketDrop,@RequestParam(name = "link") Link link){
bucketdropdao.addLink(bucketDrop,link);
}


@GetMapping
("/deleteLink")
public boolean deleteLink(@RequestParam(name = "bucketDrop") BucketDrop bucketDrop,@RequestParam(name = "link") Link link){
  return bucketdropdao.deleteLink(bucketDrop,link);
}


@GetMapping
("/findPlace")
public BucketDropPlace findPlace(@RequestParam(name = "bucketDrop") BucketDrop bucketDrop,@RequestParam(name = "place") Place place){
  return bucketdropdao.findPlace(bucketDrop,place);
}


@PutMapping
("/addPlace")
public void addPlace(@RequestParam(name = "bucketDrop") BucketDrop bucketDrop,@RequestParam(name = "place") Place place){
bucketdropdao.addPlace(bucketDrop,place);
}


@GetMapping
("/deletePlace")
public boolean deletePlace(@RequestParam(name = "bucketDrop") BucketDrop bucketDrop,@RequestParam(name = "place") Place place){
  return bucketdropdao.deletePlace(bucketDrop,place);
}


@GetMapping
("/addComment")
public BucketDropComment addComment(@RequestParam(name = "bucketDrop") BucketDrop bucketDrop,@RequestParam(name = "account") Account account,@RequestParam(name = "commentText") String commentText){
  return bucketdropdao.addComment(bucketDrop,account,commentText);
}


@GetMapping
("/deleteComment")
public boolean deleteComment(@RequestParam(name = "commentId") Long commentId){
  return bucketdropdao.deleteComment(commentId);
}


@GetMapping
("/findForm")
public BucketDropForm findForm(@RequestParam(name = "dropId") Long dropId,@RequestParam(name = "formId") Long formId){
  return bucketdropdao.findForm(dropId,formId);
}


@GetMapping
("/isRead")
public boolean isRead(@RequestParam(name = "bucketDrop") BucketDrop bucketDrop,@RequestParam(name = "account") Account account){
  return bucketdropdao.isRead(bucketDrop,account);
}


@GetMapping
("/populateMetadata")
public Object populateMetadata(@RequestParam(name = "Object") Object Object){
  return bucketdropdao.populateMetadata(Object);
}


}