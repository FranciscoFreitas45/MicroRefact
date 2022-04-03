package com.ITBlog.DTO;
 import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ITBlog.ITBlogSiteBackEnd.Entity.Comment;
import com.ITBlog.ITBlogSiteBackEnd.Repository.CommentRepository;
import com.ITBlog.ITBlogSiteBackEnd.Service.BlogService;
import com.ITBlog.Interface.BlogService;
public class CommentService {

 private  CommentRepository commentRepository;

 private  BlogService blogService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

@Autowired
public CommentService(CommentRepository commentRepository, BlogService blogService) {
    this.setCommentRepository(commentRepository);
    this.setBlogService(blogService);
}
public BlogService getBlogService(){
    return blogService;
}


public CommentRepository getCommentRepository(){
    return commentRepository;
// this.commentRepository.deleteById(id);
}


@Transactional(propagation = Propagation.REQUIRED)
public Comment getCommentById(long commentId){
    Comment comment = (Comment) this.commentRepository.findByCommentId(commentId);
    return comment;
}


@Transactional(propagation = Propagation.REQUIRED)
public List<Comment> getComByBlogId(long blogId){
    List<Comment> comment = this.commentRepository.getComByBlogId(blogId);
    return comment;
}


@Transactional(propagation = Propagation.REQUIRED)
public List<Comment> getComByAuthorId(long authorId){
    List<Comment> comment = this.commentRepository.getComByAuthorId(authorId);
    return comment;
}


@Transactional(propagation = Propagation.REQUIRED)
public int deleteComment(long commentId){
    this.commentRepository.deleteById(commentId);
    return 0;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteComment"))

.queryParam("commentId",commentId)
;
int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux;
}


}