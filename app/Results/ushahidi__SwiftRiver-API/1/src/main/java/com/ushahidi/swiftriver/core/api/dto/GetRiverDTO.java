package com.ushahidi.swiftriver.core.api.dto;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class GetRiverDTO {

 private  long id;

@JsonProperty("name")
 private  String riverName;

 private  String description;

 private  String category;

 private  AccountDTO account;

@JsonProperty("follower_count")
 private  int followerCount;

@JsonProperty("public")
 private  boolean riverPublic;

 private  boolean active;

@JsonProperty("drop_count")
 private  int dropCount;

@JsonProperty("drop_quota")
 private  int dropQuota;

 private  boolean full;

@JsonProperty("date_added")
 private  String dateAdded;

@JsonProperty("expiry_date")
 private  String expiryDate;

@JsonProperty("extension_count")
 private  int extensionCount;

@JsonProperty("max_drop_id")
 private  long maxDropId;

 private  List<Channel> channels;

 private  long id;

 private  String channel;

 private  boolean active;

 private  String parameters;

@JsonProperty("drop_count")
 private  int dropCount;


public void setDropCount(int dropCount){
    this.dropCount = dropCount;
}


public long getId(){
    return id;
}


public void setMaxDropId(long maxDropId){
    this.maxDropId = maxDropId;
}


public void setChannel(String channel){
    this.channel = channel;
}


public void setDescription(String description){
    this.description = description;
}


public boolean isActive(){
    return active;
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


public void setRiverName(String riverName){
    this.riverName = riverName;
}


public void setParameters(String parameters){
    this.parameters = parameters;
}


public String getChannel(){
    return channel;
}


public void setCategory(String category){
    this.category = category;
}


public String getDateAdded(){
    return dateAdded;
}


public AccountDTO getAccount(){
    return account;
}


public int getExtensionCount(){
    return extensionCount;
}


public void setId(long id){
    this.id = id;
}


public void setExtensionCount(int extensionCount){
    this.extensionCount = extensionCount;
}


public void setFull(boolean full){
    this.full = full;
}


public int getDropQuota(){
    return dropQuota;
}


public void setChannels(List<Channel> channels){
    this.channels = channels;
}


public String getExpiryDate(){
    return expiryDate;
}


public void setDropQuota(int dropQuota){
    this.dropQuota = dropQuota;
}


public String getRiverName(){
    return riverName;
}


public String getCategory(){
    return category;
}


public long getMaxDropId(){
    return maxDropId;
}


public void setRiverPublic(boolean riverPublic){
    this.riverPublic = riverPublic;
}


public int getDropCount(){
    return dropCount;
}


public void setActive(boolean active){
    this.active = active;
}


public void setExpiryDate(String expiryDate){
    this.expiryDate = expiryDate;
}


public int getFollowerCount(){
    return followerCount;
}


public String getParameters(){
    return parameters;
}


public List<Channel> getChannels(){
    return channels;
}


public void setAccount(AccountDTO account){
    this.account = account;
}


public boolean isFull(){
    return full;
}


public boolean isRiverPublic(){
    return riverPublic;
}


}