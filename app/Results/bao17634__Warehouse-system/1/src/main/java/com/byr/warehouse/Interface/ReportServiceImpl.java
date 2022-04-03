package com.byr.warehouse.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.byr.warehouse.Interface.ReportService;
public class ReportServiceImpl implements ReportService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<StockHUB> generateStoreReoport(Date date){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/generateStoreReoport"))
    .queryParam("date",date)
;  List<StockHUB> aux = restTemplate.getForObject(builder.toUriString(), List<StockHUB>.class);

 return aux;
}


}