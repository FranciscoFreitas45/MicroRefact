package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.ConsultationDao;
public class ConsultationDaoImpl implements ConsultationDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Consultation> findList(Member member,Product product,Boolean isShow,Integer count,List<Filter> filters,List<Order> orders){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findList"))
    .queryParam("member",member)
    .queryParam("product",product)
    .queryParam("isShow",isShow)
    .queryParam("count",count)
    .queryParam("filters",filters)
    .queryParam("orders",orders)
;  List<Consultation> aux = restTemplate.getForObject(builder.toUriString(), List<Consultation>.class);

 return aux;
}


public Page<Consultation> findPage(Member member,Product product,Boolean isShow,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPage"))
    .queryParam("member",member)
    .queryParam("product",product)
    .queryParam("isShow",isShow)
    .queryParam("pageable",pageable)
;  Page<Consultation> aux = restTemplate.getForObject(builder.toUriString(), Page<Consultation>.class);

 return aux;
}


public Long count(Member member,Product product,Boolean isShow){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("member",member)
    .queryParam("product",product)
    .queryParam("isShow",isShow)
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


public Object merge(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/merge"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object persist(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/persist"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object flush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/flush"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}