package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.BucketDrop;
import com.ushahidi.swiftriver.core.Request.BucketDropRequest;
public class BucketDropRequestImpl implements BucketDropRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<BucketDrop> getReadBucketDrops(long id){
 List<BucketDrop> aux = restTemplate.getForObject("http://4/Account/{id}/BucketDrop/getReadBucketDrops",List<BucketDrop>.class,id);
return aux;
}


public void setReadBucketDrops(List<BucketDrop> readBucketDrops,long id){
 restTemplate.put("http://4/Account/{id}/BucketDrop/setReadBucketDrops",readBucketDrops,id);
 return ;
}


}