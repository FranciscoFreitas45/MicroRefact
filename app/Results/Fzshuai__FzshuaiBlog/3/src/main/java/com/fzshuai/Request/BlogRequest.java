package com.fzshuai.Request;
import com.fzshuai.DTO.Blog;
public interface BlogRequest {

   public void setBlogs(List<Blog> blogs,Long id);
   public List<Blog> getBlogs(Long id);
}