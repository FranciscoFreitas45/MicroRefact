package com.ushahidi.swiftriver.core.api.dto.GetFormDTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class Account {

 private  long id;

@JsonProperty("account_path")
 private  String accountPath;


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


}