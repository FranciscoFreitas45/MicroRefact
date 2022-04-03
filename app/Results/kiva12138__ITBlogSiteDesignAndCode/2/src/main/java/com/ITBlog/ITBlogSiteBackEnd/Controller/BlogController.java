package com.ITBlog.ITBlogSiteBackEnd.Controller;
 import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ITBlog.ITBlogSiteBackEnd.Service.BlogService;
import com.ITBlog.ITBlogSiteBackEnd.Entity.Blog;
import com.ITBlog.ITBlogSiteBackEnd.Response.BlogResponse;
import com.ITBlog.ITBlogSiteBackEnd.Response.FindBlogResponse;
import com.ITBlog.ITBlogSiteBackEnd.Response.FindBlogByIdResponse;
@RestController
@RequestMapping("/blog")
public class BlogController {

 private  BlogService blogService;

@Autowired
public BlogController(BlogService blogService) {
    this.setUserService(blogService);
}
@GetMapping("/findlikebytitle")
public FindBlogResponse findLikeByTitle(String title){
    FindBlogResponse blogResponse = new FindBlogResponse();
    List<Blog> blog = this.blogService.findBlogLikeTitle(title);
    blogResponse.setCode(0);
    blogResponse.setFindBlog(blog);
    return blogResponse;
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


public void setUserService(BlogService blogService){
    this.blogService = blogService;
}


@PostMapping("/updatecommentsnum")
public BlogResponse updateCommentsNum(long blogId){
    BlogResponse blogResponse = new BlogResponse();
    // Save To Service
    int code = this.blogService.updateCommentsNum(blogId);
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


@GetMapping("/findbyuserid")
public FindBlogResponse FindBlogByUserId(long userId){
    FindBlogResponse blogResponse = new FindBlogResponse();
    List<Blog> blog = this.blogService.findBlogByUserId(userId);
    blogResponse.setCode(0);
    blogResponse.setFindBlog(blog);
    return blogResponse;
}


public BlogService getUserService(){
    return blogService;
}


@GetMapping("/findbytime")
public FindBlogResponse FindBlogByTime(){
    FindBlogResponse blogResponse = new FindBlogResponse();
    List<Blog> blog = this.blogService.findBlogByTime();
    if (blog.size() == 0) {
        blogResponse.setCode(1);
        blogResponse.setOtherInformation("NULL Value!");
        return blogResponse;
    } else {
        blogResponse.setCode(0);
        blogResponse.setFindBlog(blog);
    }
    return blogResponse;
}


@GetMapping("/find")
public FindBlogByIdResponse find(long blogId){
    FindBlogByIdResponse blogResponse = new FindBlogByIdResponse();
    Blog blog = this.blogService.findBlogById(blogId);
    if (blog == null) {
        blogResponse.setCode(1);
        blogResponse.setOtherInformation("NULL Value!");
        return blogResponse;
    } else {
        blogResponse.setCode(0);
        blogResponse.setFindBlogById(blog);
    }
    return blogResponse;
}


@PostMapping("/updatereadnum")
public BlogResponse updateReadNum(long blogId){
    BlogResponse blogResponse = new BlogResponse();
    // Save To Service
    int code = this.blogService.updateReadNum(blogId);
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


}