package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.MaterialUseService;
public class MaterialUseServiceImpl implements MaterialUseService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public int countNoUseRecord(String companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countNoUseRecord"))
    .queryParam("companyId",companyId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<RecordBaseView> queryNoUseRecord(String companyId,Pager pager){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryNoUseRecord"))
    .queryParam("companyId",companyId)
    .queryParam("pager",pager)
;  List<RecordBaseView> aux = restTemplate.getForObject(builder.toUriString(), List<RecordBaseView>.class);

 return aux;
}


public int countHasUseRecord(String companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countHasUseRecord"))
    .queryParam("companyId",companyId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<RecordBaseView> queryHasUseRecord(String companyId,Pager pager){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryHasUseRecord"))
    .queryParam("companyId",companyId)
    .queryParam("pager",pager)
;  List<RecordBaseView> aux = restTemplate.getForObject(builder.toUriString(), List<RecordBaseView>.class);

 return aux;
}


public List<WorkInfo> userWorksStatusByPager(User user,String status,Pager pager){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/userWorksStatusByPager"))
    .queryParam("user",user)
    .queryParam("status",status)
    .queryParam("pager",pager)
;  List<WorkInfo> aux = restTemplate.getForObject(builder.toUriString(), List<WorkInfo>.class);

 return aux;
}


public int countUserWorksStatus(User user,String status){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countUserWorksStatus"))
    .queryParam("user",user)
    .queryParam("status",status)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<DetailTemp> queryDetailsByRecordId(String recordId,String companyId,Pager pager){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryDetailsByRecordId"))
    .queryParam("recordId",recordId)
    .queryParam("companyId",companyId)
    .queryParam("pager",pager)
;  List<DetailTemp> aux = restTemplate.getForObject(builder.toUriString(), List<DetailTemp>.class);

 return aux;
}


public int countDetailsByRecordId(String recordId,String companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countDetailsByRecordId"))
    .queryParam("recordId",recordId)
    .queryParam("companyId",companyId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<User> companyEmps(String companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/companyEmps"))
    .queryParam("companyId",companyId)
;  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


public boolean recordIsDisp(String recordId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/recordIsDisp"))
    .queryParam("recordId",recordId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public int insertWorkInfo(WorkInfo workInfo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insertWorkInfo"))
    .queryParam("workInfo",workInfo)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public String queryUserIdbyRecordId4workInfo(String recordId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryUserIdbyRecordId4workInfo"))
    .queryParam("recordId",recordId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public int updRunProInstStartUser(String newUserId,String recordId,String flowName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updRunProInstStartUser"))
    .queryParam("newUserId",newUserId)
    .queryParam("recordId",recordId)
    .queryParam("flowName",flowName)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int updWorkInfoUser(String recordId,String userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updWorkInfoUser"))
    .queryParam("recordId",recordId)
    .queryParam("userId",userId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}