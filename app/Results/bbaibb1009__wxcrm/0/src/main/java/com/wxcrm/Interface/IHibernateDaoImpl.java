package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IHibernateDao;
public class IHibernateDaoImpl implements IHibernateDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Serializable add(Object entity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/add"))
    .queryParam("entity",entity)
;  Serializable aux = restTemplate.getForObject(builder.toUriString(), Serializable.class);

 return aux;
}


public T get(Class<T> entityClass,Serializable id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("entityClass",entityClass)
    .queryParam("id",id)
;  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


public void update(Object entity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("entity",entity)
;
  restTemplate.put(builder.toUriString(), null);
}


}