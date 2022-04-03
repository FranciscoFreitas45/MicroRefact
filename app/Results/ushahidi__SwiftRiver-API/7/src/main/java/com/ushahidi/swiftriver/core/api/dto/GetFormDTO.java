package com.ushahidi.swiftriver.core.api.dto;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class GetFormDTO {

 private  String id;

 private  String name;

 private  Account account;

 private  List<GetFormFieldDTO> fields;

 private  long id;

@JsonProperty("account_path")
 private  String accountPath;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public List<GetFormFieldDTO> getFields(){
    return fields;
}


public Account getAccount(){
    return account;
}


public String getAccountPath(){
    return accountPath;
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


public void setFields(List<GetFormFieldDTO> fields){
    this.fields = fields;
}


public void setAccountPath(String accountPath){
    this.accountPath = accountPath;
}


}