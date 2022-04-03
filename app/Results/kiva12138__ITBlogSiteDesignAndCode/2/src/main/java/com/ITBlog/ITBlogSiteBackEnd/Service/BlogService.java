package com.ITBlog.ITBlogSiteBackEnd.Service;
 import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ITBlog.ITBlogSiteBackEnd.Repository.BlogRepository;
import com.ITBlog.ITBlogSiteBackEnd.Entity;
@Service
public class BlogService {

 private  BlogRepository blogRepository;

@Autowired
public BlogService(BlogRepository blogRepository) {
    this.setBlogRepository(blogRepository);
}
@Transactional(propagation = Propagation.REQUIRED)
public int addBlog(String title,String context,long authorId,Date time){
    Blog blog = new Blog(title, context, authorId, time);
    Blog blogTemp = this.blogRepository.saveAndFlush(blog);
    if (blogTemp == null) {
        return 1;
    } else {
        return 0;
    }
}


@Transactional(propagation = Propagation.REQUIRED)
public List<Blog> findBlogLikeTitle(String title){
    List<Blog> blog = this.blogRepository.findBlogLikeTitle(title);
    return blog;
}


@Transactional(propagation = Propagation.REQUIRED)
public int deleteBlog(long blogId){
    this.blogRepository.deleteById(blogId);
    return 0;
}


@Transactional(propagation = Propagation.REQUIRED)
public List<Blog> findBlogByUserId(long authorId){
    List<Blog> blog = this.blogRepository.findBlogByUserId(authorId);
    return blog;
}


@Transactional(propagation = Propagation.REQUIRED)
public int updateCommentsNum(long blogId){
    int effectedNum = this.blogRepository.updateCommentsNum(blogId);
    if (effectedNum == 0) {
        return 1;
    } else {
        return 0;
    }
}


@Transactional(propagation = Propagation.REQUIRED)
public int updateContext(long blogId,String context){
    int effectedNum = this.blogRepository.updateContext(context, blogId);
    if (effectedNum == 0) {
        return 1;
    } else {
        return 0;
    }
}


@Transactional(propagation = Propagation.REQUIRED)
public int updateTime(long blogId,Date time){
    int effectedNum = this.blogRepository.updateTime(time, blogId);
    if (effectedNum == 0) {
        return 1;
    } else {
        return 0;
    }
}


public BlogRepository getBlogRepository(){
    return blogRepository;
}


@Transactional(propagation = Propagation.REQUIRED)
public Blog findBlogById(long blogId){
    Blog blog = this.blogRepository.findBlogById(blogId);
    return blog;
}


public void setBlogRepository(BlogRepository blogRepository){
    this.blogRepository = blogRepository;
}


@Transactional(propagation = Propagation.REQUIRED)
public List<Blog> findBlogByTime(){
    List<Blog> blog = this.blogRepository.findBlogByTime();
    return blog;
}


@Transactional(propagation = Propagation.REQUIRED)
public int updateReadNum(long blogId){
    int effectedNum = this.blogRepository.updateReadNum(blogId);
    if (effectedNum == 0) {
        return 1;
    } else {
        return 0;
    }
}


@Transactional(propagation = Propagation.REQUIRED)
public int updateTitle(long blogId,String title){
    int effectedNum = this.blogRepository.updateTitle(title, blogId);
    if (effectedNum == 0) {
        return 1;
    } else {
        return 0;
    }
}


}