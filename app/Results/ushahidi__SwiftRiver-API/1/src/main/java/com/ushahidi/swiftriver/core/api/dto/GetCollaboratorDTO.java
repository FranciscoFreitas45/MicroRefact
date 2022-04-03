package com.ushahidi.swiftriver.core.api.dto;
 import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import com.ushahidi.swiftriver.core.api.dto.AccountDTO.Owner;
public class GetCollaboratorDTO {

 private  long id;

@JsonProperty("account_path")
 private  String accountPath;

 private  Owner owner;

 private  boolean active;

@JsonProperty("read_only")
 private  boolean readOnly;

@JsonProperty("date_added")
 private  Date dateAdded;


public void setActive(boolean active){
    this.active = active;
}


public boolean isReadOnly(){
    return readOnly;
}


public Date getDateAdded(){
    return dateAdded;
}


public String getAccountPath(){
    return accountPath;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setAccountPath(String accountPath){
    this.accountPath = accountPath;
}


public Owner getOwner(){
    return owner;
}


public boolean isActive(){
    return active;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


public void setOwner(Owner owner){
    this.owner = owner;
}


public void setReadOnly(boolean readOnly){
    this.readOnly = readOnly;
}


}