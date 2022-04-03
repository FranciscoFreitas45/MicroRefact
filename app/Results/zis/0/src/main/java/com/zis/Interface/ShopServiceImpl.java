package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.ShopService;
import com.zis.shop.bean.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.zis.shop.dto.SaveOrUpdateCompanyDto;
import java.util.*;
import com.zis.DTO.ShopInfo;
public class ShopServiceImpl implements ShopService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Page<Company> queryCompany(String companyName,String contacts,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCompany"))
    .queryParam("companyName",companyName)
    .queryParam("contacts",contacts)
    .queryParam("page",page)
;  Page<Company> aux = restTemplate.getForObject(builder.toUriString(), Page.class);

 return aux;
}


public Company findCompanyOne(Integer companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCompanyOne"))
    .queryParam("companyId",companyId)
;  Company aux = restTemplate.getForObject(builder.toUriString(), Company.class);

 return aux;
}


public void updateCompany(SaveOrUpdateCompanyDto dto){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateCompany"))
    .queryParam("dto",dto)
;
  restTemplate.put(builder.toUriString(), null);
}


public void saveCompany(SaveOrUpdateCompanyDto dto){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveCompany"))
    .queryParam("dto",dto)
;
  restTemplate.put(builder.toUriString(), null);
}


public List<ShopInfo> findCompanyShop(Integer companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCompanyShop"))
    .queryParam("companyId",companyId)
;  List<ShopInfo> aux = restTemplate.getForObject(builder.toUriString(), List.class);

 return aux;
}


}