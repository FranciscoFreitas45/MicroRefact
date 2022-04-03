package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class GetCommentDTO {

 private  long id;

@JsonProperty("comment_text")
 private  String commentText;

@JsonProperty("date_added")
 private  String dateAdded;

 private  AccountDTO account;


public String getCommentText(){
    return commentText;
}


public String getDateAdded(){
    return dateAdded;
}


public void setCommentText(String commentText){
    this.commentText = commentText;
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


public long getId(){
    return id;
}


public void setDateAdded(String dateAdded){
    this.dateAdded = dateAdded;
}


}