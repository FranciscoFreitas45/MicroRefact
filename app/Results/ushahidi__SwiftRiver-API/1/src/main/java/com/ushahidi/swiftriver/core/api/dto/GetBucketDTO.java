package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
import com.ushahidi.swiftriver.core.model.Bucket;
public class GetBucketDTO {

 private  long id;

 private  String name;

 private  String description;

 private  String category;

@JsonProperty("follower_count")
 private  int followerCount;

 private  boolean collaborating;

 private  boolean following;

@JsonProperty("date_added")
 private  String dateAdded;

@JsonProperty("drop_count")
 private  int dropCount;

@JsonProperty("public")
 private  boolean published;

 private  AccountDTO account;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public boolean isFollowing(){
    return following;
}


public boolean isCollaborating(){
    return collaborating;
}


public boolean isPublished(){
    return published;
}


public void setFollowing(boolean following){
    this.following = following;
}


public void setDropCount(int dropCount){
    this.dropCount = dropCount;
}


public String getCategory(){
    return category;
}


public long getId(){
    return id;
}


public void setPublished(boolean published){
    this.published = published;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setFollowerCount(int followerCount){
    this.followerCount = followerCount;
}


public void setDateAdded(String dateAdded){
    this.dateAdded = dateAdded;
}


public int getDropCount(){
    return dropCount;
}


public void setCategory(String category){
    this.category = category;
}


public String getDateAdded(){
    return dateAdded;
}


public int getFollowerCount(){
    return followerCount;
}


public void setCollaborating(boolean collaborating){
    this.collaborating = collaborating;
}


public AccountDTO getAccount(){
    return account;
}


public void setId(long id){
    this.id = id;
}


public void setAccount(AccountDTO account){
    this.account = account;
}


}