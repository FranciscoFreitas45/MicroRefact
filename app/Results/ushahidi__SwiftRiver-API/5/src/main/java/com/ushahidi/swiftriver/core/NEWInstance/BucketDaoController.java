package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BucketDaoController {

 private BucketDao bucketdao;


@GetMapping
("/findBucketByName")
public Bucket findBucketByName(@RequestParam(name = "account") Account account,@RequestParam(name = "bucketName") String bucketName){
  return bucketdao.findBucketByName(account,bucketName);
}


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return bucketdao.create(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return bucketdao.findById(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return bucketdao.update(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return bucketdao.delete(Object);
}


@GetMapping
("/findCollaborator")
public BucketCollaborator findCollaborator(@RequestParam(name = "bucketId") Long bucketId,@RequestParam(name = "accountId") Long accountId){
  return bucketdao.findCollaborator(bucketId,accountId);
}


@GetMapping
("/addCollaborator")
public BucketCollaborator addCollaborator(@RequestParam(name = "bucket") Bucket bucket,@RequestParam(name = "account") Account account,@RequestParam(name = "readOnly") boolean readOnly){
  return bucketdao.addCollaborator(bucket,account,readOnly);
}


@PutMapping
("/updateCollaborator")
public void updateCollaborator(@RequestParam(name = "collaborator") BucketCollaborator collaborator){
bucketdao.updateCollaborator(collaborator);
}


@GetMapping
("/getDrops")
public List<Drop> getDrops(@RequestParam(name = "bucketId") Long bucketId,@RequestParam(name = "filter") DropFilter filter,@RequestParam(name = "page") int page,@RequestParam(name = "dropCount") int dropCount,@RequestParam(name = "queryingAccount") Account queryingAccount){
  return bucketdao.getDrops(bucketId,filter,page,dropCount,queryingAccount);
}


@PutMapping
("/decreaseDropCount")
public void decreaseDropCount(@RequestParam(name = "bucket") Bucket bucket){
bucketdao.decreaseDropCount(bucket);
}


@GetMapping
("/findBucketDrop")
public BucketDrop findBucketDrop(@RequestParam(name = "bucketId") Long bucketId,@RequestParam(name = "dropId") Long dropId){
  return bucketdao.findBucketDrop(bucketId,dropId);
}


@PutMapping
("/increaseDropCount")
public void increaseDropCount(@RequestParam(name = "bucket") Bucket bucket){
bucketdao.increaseDropCount(bucket);
}


@GetMapping
("/addComment")
public BucketComment addComment(@RequestParam(name = "bucket") Bucket bucket,@RequestParam(name = "commentText") String commentText,@RequestParam(name = "account") Account account){
  return bucketdao.addComment(bucket,commentText,account);
}


@GetMapping
("/findAll")
public List<Bucket> findAll(@RequestParam(name = "searchTerm") String searchTerm,@RequestParam(name = "count") int count,@RequestParam(name = "page") int page){
  return bucketdao.findAll(searchTerm,count,page);
}


}