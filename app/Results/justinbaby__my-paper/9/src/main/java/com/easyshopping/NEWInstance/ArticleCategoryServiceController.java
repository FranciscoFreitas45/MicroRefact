package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ArticleCategoryServiceController {

 private ArticleCategoryService articlecategoryservice;


@GetMapping
("/findTree")
public List<ArticleCategory> findTree(){
  return articlecategoryservice.findTree();
}


}