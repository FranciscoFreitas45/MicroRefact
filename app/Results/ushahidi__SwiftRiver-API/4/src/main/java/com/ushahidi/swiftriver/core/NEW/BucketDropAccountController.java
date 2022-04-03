package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.BucketDrop;
@RestController
@CrossOrigin
public class BucketDropAccountController {

@Autowired
 private BucketDropAccountService bucketdropaccountservice;


@GetMapping
("/Account/{id}/BucketDrop/getReadBucketDrops")
public List<BucketDrop> getReadBucketDrops(@PathVariable(name="id") long id){
return bucketdropaccountservice.getReadBucketDrops(id);
}


@PutMapping
("/Account/{id}/BucketDrop/setReadBucketDrops")
public void setReadBucketDrops(@PathVariable(name="id") long id,@RequestBody List<BucketDrop> readBucketDrops){
bucketdropaccountservice.setReadBucketDrops(id,readBucketDrops);
}


}