package com.fzshuai.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.dao.BlogRepository;
import com.fzshuai.po.Blog;
@Service
public class BlogUserService {

@Autowired
 private BlogRepository blogrepository;


public void setBlogs(Long id,List<Blog> blogs){
blogrepository.setBlogs(id,blogs);
}


public List<Blog> getBlogs(Long id){
return blogrepository.getBlogs(id);
}


}