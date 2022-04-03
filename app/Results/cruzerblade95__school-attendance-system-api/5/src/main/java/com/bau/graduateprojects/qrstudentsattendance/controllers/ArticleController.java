package com.bau.graduateprojects.qrstudentsattendance.controllers;
 import com.bau.graduateprojects.qrstudentsattendance.entities.ArticleEntity;
import com.bau.graduateprojects.qrstudentsattendance.servicies.article.ArticleService;
import org.springframework.web.bind.annotation;
import java.util.List;
@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

 private  ArticleService articleService;

public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
}
@GetMapping("/{id}")
public ArticleEntity getArticleById(Long id){
    return articleService.getArticleById(id);
}


@DeleteMapping("/{id}")
public void removeById(Long id){
    articleService.remove(id);
}


@PostMapping
public ArticleEntity insert(ArticleEntity articleEntity){
    return articleService.insert(articleEntity);
}


@GetMapping
public List<ArticleEntity> list(){
    return articleService.list();
}


@GetMapping("/count")
public Long getCount(){
    return articleService.getCount();
}


}