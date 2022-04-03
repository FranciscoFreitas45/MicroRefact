package com.ITBlog.ITBlogSiteBackEnd.Service;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ITBlog.Interface.UserService;
import com.ITBlog.Interface.BlogService;
import com.ITBlog.Interface.CommentService;
import com.ITBlog.DTO.UserService;
import com.ITBlog.DTO.CommentService;
import com.ITBlog.DTO.BlogService;
@Service
public class AdminService {

 private  UserService userService;

 private  BlogService blogService;

 private  CommentService commentService;

@Autowired
public AdminService(UserService userService, BlogService blogService, CommentService commentService) {
    this.setUserService(userService);
    this.setBlogService(blogService);
    this.setCommentService(commentService);
}
public BlogService getBlogService(){
    return blogService;
}


@Transactional(propagation = Propagation.REQUIRED)
public int updatePassword(long adminId,long userId,String password){
    int result = 0;
    int adminType = this.userService.getTypeByUserId(adminId);
    if (adminType == 0) {
        result = this.userService.updatePassword(userId, password);
    } else {
        result = 2;
    }
    return result;
}


public void setBlogService(BlogService blogService){
    this.blogService = blogService;
}


public void setCommentService(CommentService commentService){
    this.commentService = commentService;
}


@Transactional(propagation = Propagation.REQUIRED)
public int deleteComment(long adminId,long commentId){
    int result = 0;
    int adminType = this.userService.getTypeByUserId(adminId);
    if (adminType == 0) {
        this.commentService.deleteComment(commentId);
    } else {
        result = 2;
    }
    return result;
}


@Transactional(propagation = Propagation.REQUIRED)
public int deleteUser(long adminId,long userId){
    int result = 0;
    int adminType = this.userService.getTypeByUserId(adminId);
    if (adminType == 0) {
        result = this.userService.deleteUser(userId);
    } else {
        result = 2;
    }
    return result;
}


@Transactional(propagation = Propagation.REQUIRED)
public int deleteBlog(long adminId,long blogId){
    int result = 0;
    int adminType = this.userService.getTypeByUserId(adminId);
    if (adminType == 0) {
        this.blogService.deleteBlog(blogId);
    } else {
        result = 2;
    }
    return result;
}


public void setUserService(UserService userService){
    this.userService = userService;
}


public CommentService getCommentService(){
    return commentService;
}


public UserService getUserService(){
    return userService;
}


public int saveUser(long adminId,String name,int gender,int age,String password,long phone){
    int result = 0;
    int adminType = this.userService.getTypeByUserId(adminId);
    if (adminType == 0) {
        result = this.userService.saveUser(name, gender, age, password, phone);
    } else {
        result = 2;
    }
    return result;
}


@Transactional(propagation = Propagation.REQUIRED)
public int closeUserAccount(long adminId,long userId){
    int result = 0;
    int adminType = this.userService.getTypeByUserId(adminId);
    if (adminType == 0) {
        result = this.userService.closeUserAccount(userId);
    } else {
        result = 2;
    }
    return result;
}


}