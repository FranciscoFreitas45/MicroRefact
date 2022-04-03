package com.dtdhehe.ptulife.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.dtdhehe.ptulife.Interface.NewsService;
public class NewsServiceImpl implements NewsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<PtuNews> queryAllNews(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryAllNews"))
;  List<PtuNews> aux = restTemplate.getForObject(builder.toUriString(), List<PtuNews>.class);

 return aux;
}


}