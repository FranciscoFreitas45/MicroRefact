package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.PositionService;
public class PositionServiceImpl implements PositionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<PositionGradeDTO> getPositionByDepartmentId(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPositionByDepartmentId"))
    .queryParam("id",id)
;  List<PositionGradeDTO> aux = restTemplate.getForObject(builder.toUriString(), List<PositionGradeDTO>.class);

 return aux;
}


}