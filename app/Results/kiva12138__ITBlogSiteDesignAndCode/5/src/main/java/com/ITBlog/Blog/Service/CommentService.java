package com.ITBlog.Blog.Service;
 import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ITBlog.Blog.Entity.Blog;
import com.ITBlog.Blog.Entity.Comment;
import com.ITBlog.Blog.Repository.CommentRepository;
import com.ITBlog.Blog.Service.BlogService;
import com.ITBlog.Interface.BlogService;
@Service
public class CommentService {

 private  CommentRepository commentRepository;

 private  BlogService blogService;

@Autowired
public CommentService(CommentRepository commentRepository) {
    this.setCommentRepository(commentRepository);
}
public void setCommentRepository(CommentRepository commentRepository){
    this.commentRepository = commentRepository;
}


public BlogService getBlogService(){
    return blogService;
}


@Transactional(propagation = Propagation.REQUIRED)
public int saveComment(long blogId,long authorId,Date time,String content){
    // 首先要判断博客id值不为空
    Blog blog = this.blogService.findBlogById(blogId);
    if (blog == null) {
        return 1;
    } else {
        // Type是1表示普通用户
        Comment comment = new Comment(blogId, authorId, time, content);
        Comment commentTemp = this.commentRepository.saveAndFlush(comment);
        if (commentTemp == null) {
            return 1;
        } else if (commentTemp.getBlogId() == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}


public CommentRepository getCommentRepository(){
    return commentRepository;
// this.commentRepository.deleteById(id);
}


public void setBlogService(BlogService blogService){
    this.blogService = blogService;
}


@Transactional(propagation = Propagation.REQUIRED)
public Comment getCommentById(long commentId){
    Comment comment = (Comment) this.commentRepository.findByCommentId(commentId);
    return comment;
}


@Transactional(propagation = Propagation.REQUIRED)
public int deleteComment(long commentId){
    this.commentRepository.deleteById(commentId);
    return 0;
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
public int updateContent(long commentId,String content){
    int effectNum = this.commentRepository.updateContent(content, commentId);
    if (effectNum == 0) {
        return 1;
    } else {
        return 0;
    }
}


}