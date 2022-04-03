package com.ushahidi.swiftriver.core.api.dto;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class GetAccountDTO {

 private  long id;

@JsonProperty("account_path")
 private  String accountPath;

@JsonProperty("date_added")
 private  String dateAdded;

 private  boolean active;

@JsonProperty("private")
 private  boolean accountPrivate;

@JsonProperty("river_quota_remaining")
 private  int riverQuotaRemaining;

@JsonProperty("follower_count")
 private  int followerCount;

@JsonProperty("following_count")
 private  int followingCount;

 private  Owner owner;

 private  List<GetRiverDTO> rivers;

@JsonProperty("collaborating_rivers")
 private  List<GetRiverDTO> collaboratingRivers;

@JsonProperty("following_rivers")
 private  List<GetRiverDTO> followingRivers;

 private  List<GetBucketDTO> buckets;

@JsonProperty("collaborating_buckets")
 private  List<GetBucketDTO> collaboratingBuckets;

@JsonProperty("following_buckets")
 private  List<GetBucketDTO> followingBuckets;

 private  List<GetFormDTO> forms;

 private  String name;

 private  String email;

 private  String username;

 private  String avatar;

@JsonProperty("date_added")
 private  String dateCreated;

 private  Boolean active;


public void setName(String name){
    this.name = name;
}


public void setFollowingCount(int followingCount){
    this.followingCount = followingCount;
}


public void setAccountPrivate(boolean accountPrivate){
    this.accountPrivate = accountPrivate;
}


public String getName(){
    return name;
}


public List<GetRiverDTO> getRivers(){
    return rivers;
}


public String getDateCreated(){
    return dateCreated;
}


public void setRiverQuotaRemaining(int riverQuotaRemaining){
    this.riverQuotaRemaining = riverQuotaRemaining;
}


public String getAvatar(){
    return avatar;
}


public String getAccountPath(){
    return accountPath;
}


public long getId(){
    return id;
}


public boolean isActive(){
    return active;
}


public Owner getOwner(){
    return owner;
}


public void setDateAdded(String dateAdded){
    this.dateAdded = dateAdded;
}


public void setFollowerCount(int followerCount){
    this.followerCount = followerCount;
}


public void setOwner(Owner owner){
    this.owner = owner;
}


public String getUsername(){
    return username;
}


public void setForms(List<GetFormDTO> forms){
    this.forms = forms;
}


public String getDateAdded(){
    return dateAdded;
}


public List<GetBucketDTO> getCollaboratingBuckets(){
    return collaboratingBuckets;
}


public List<GetFormDTO> getForms(){
    return forms;
}


public boolean isAccountPrivate(){
    return accountPrivate;
}


public void setId(long id){
    this.id = id;
}


public int getRiverQuotaRemaining(){
    return riverQuotaRemaining;
}


public void setDateCreated(String dateCreated){
    this.dateCreated = dateCreated;
}


public List<GetBucketDTO> getFollowingBuckets(){
    return followingBuckets;
}


public void setFollowingRivers(List<GetRiverDTO> followingRivers){
    this.followingRivers = followingRivers;
}


public void setUsername(String username){
    this.username = username;
}


public void setAvatar(String avatar){
    this.avatar = avatar;
}


public void setCollaboratingBuckets(List<GetBucketDTO> collaboratingBuckets){
    this.collaboratingBuckets = collaboratingBuckets;
}


public void setAccountPath(String accountPath){
    this.accountPath = accountPath;
}


public void setBuckets(List<GetBucketDTO> buckets){
    this.buckets = buckets;
}


public List<GetBucketDTO> getBuckets(){
    return buckets;
}


public void setFollowingBuckets(List<GetBucketDTO> followingBuckets){
    this.followingBuckets = followingBuckets;
}


public void setActive(boolean active){
    this.active = active;
}


public void setEmail(String email){
    this.email = email;
}


public void setCollaboratingRivers(List<GetRiverDTO> collaboratingRivers){
    this.collaboratingRivers = collaboratingRivers;
}


public int getFollowerCount(){
    return followerCount;
}


public String getEmail(){
    return email;
}


public Boolean getActive(){
    return active;
}


public void setRivers(List<GetRiverDTO> rivers){
    this.rivers = rivers;
}


public List<GetRiverDTO> getCollaboratingRivers(){
    return collaboratingRivers;
}


public List<GetRiverDTO> getFollowingRivers(){
    return followingRivers;
}


public int getFollowingCount(){
    return followingCount;
}


}