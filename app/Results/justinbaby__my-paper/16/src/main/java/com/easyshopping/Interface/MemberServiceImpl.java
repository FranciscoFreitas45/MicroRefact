package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.MemberService;
public class MemberServiceImpl implements MemberService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Object[]> findPurchaseList(Date beginDate,Date endDate,Integer count){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPurchaseList"))
    .queryParam("beginDate",beginDate)
    .queryParam("endDate",endDate)
    .queryParam("count",count)
;  List<Object[]> aux = restTemplate.getForObject(builder.toUriString(), List<Object[]>.class);

 return aux;
}


}