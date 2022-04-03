package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ArticleDaoController {

 private ArticleDao articledao;


@GetMapping
("/findList")
public List<Article> findList(@RequestParam(name = "articleCategory") ArticleCategory articleCategory,@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate,@RequestParam(name = "first") Integer first,@RequestParam(name = "count") Integer count){
  return articledao.findList(articleCategory,beginDate,endDate,first,count);
}


@GetMapping
("/clear")
public Object clear(@RequestParam(name = "Object") Object Object){
  return articledao.clear(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return articledao.count(Object);
}


}