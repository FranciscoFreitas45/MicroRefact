package org.sdrc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.sdrc.Interface.DashboardService;
public class DashboardServiceImpl implements DashboardService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<UtDataModel> fetchPdfData(String indicatorId,String sourceId,String areaId,String timePeriodId,Integer childLevel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fetchPdfData"))
    .queryParam("indicatorId",indicatorId)
    .queryParam("sourceId",sourceId)
    .queryParam("areaId",areaId)
    .queryParam("timePeriodId",timePeriodId)
    .queryParam("childLevel",childLevel)
;  List<UtDataModel> aux = restTemplate.getForObject(builder.toUriString(), List<UtDataModel>.class);

 return aux;
}


}