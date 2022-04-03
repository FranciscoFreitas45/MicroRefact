package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.IJobFailureService;
public class IJobFailureServiceImpl implements IJobFailureService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<JobFailureEntity> listTopNByJobAndTableName(String jobName,String tableName,Integer topN){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listTopNByJobAndTableName"))
    .queryParam("jobName",jobName)
    .queryParam("tableName",tableName)
    .queryParam("topN",topN)
;  List<JobFailureEntity> aux = restTemplate.getForObject(builder.toUriString(), List<JobFailureEntity>.class);

 return aux;
}


public Boolean deleteJobFailure(int innerid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteJobFailure"))
    .queryParam("innerid",innerid)
;  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


public boolean saveFailureJob(String jobName,String tableName,List<Integer> idList,Date updateTime,int num){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveFailureJob"))
    .queryParam("jobName",jobName)
    .queryParam("tableName",tableName)
    .queryParam("idList",idList)
    .queryParam("updateTime",updateTime)
    .queryParam("num",num)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}