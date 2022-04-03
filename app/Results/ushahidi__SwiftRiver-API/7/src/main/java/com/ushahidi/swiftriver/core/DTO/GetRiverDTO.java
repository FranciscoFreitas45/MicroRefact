package com.ushahidi.swiftriver.core.DTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class GetRiverDTO {

 private  long id;

 private  String riverName;

 private  String description;

 private  String category;

 private  AccountDTO account;

 private  int followerCount;

 private  boolean riverPublic;

 private  boolean active;

 private  int dropCount;

 private  int dropQuota;

 private  boolean full;

 private  String dateAdded;

 private  String expiryDate;

 private  int extensionCount;

 private  long maxDropId;

 private  List<Channel> channels;

 private  long id;

 private  String channel;

 private  boolean active;

 private  String parameters;

 private  int dropCount;


public long getId(){
    return id;
}


public void setChannel(String channel){
    this.channel = channel;
}


public boolean isActive(){
    return active;
}


public String getDescription(){
    return description;
}


public void setDateAdded(String dateAdded){
    this.dateAdded = dateAdded;
}


public void setParameters(String parameters){
    this.parameters = parameters;
}


public String getChannel(){
    return channel;
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


public void setExtensionCount(int extensionCount){
    this.extensionCount = extensionCount;
}


public int getDropQuota(){
    return dropQuota;
}


public String getExpiryDate(){
    return expiryDate;
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


public int getDropCount(){
    return dropCount;
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


public boolean isFull(){
    return full;
}


}