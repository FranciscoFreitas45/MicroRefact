package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Bucket;
import com.ushahidi.swiftriver.core.Request.BucketRequest;
public class BucketRequestImpl implements BucketRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setBucket(Bucket bucket,long idFGI4){
 restTemplate.put("http://5/BucketDrop/{id}/Bucket/setBucket",bucket,idFGI4);
 return ;
}


public Bucket getBucket(long idFGI4){
 Bucket aux = restTemplate.getForObject("http://5/BucketDrop/{id}/Bucket/getBucket",Bucket.class,idFGI4);
return aux;
}


}