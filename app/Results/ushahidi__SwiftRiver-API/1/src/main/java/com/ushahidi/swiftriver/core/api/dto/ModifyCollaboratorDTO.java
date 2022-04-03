package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class ModifyCollaboratorDTO {

 private  Long id;

@JsonProperty("read_only")
 private  Boolean readOnly;

 private  Boolean active;

 private  Account account;

 private  long id;


public void setActive(Boolean active){
    this.active = active;
}


public Account getAccount(){
    return account;
}


public void setId(long id){
    this.id = id;
}


public void setAccount(Account account){
    this.account = account;
}


public long getId(){
    return id;
}


public Boolean getReadOnly(){
    return readOnly;
}


public Boolean getActive(){
    return active;
}


public void setReadOnly(Boolean readOnly){
    this.readOnly = readOnly;
}


}