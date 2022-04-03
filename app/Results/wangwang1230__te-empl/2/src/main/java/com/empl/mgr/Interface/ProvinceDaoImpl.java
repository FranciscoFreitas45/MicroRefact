package com.empl.mgr.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.empl.mgr.Interface.ProvinceDao;
public class ProvinceDaoImpl implements ProvinceDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<AddressDto> findAllProvince(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllProvince"))
;  List<AddressDto> aux = restTemplate.getForObject(builder.toUriString(), List<AddressDto>.class);

 return aux;
}


}