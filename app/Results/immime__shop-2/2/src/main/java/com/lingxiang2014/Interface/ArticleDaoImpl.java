package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.ArticleDao;
public class ArticleDaoImpl implements ArticleDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<Article> findList(ArticleCategory articleCategory,Date beginDate,Date endDate,Integer first,Integer count){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findList"))
    .queryParam("articleCategory",articleCategory)
    .queryParam("beginDate",beginDate)
    .queryParam("endDate",endDate)
    .queryParam("first",first)
    .queryParam("count",count)
;  List<Article> aux = restTemplate.getForObject(builder.toUriString(), List<Article>.class);

 return aux;
}


public Object clear(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/clear"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object count(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}