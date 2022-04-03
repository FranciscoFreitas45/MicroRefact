package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import com.ushahidi.swiftriver.core.model.Account;
public class FollowerDTO {

 private  long id;

 private  String name;

 private  String email;

 private  String accountPath;

 private  String isPublic;

 private  Date dateAdded;

 private  int followerCount;

 private  int followingCount;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public String getName(){
    return name;
}


public String getAccountPath(){
    return accountPath;
}


public long getId(){
    return id;
}


public String getIsPublic(){
    return isPublic;
}


public Date getDateAdded(){
    return dateAdded;
}


public int getFollowerCount(){
    return followerCount;
}


public String getEmail(){
    return email;
}


public int getFollowingCount(){
    return followingCount;
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEmail(String email){
    this.email = email;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEmail"))

.queryParam("email",email)
;
restTemplate.put(builder.toUriString(),null);
}


}