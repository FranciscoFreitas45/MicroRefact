package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.ArticleCategoryService;
public class ArticleCategoryServiceImpl implements ArticleCategoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<ArticleCategory> findTree(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTree"))
;  List<ArticleCategory> aux = restTemplate.getForObject(builder.toUriString(), List<ArticleCategory>.class);

 return aux;
}


}