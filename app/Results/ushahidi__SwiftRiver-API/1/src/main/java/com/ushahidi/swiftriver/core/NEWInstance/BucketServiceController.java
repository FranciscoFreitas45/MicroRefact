package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BucketServiceController {

 private BucketService bucketservice;


@GetMapping
("/filterVisible")
public List<Bucket> filterVisible(@RequestParam(name = "buckets") List<Bucket> buckets,@RequestParam(name = "queryingAccount") Account queryingAccount){
  return bucketservice.filterVisible(buckets,queryingAccount);
}


@GetMapping
("/isOwner")
public boolean isOwner(@RequestParam(name = "bucket") Bucket bucket,@RequestParam(name = "account") Account account){
  return bucketservice.isOwner(bucket,account);
}


@GetMapping
("/findBuckets")
public List<GetBucketDTO> findBuckets(@RequestParam(name = "searchTerm") String searchTerm,@RequestParam(name = "count") int count,@RequestParam(name = "page") int page){
  return bucketservice.findBuckets(searchTerm,count,page);
}


}