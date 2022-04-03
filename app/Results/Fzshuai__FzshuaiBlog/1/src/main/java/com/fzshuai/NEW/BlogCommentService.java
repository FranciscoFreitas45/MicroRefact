package com.fzshuai.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.dao.BlogRepository;
import com.fzshuai.po.Blog;
@Service
public class BlogCommentService {

@Autowired
 private BlogRepository blogrepository;


public Blog getBlog(Long id){
return blogrepository.getBlog(id);
}


public void setBlog(Long id,Blog blog){
blogrepository.setBlog(id,blog);
}


}