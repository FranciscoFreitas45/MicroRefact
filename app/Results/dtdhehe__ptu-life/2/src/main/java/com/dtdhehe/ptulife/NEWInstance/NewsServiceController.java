package com.dtdhehe.ptulife.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NewsServiceController {

 private NewsService newsservice;


@GetMapping
("/queryNewsByUserId")
public Page<PtuNews> queryNewsByUserId(@RequestParam(name = "userId") String userId,@RequestParam(name = "newsTitle") String newsTitle,@RequestParam(name = "pageable") Pageable pageable){
  return newsservice.queryNewsByUserId(userId,newsTitle,pageable);
}


@PutMapping
("/delNewsById")
public void delNewsById(@RequestParam(name = "newsId") String newsId){
newsservice.delNewsById(newsId);
}


@GetMapping
("/queryAllNews")
public List<PtuNews> queryAllNews(){
  return newsservice.queryAllNews();
}


}