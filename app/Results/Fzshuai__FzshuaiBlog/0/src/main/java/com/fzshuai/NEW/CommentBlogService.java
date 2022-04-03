package com.fzshuai.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.dao.CommentRepository;
import com.fzshuai.po.Comment;
@Service
public class CommentBlogService {

@Autowired
 private CommentRepository commentrepository;


public List<Comment> getComments(Long id){
return commentrepository.getComments(id);
}


public void setComments(Long id,List<Comment> comments){
commentrepository.setComments(id,comments);
}


}