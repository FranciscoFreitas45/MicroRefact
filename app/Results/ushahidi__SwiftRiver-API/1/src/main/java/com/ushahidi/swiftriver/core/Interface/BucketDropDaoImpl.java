package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.BucketDropDao;
public class BucketDropDaoImpl implements BucketDropDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void increaseVeracity(BucketDrop bucketDrop){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/increaseVeracity"))
    .queryParam("bucketDrop",bucketDrop)
;
  restTemplate.put(builder.toUriString(), null);
}


public Object create(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public BucketDropTag findTag(BucketDrop bucketDrop,Tag tag){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTag"))
    .queryParam("bucketDrop",bucketDrop)
    .queryParam("tag",tag)
;  BucketDropTag aux = restTemplate.getForObject(builder.toUriString(), BucketDropTag.class);

 return aux;
}


public void addTag(BucketDrop bucketDrop,Tag tag){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addTag"))
    .queryParam("bucketDrop",bucketDrop)
    .queryParam("tag",tag)
;
  restTemplate.put(builder.toUriString(), null);
}


public boolean deleteTag(BucketDrop bucketDrop,Tag tag){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteTag"))
    .queryParam("bucketDrop",bucketDrop)
    .queryParam("tag",tag)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public BucketDropLink findLink(BucketDrop bucketDrop,Link link){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findLink"))
    .queryParam("bucketDrop",bucketDrop)
    .queryParam("link",link)
;  BucketDropLink aux = restTemplate.getForObject(builder.toUriString(), BucketDropLink.class);

 return aux;
}


public void addLink(BucketDrop bucketDrop,Link link){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addLink"))
    .queryParam("bucketDrop",bucketDrop)
    .queryParam("link",link)
;
  restTemplate.put(builder.toUriString(), null);
}


public boolean deleteLink(BucketDrop bucketDrop,Link link){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteLink"))
    .queryParam("bucketDrop",bucketDrop)
    .queryParam("link",link)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public BucketDropPlace findPlace(BucketDrop bucketDrop,Place place){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPlace"))
    .queryParam("bucketDrop",bucketDrop)
    .queryParam("place",place)
;  BucketDropPlace aux = restTemplate.getForObject(builder.toUriString(), BucketDropPlace.class);

 return aux;
}


public void addPlace(BucketDrop bucketDrop,Place place){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addPlace"))
    .queryParam("bucketDrop",bucketDrop)
    .queryParam("place",place)
;
  restTemplate.put(builder.toUriString(), null);
}


public boolean deletePlace(BucketDrop bucketDrop,Place place){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deletePlace"))
    .queryParam("bucketDrop",bucketDrop)
    .queryParam("place",place)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public BucketDropComment addComment(BucketDrop bucketDrop,Account account,String commentText){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addComment"))
    .queryParam("bucketDrop",bucketDrop)
    .queryParam("account",account)
    .queryParam("commentText",commentText)
;  BucketDropComment aux = restTemplate.getForObject(builder.toUriString(), BucketDropComment.class);

 return aux;
}


public boolean deleteComment(Long commentId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteComment"))
    .queryParam("commentId",commentId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public BucketDropForm findForm(Long dropId,Long formId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findForm"))
    .queryParam("dropId",dropId)
    .queryParam("formId",formId)
;  BucketDropForm aux = restTemplate.getForObject(builder.toUriString(), BucketDropForm.class);

 return aux;
}


public boolean isRead(BucketDrop bucketDrop,Account account){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isRead"))
    .queryParam("bucketDrop",bucketDrop)
    .queryParam("account",account)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}