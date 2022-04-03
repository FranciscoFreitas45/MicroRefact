package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.DoPurchaseService;
public class DoPurchaseServiceImpl implements DoPurchaseService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void addTempImportTask(List<TempImportDTO> list,TempImportTaskBizTypeEnum bizType,String memo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addTempImportTask"))
    .queryParam("list",list)
    .queryParam("bizType",bizType)
    .queryParam("memo",memo)
;
  restTemplate.put(builder.toUriString(), null);
}


}