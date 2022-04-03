package com.fzshuai.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BlogServiceController {

 private BlogService blogservice;


@GetMapping
("/getBlog")
public Blog getBlog(@RequestParam(name = "id") Long id){
  return blogservice.getBlog(id);
}


@GetMapping
("/listBlog")
public Page<Blog> listBlog(@RequestParam(name = "query") String query,@RequestParam(name = "pageable") Pageable pageable){
  return blogservice.listBlog(query,pageable);
}


@GetMapping
("/listRecommendBlogTop")
public List<Blog> listRecommendBlogTop(@RequestParam(name = "size") Integer size){
  return blogservice.listRecommendBlogTop(size);
}


@GetMapping
("/updateBlog")
public Blog updateBlog(@RequestParam(name = "id") Long id,@RequestParam(name = "blog") Blog blog){
  return blogservice.updateBlog(id,blog);
}


@GetMapping
("/saveBlog")
public Blog saveBlog(@RequestParam(name = "blog") Blog blog){
  return blogservice.saveBlog(blog);
}


@PutMapping
("/deleteBlog")
public void deleteBlog(@RequestParam(name = "id") Long id){
blogservice.deleteBlog(id);
}


}