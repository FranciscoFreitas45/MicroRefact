package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.DoPurchaseService;
public class DoPurchaseServiceImpl implements DoPurchaseService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Page<TempImportTask> findAllTempImportTask(Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllTempImportTask"))
    .queryParam("page",page)
;  Page<TempImportTask> aux = restTemplate.getForObject(builder.toUriString(), Page<TempImportTask>.class);

 return aux;
}


}