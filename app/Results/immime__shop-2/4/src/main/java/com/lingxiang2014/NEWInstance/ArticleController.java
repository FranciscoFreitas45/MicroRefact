package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ArticleController {

 private Article article;

 private Article article;


@PutMapping
("/setPageNumber")
public void setPageNumber(@RequestParam(name = "pageNumber") Integer pageNumber){
article.setPageNumber(pageNumber);
}


}