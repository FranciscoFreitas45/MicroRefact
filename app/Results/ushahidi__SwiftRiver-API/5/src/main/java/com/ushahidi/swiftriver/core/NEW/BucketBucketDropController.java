package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Bucket;
@RestController
@CrossOrigin
public class BucketBucketDropController {

@Autowired
 private BucketBucketDropService bucketbucketdropservice;


@PutMapping
("/BucketDrop/{id}/Bucket/setBucket")
public void setBucket(@PathVariable(name="id") long idFGI4,@RequestBody Bucket bucket){
bucketbucketdropservice.setBucket(idFGI4,bucket);
}


@GetMapping
("/BucketDrop/{id}/Bucket/getBucket")
public Bucket getBucket(@PathVariable(name="id") long idFGI4){
return bucketbucketdropservice.getBucket(idFGI4);
}


}