package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Bucket;
import com.ushahidi.swiftriver.core.Request.BucketRequest;
public class BucketRequestImpl implements BucketRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Bucket> getCollaboratingBuckets(long id){
 List<Bucket> aux = restTemplate.getForObject("http://5/Account/{id}/Bucket/getCollaboratingBuckets",List<Bucket>.class,id);
return aux;
}


public List<Bucket> getFollowingBuckets(long id){
 List<Bucket> aux = restTemplate.getForObject("http://5/Account/{id}/Bucket/getFollowingBuckets",List<Bucket>.class,id);
return aux;
}


public void setCollaboratingBuckets(List<Bucket> collaboratingBuckets,long id){
 restTemplate.put("http://5/Account/{id}/Bucket/setCollaboratingBuckets",collaboratingBuckets,id);
 return ;
}


public void setBuckets(List<Bucket> buckets,long id){
 restTemplate.put("http://5/Account/{id}/Bucket/setBuckets",buckets,id);
 return ;
}


public List<Bucket> getBuckets(long id){
 List<Bucket> aux = restTemplate.getForObject("http://5/Account/{id}/Bucket/getBuckets",List<Bucket>.class,id);
return aux;
}


public void setFollowingBuckets(List<Bucket> followingBuckets,long id){
 restTemplate.put("http://5/Account/{id}/Bucket/setFollowingBuckets",followingBuckets,id);
 return ;
}


}