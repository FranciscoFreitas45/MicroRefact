package com.fzshuai.Request;
import com.fzshuai.DTO.Blog;
public interface BlogRequest {

   public Blog getBlog(Long id);
   public void setBlog(Blog blog,Long id);
}