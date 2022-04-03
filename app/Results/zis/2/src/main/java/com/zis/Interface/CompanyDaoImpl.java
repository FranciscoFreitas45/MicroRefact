package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.CompanyDao;
public class CompanyDaoImpl implements CompanyDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Page<Company> findAllCompany(Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllCompany"))
    .queryParam("page",page)
;  Page<Company> aux = restTemplate.getForObject(builder.toUriString(), Page<Company>.class);

 return aux;
}


public Page<Company> findByContacts(String contacts,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByContacts"))
    .queryParam("contacts",contacts)
    .queryParam("page",page)
;  Page<Company> aux = restTemplate.getForObject(builder.toUriString(), Page<Company>.class);

 return aux;
}


public Page<Company> findByLikeCompanyName(String companyName,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByLikeCompanyName"))
    .queryParam("companyName",companyName)
    .queryParam("page",page)
;  Page<Company> aux = restTemplate.getForObject(builder.toUriString(), Page<Company>.class);

 return aux;
}


public Company findByCompanyId(Integer companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCompanyId"))
    .queryParam("companyId",companyId)
;  Company aux = restTemplate.getForObject(builder.toUriString(), Company.class);

 return aux;
}


public Company findByCompanyName(String companyName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCompanyName"))
    .queryParam("companyName",companyName)
;  Company aux = restTemplate.getForObject(builder.toUriString(), Company.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}