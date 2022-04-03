package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.TempImportBO;
public class TempImportBOImpl implements TempImportBO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public void addTempImportTask(List<TempImportDTO> list,TempImportTaskBizTypeEnum bizType,String memo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addTempImportTask"))
    .queryParam("list",list)
    .queryParam("bizType",bizType)
    .queryParam("memo",memo)
;
  restTemplate.put(builder.toUriString(), null);
}


public void updateTempImportDetail(Integer taskId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateTempImportDetail"))
    .queryParam("taskId",taskId)
;
  restTemplate.put(builder.toUriString(), null);
}


public String updateAssociateTempImportDetailWithBookInfo(Integer tempImportDetailId,Integer bookId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateAssociateTempImportDetailWithBookInfo"))
    .queryParam("tempImportDetailId",tempImportDetailId)
    .queryParam("bookId",bookId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String updateAssociatePurchaseTempImportWithBookInfo(TempImportDetail detail,Bookinfo book){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateAssociatePurchaseTempImportWithBookInfo"))
    .queryParam("detail",detail)
    .queryParam("book",book)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public Page<TempImportTask> findAllTempImportTask(Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllTempImportTask"))
    .queryParam("page",page)
;  Page<TempImportTask> aux = restTemplate.getForObject(builder.toUriString(), Page<TempImportTask>.class);

 return aux;
}


public TempImportTask findTempImportTaskByTaskId(Integer taskId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTempImportTaskByTaskId"))
    .queryParam("taskId",taskId)
;  TempImportTask aux = restTemplate.getForObject(builder.toUriString(), TempImportTask.class);

 return aux;
}


public List<TempImportDetailView> findTempImportDetail(Integer taskId,String tempImportDetailStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTempImportDetail"))
    .queryParam("taskId",taskId)
    .queryParam("tempImportDetailStatus",tempImportDetailStatus)
;  List<TempImportDetailView> aux = restTemplate.getForObject(builder.toUriString(), List<TempImportDetailView>.class);

 return aux;
}


public void updateTempImportTaskStatus(Integer taskId,Integer tempImportTaskStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateTempImportTaskStatus"))
    .queryParam("taskId",taskId)
    .queryParam("tempImportTaskStatus",tempImportTaskStatus)
;
  restTemplate.put(builder.toUriString(), null);
}


}