package com.empl.mgr.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.empl.mgr.Interface.EmployeesTrainingLogDao;
public class EmployeesTrainingLogDaoImpl implements EmployeesTrainingLogDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<EmployeesTrainingRecordDto> findTrainingRecord(long emplId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTrainingRecord"))
    .queryParam("emplId",emplId)
;  List<EmployeesTrainingRecordDto> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeesTrainingRecordDto>.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public TeEmployeesTrainingLog findByEmplTraining(long emplId,long trainingId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmplTraining"))
    .queryParam("emplId",emplId)
    .queryParam("trainingId",trainingId)
;  TeEmployeesTrainingLog aux = restTemplate.getForObject(builder.toUriString(), TeEmployeesTrainingLog.class);

 return aux;
}


public Object deleteByProperty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteByProperty"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}