package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class CreateCommentDTO {

@JsonProperty("comment_text")
 private  String commentText;


public String getCommentText(){
    return commentText;
}


public void setCommentText(String commentText){
    this.commentText = commentText;
}


}