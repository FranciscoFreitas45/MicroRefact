package com.metservice.kanban.model;
 import java.io.Serializable;
import org.joda.time.LocalDateTime;
import org.springframework.util.Assert;
public class WorkItemComment implements Serializable{

 private  long serialVersionUID;

 private  int parentId;

 private  LocalDateTime whenAdded;

 private  String addedBy;

 private  String commentText;

/**
 * Creates a new Comment object for the specified Work Item.
 *
 * @param addedBy
 *          The person who added the comment; mandatory.
 * @param commentText
 *          The contents of the comment mandatory.
 *
 * @throws IllegalArgumentException
 *          If any of the mandatory parameters are {@code null}.
 */
public WorkItemComment(String addedBy, String commentText) {
    Assert.notNull(addedBy);
    Assert.notNull(commentText);
    this.whenAdded = new LocalDateTime();
    this.addedBy = addedBy;
    this.commentText = commentText;
}
public String getCommentText(){
    return commentText;
}


public LocalDateTime getWhenAdded(){
    return whenAdded;
}


public void setParentId(int parentId){
    this.parentId = parentId;
}


@Override
public String toString(){
    return String.format("WorkItemComment [parentId=%s, whenAdded=%s, addedBy=%s, commentText=%s]", parentId, whenAdded, addedBy, commentText);
}


public String getAddedBy(){
    return addedBy;
}


}