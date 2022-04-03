package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.DoPurchaseService;
public class DoPurchaseServiceImpl implements DoPurchaseService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<TempImportDetailView> findTempImportDetail(Integer taskId,String tempImportDetailStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTempImportDetail"))
    .queryParam("taskId",taskId)
    .queryParam("tempImportDetailStatus",tempImportDetailStatus)
;  List<TempImportDetailView> aux = restTemplate.getForObject(builder.toUriString(), List<TempImportDetailView>.class);

 return aux;
}


}