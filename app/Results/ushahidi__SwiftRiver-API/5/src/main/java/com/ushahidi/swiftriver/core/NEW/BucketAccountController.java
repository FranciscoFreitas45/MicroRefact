package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Bucket;
@RestController
@CrossOrigin
public class BucketAccountController {

@Autowired
 private BucketAccountService bucketaccountservice;


@GetMapping
("/Account/{id}/Bucket/getCollaboratingBuckets")
public List<Bucket> getCollaboratingBuckets(@PathVariable(name="id") long id){
return bucketaccountservice.getCollaboratingBuckets(id);
}


@GetMapping
("/Account/{id}/Bucket/getFollowingBuckets")
public List<Bucket> getFollowingBuckets(@PathVariable(name="id") long id){
return bucketaccountservice.getFollowingBuckets(id);
}


@PutMapping
("/Account/{id}/Bucket/setCollaboratingBuckets")
public void setCollaboratingBuckets(@PathVariable(name="id") long id,@RequestBody List<Bucket> collaboratingBuckets){
bucketaccountservice.setCollaboratingBuckets(id,collaboratingBuckets);
}


@PutMapping
("/Account/{id}/Bucket/setBuckets")
public void setBuckets(@PathVariable(name="id") long id,@RequestBody List<Bucket> buckets){
bucketaccountservice.setBuckets(id,buckets);
}


@GetMapping
("/Account/{id}/Bucket/getBuckets")
public List<Bucket> getBuckets(@PathVariable(name="id") long id){
return bucketaccountservice.getBuckets(id);
}


@PutMapping
("/Account/{id}/Bucket/setFollowingBuckets")
public void setFollowingBuckets(@PathVariable(name="id") long id,@RequestBody List<Bucket> followingBuckets){
bucketaccountservice.setFollowingBuckets(id,followingBuckets);
}


}