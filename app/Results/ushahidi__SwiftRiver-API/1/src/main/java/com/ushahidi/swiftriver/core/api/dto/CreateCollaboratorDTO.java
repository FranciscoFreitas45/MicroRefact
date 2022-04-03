package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class CreateCollaboratorDTO {

@JsonProperty("read_only")
 private  boolean readOnly;

 private  Account account;

 private  long id;


public boolean isReadOnly(){
    return readOnly;
}


public Account getAccount(){
    return account;
}


public void setAccount(Account account){
    this.account = account;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setReadOnly(boolean readOnly){
    this.readOnly = readOnly;
}


}