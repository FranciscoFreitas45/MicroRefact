package com.ushahidi.swiftriver.core.api.dto;
 import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import com.ushahidi.swiftriver.core.model.Account;
public class FollowerDTO {

 private  long id;

 private  String name;

 private  String email;

@JsonProperty("account_path")
 private  String accountPath;

@JsonProperty("public")
 private  String isPublic;

@JsonProperty("date_added")
 private  Date dateAdded;

@JsonProperty("follower_count")
 private  int followerCount;

@JsonProperty("following_count")
 private  int followingCount;


public void setName(String name){
    this.name = name;
}


public void setFollowingCount(int followingCount){
    this.followingCount = followingCount;
}


public String getName(){
    return name;
}


public String getAccountPath(){
    return accountPath;
}


public void setIsPublic(String isPublic){
    this.isPublic = isPublic;
}


public long getId(){
    return id;
}


public void setAccountPath(String accountPath){
    this.accountPath = accountPath;
}


public String getIsPublic(){
    return isPublic;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


public void setFollowerCount(int followerCount){
    this.followerCount = followerCount;
}


public void setEmail(String email){
    this.email = email;
}


public Date getDateAdded(){
    return dateAdded;
}


public int getFollowerCount(){
    return followerCount;
}


public void setId(long id){
    this.id = id;
}


public String getEmail(){
    return email;
}


public int getFollowingCount(){
    return followingCount;
}


}