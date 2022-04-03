package com.ITBlog.Blog.Controller;
 import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ITBlog.Blog.Service.BlogService;
import com.ITBlog.Blog.Entity.Blog;
import com.ITBlog.Blog.Response.BlogResponse;
import com.ITBlog.Blog.Repository.BlogRepository;
@RestController
@RequestMapping("/blog")
public class BlogController {

 private  BlogService blogService;

@Autowired
public BlogController(BlogService blogService) {
    this.setUserService(blogService);
}
@PostMapping("/addblog")
public BlogResponse addBlog(String title,String context,long authorId,Date time){
    BlogResponse blogResponse = new BlogResponse();
    if (title == null || context == null) {
        blogResponse.setCode(1);
        blogResponse.setOtherInformation("Empty Value!");
        return blogResponse;
    }
    int code = this.blogService.addBlog(title, context, authorId, time);
    if (code == 0) {
        blogResponse.setCode(0);
    } else {
        blogResponse.setCode(2);
        blogResponse.setOtherInformation("Service Error!");
    }
    return blogResponse;
}


@GetMapping("/findlikebytitle")
public List<Blog> FindLikeByTitle(String title){
    List<Blog> blog = this.blogService.findBlogLikeTitle(title);
    return blog;
}


@GetMapping("/deleteblog")
public BlogResponse deleteBlog(long blogId){
    BlogResponse blogResponse = new BlogResponse();
    // Judge Empty Values
    if (blogId == 0) {
        blogResponse.setCode(1);
        blogResponse.setOtherInformation("Empty Value!");
        return blogResponse;
    }
    int code = this.blogService.deleteBlog(blogId);
    if (code == 0) {
        blogResponse.setCode(0);
    } else {
        blogResponse.setCode(2);
        blogResponse.setOtherInformation("Service Error!");
    }
    return blogResponse;
}


@GetMapping("/find")
public Blog Find(long blogId){
    Blog blog = this.blogService.findBlogById(blogId);
    return blog;
}


public void setUserService(BlogService blogService){
    this.blogService = blogService;
}


@PostMapping("/updatecommentsnum")
public BlogResponse updateCommentsNum(long blogId,int commentNum){
    BlogResponse blogResponse = new BlogResponse();
    // Judge Empty Values
    if (commentNum < 0) {
        blogResponse.setCode(1);
        blogResponse.setOtherInformation("Error Value!");
        return blogResponse;
    }
    // Save To Service
    int code = this.blogService.updateCommentsNum(blogId, commentNum);
    if (code == 0) {
        blogResponse.setCode(0);
    } else {
        blogResponse.setCode(2);
        blogResponse.setOtherInformation("Service Error!");
    }
    return blogResponse;
}


@PostMapping("/updatecontext")
public BlogResponse updateContext(long blogId,String context){
    BlogResponse blogResponse = new BlogResponse();
    // Judge Empty Values
    if (context == null) {
        blogResponse.setCode(1);
        blogResponse.setOtherInformation("Empty Value!");
        return blogResponse;
    }
    // Save To Service
    int code = this.blogService.updateContext(blogId, context);
    if (code == 0) {
        blogResponse.setCode(0);
    } else {
        blogResponse.setCode(2);
        blogResponse.setOtherInformation("Service Error!");
    }
    return blogResponse;
}


@PostMapping("/updatetime")
public BlogResponse updateTime(long blogId,Date time){
    BlogResponse blogResponse = new BlogResponse();
    // Judge Empty Values
    if (time == null) {
        blogResponse.setCode(1);
        blogResponse.setOtherInformation("Empty Value!");
        return blogResponse;
    }
    // Save To Service
    int code = this.blogService.updateTime(blogId, time);
    if (code == 0) {
        blogResponse.setCode(0);
    } else {
        blogResponse.setCode(2);
        blogResponse.setOtherInformation("Service Error!");
    }
    return blogResponse;
}


@PostMapping("/updatereadnum")
public BlogResponse updateReadNum(long blogId,int readNum){
    BlogResponse blogResponse = new BlogResponse();
    // Judge Empty Values
    if (readNum < 0) {
        blogResponse.setCode(1);
        blogResponse.setOtherInformation("Error Value!");
        return blogResponse;
    }
    // Save To Service
    int code = this.blogService.updateReadNum(blogId, readNum);
    if (code == 0) {
        blogResponse.setCode(0);
    } else {
        blogResponse.setCode(2);
        blogResponse.setOtherInformation("Service Error!");
    }
    return blogResponse;
}


@PostMapping("/updatetitle")
public BlogResponse updateTitle(long blogId,String title){
    BlogResponse blogResponse = new BlogResponse();
    // Judge Empty Values
    if (title == null) {
        blogResponse.setCode(1);
        blogResponse.setOtherInformation("Empty Value!");
        return blogResponse;
    }
    // Save To Service
    int code = this.blogService.updateTitle(blogId, title);
    if (code == 0) {
        blogResponse.setCode(0);
    } else {
        blogResponse.setCode(2);
        blogResponse.setOtherInformation("Service Error!");
    }
    return blogResponse;
}


public BlogService getUserService(){
    return blogService;
}


}