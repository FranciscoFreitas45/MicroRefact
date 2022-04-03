package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.BucketDao;
public class BucketDaoImpl implements BucketDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Bucket findBucketByName(Account account,String bucketName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBucketByName"))
    .queryParam("account",account)
    .queryParam("bucketName",bucketName)
;  Bucket aux = restTemplate.getForObject(builder.toUriString(), Bucket.class);

 return aux;
}


public Object create(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object update(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public BucketCollaborator findCollaborator(Long bucketId,Long accountId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCollaborator"))
    .queryParam("bucketId",bucketId)
    .queryParam("accountId",accountId)
;  BucketCollaborator aux = restTemplate.getForObject(builder.toUriString(), BucketCollaborator.class);

 return aux;
}


public BucketCollaborator addCollaborator(Bucket bucket,Account account,boolean readOnly){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addCollaborator"))
    .queryParam("bucket",bucket)
    .queryParam("account",account)
    .queryParam("readOnly",readOnly)
;  BucketCollaborator aux = restTemplate.getForObject(builder.toUriString(), BucketCollaborator.class);

 return aux;
}


public void updateCollaborator(BucketCollaborator collaborator){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateCollaborator"))
    .queryParam("collaborator",collaborator)
;
  restTemplate.put(builder.toUriString(), null);
}


public List<Drop> getDrops(Long bucketId,DropFilter filter,int page,int dropCount,Account queryingAccount){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDrops"))
    .queryParam("bucketId",bucketId)
    .queryParam("filter",filter)
    .queryParam("page",page)
    .queryParam("dropCount",dropCount)
    .queryParam("queryingAccount",queryingAccount)
;  List<Drop> aux = restTemplate.getForObject(builder.toUriString(), List<Drop>.class);

 return aux;
}


public void decreaseDropCount(Bucket bucket){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/decreaseDropCount"))
    .queryParam("bucket",bucket)
;
  restTemplate.put(builder.toUriString(), null);
}


public BucketDrop findBucketDrop(Long bucketId,Long dropId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBucketDrop"))
    .queryParam("bucketId",bucketId)
    .queryParam("dropId",dropId)
;  BucketDrop aux = restTemplate.getForObject(builder.toUriString(), BucketDrop.class);

 return aux;
}


public void increaseDropCount(Bucket bucket){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/increaseDropCount"))
    .queryParam("bucket",bucket)
;
  restTemplate.put(builder.toUriString(), null);
}


public BucketComment addComment(Bucket bucket,String commentText,Account account){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addComment"))
    .queryParam("bucket",bucket)
    .queryParam("commentText",commentText)
    .queryParam("account",account)
;  BucketComment aux = restTemplate.getForObject(builder.toUriString(), BucketComment.class);

 return aux;
}


public List<Bucket> findAll(String searchTerm,int count,int page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("searchTerm",searchTerm)
    .queryParam("count",count)
    .queryParam("page",page)
;  List<Bucket> aux = restTemplate.getForObject(builder.toUriString(), List<Bucket>.class);

 return aux;
}


}