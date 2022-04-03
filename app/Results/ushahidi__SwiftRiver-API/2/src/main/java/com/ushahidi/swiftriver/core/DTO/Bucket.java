package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
import com.ushahidi.swiftriver.core.Request.Impl.AccountRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Account;
public class Bucket {

 private  long id;

 private  Account account;

 private  String name;

 private  String bucketNameCanonical;

 private  String description;

 private  boolean published;

 private  String defaultLayout;

 private  Date dateAdded;

 private  String publicToken;

 private  int dropCount;

 private  List<BucketCollaborator> collaborators;

 private  List<Account> followers;

 private  List<BucketComment> comments;

 private long id0O8K;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public String getPublicToken(){
    return publicToken;
}


public String getName(){
    return name;
}


public long getId(){
    return id;
}


public List<Account> getFollowers(){
    return followers;
}


public String getDescription(){
    return description;
}


public Date getDateAdded(){
    return dateAdded;
}


public Account getAccount(){
    return account;
}


public List<BucketComment> getComments(){
    return comments;
}


public String getBucketNameCanonical(){
    return bucketNameCanonical;
}


public List<BucketCollaborator> getCollaborators(){
    return collaborators;
}


public String getDefaultLayout(){
    return defaultLayout;
}


public int getDropCount(){
    return dropCount;
}


public void setId(long id){
    this.id = id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setId"))

.queryParam("id",id)
;
restTemplate.put(builder.toUriString(),null);
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


}