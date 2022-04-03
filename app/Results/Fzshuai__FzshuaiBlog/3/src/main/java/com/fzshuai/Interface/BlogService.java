package com.fzshuai.Interface;
public interface BlogService {

   public Page<Blog> listBlog(String query,Pageable pageable);
}