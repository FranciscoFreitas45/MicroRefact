package com.dtdhehe.ptulife.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.dtdhehe.ptulife.Interface.NewsService;
public class NewsServiceImpl implements NewsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Page<PtuNews> queryNewsByUserId(String userId,String newsTitle,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryNewsByUserId"))
    .queryParam("userId",userId)
    .queryParam("newsTitle",newsTitle)
    .queryParam("pageable",pageable)
;  Page<PtuNews> aux = restTemplate.getForObject(builder.toUriString(), Page<PtuNews>.class);

 return aux;
}


public void delNewsById(String newsId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delNewsById"))
    .queryParam("newsId",newsId)
;
  restTemplate.put(builder.toUriString(), null);
}


}