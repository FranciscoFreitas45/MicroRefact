package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.INewsSpreadingAnalysisService;
public class INewsSpreadingAnalysisServiceImpl implements INewsSpreadingAnalysisService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<NewsSpreadingAnalysisDetail> listNewsSpreadingAnalysisDetailByIds(List<Integer> idList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listNewsSpreadingAnalysisDetailByIds"))
    .queryParam("idList",idList)
;  List<NewsSpreadingAnalysisDetail> aux = restTemplate.getForObject(builder.toUriString(), List<NewsSpreadingAnalysisDetail>.class);

 return aux;
}


}