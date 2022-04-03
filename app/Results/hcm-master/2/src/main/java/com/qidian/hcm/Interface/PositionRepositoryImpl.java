package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.PositionRepository;
public class PositionRepositoryImpl implements PositionRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<PositionEntity> findAllByDepartmentIdInAndEnableAndDeleted(List<Long> departmentIds,YesNo enable,YesNo deleted){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByDepartmentIdInAndEnableAndDeleted"))
    .queryParam("departmentIds",departmentIds)
    .queryParam("enable",enable)
    .queryParam("deleted",deleted)
;  List<PositionEntity> aux = restTemplate.getForObject(builder.toUriString(), List<PositionEntity>.class);

 return aux;
}


public List<PositionEntity> findAllByDepartmentIdAndEnableAndDeleted(Long departmentId,YesNo enable,YesNo deleted){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByDepartmentIdAndEnableAndDeleted"))
    .queryParam("departmentId",departmentId)
    .queryParam("enable",enable)
    .queryParam("deleted",deleted)
;  List<PositionEntity> aux = restTemplate.getForObject(builder.toUriString(), List<PositionEntity>.class);

 return aux;
}


}