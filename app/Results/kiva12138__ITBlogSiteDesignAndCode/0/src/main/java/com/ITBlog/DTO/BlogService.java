package com.ITBlog.DTO;
 import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ITBlog.ITBlogSiteBackEnd.Repository.BlogRepository;
import com.ITBlog.ITBlogSiteBackEnd.Entity;
public class BlogService {

 private  BlogRepository blogRepository;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";

@Autowired
public BlogService(BlogRepository blogRepository) {
    this.setBlogRepository(blogRepository);
}
public BlogRepository getBlogRepository(){
    return blogRepository;
}


@Transactional(propagation = Propagation.REQUIRED)
public int deleteBlog(long blogId){
    this.blogRepository.deleteById(blogId);
    return 0;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteBlog"))

.queryParam("blogId",blogId)
;
int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux;
}


}