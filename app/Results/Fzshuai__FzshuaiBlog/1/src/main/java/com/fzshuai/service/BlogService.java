package com.fzshuai.service;
 import com.fzshuai.po.Blog;
import com.fzshuai.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
public interface BlogService {


public Blog getAndConvert(Long id)
;

public List<Blog> listRecommendBlogTop(Integer size)
;

public Blog saveBlog(Blog blog)
;

public Map<String,List<Blog>> archiveBlog()
;

public Blog getBlog(Long id)
;

public Long countBlog()
;

public void deleteBlog(Long id)
;

public Page<Blog> listBlog(String query,Pageable pageable)
;

public Blog updateBlog(Long id,Blog blog)
;

}