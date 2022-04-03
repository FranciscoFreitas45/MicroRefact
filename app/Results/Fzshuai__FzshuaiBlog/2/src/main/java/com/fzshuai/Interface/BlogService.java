package com.fzshuai.Interface;
public interface BlogService {

   public Page<Blog> listBlog(String query,Pageable pageable);
   public List<Blog> listRecommendBlogTop(Integer size);
}