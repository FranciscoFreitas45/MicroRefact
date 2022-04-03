package com.fzshuai.Request;
import com.fzshuai.DTO.Comment;
public interface CommentRequest {

   public List<Comment> getComments(Long id);
   public void setComments(List<Comment> comments,Long id);
}