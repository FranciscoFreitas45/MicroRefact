package com.ushahidi.swiftriver.core.DTO;
 import org.codehaus.jackson.annotate.JsonProperty;
import com.ushahidi.swiftriver.core.model.Bucket;
public class GetBucketDTO {

 private  long id;

 private  String name;

 private  String description;

 private  String category;

 private  int followerCount;

 private  boolean collaborating;

 private  boolean following;

 private  String dateAdded;

 private  int dropCount;

 private  boolean published;

 private  AccountDTO account;


public String getName(){
    return name;
}


public boolean isCollaborating(){
    return collaborating;
}


public void setFollowing(boolean following){
    this.following = following;
}


public String getCategory(){
    return category;
}


public long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setDateAdded(String dateAdded){
    this.dateAdded = dateAdded;
}


public int getDropCount(){
    return dropCount;
}


public String getDateAdded(){
    return dateAdded;
}


public int getFollowerCount(){
    return followerCount;
}


public AccountDTO getAccount(){
    return account;
}


public void setAccount(AccountDTO account){
    this.account = account;
}


}