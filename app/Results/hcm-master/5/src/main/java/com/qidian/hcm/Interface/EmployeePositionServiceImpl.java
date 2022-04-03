package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.EmployeePositionService;
public class EmployeePositionServiceImpl implements EmployeePositionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Map<Long,EmployeePosition> getEmployeeCurrentPositionMap(List<Long> employeeIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmployeeCurrentPositionMap"))
    .queryParam("employeeIds",employeeIds)
;  Map<Long,EmployeePosition> aux = restTemplate.getForObject(builder.toUriString(), Map<Long,EmployeePosition>.class);

 return aux;
}


public List<EmployeePosition> findAllByCondition(Specification<EmployeePosition> condition){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByCondition"))
    .queryParam("condition",condition)
;  List<EmployeePosition> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeePosition>.class);

 return aux;
}


public List<EmployeePosition> findAllByExcludeEmployeeStatus(EmployeeStatus status){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByExcludeEmployeeStatus"))
    .queryParam("status",status)
;  List<EmployeePosition> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeePosition>.class);

 return aux;
}


public List<EmployeePositionDTO> getEmployeePositionDTOList(Long employeeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmployeePositionDTOList"))
    .queryParam("employeeId",employeeId)
;  List<EmployeePositionDTO> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeePositionDTO>.class);

 return aux;
}


public void saveEmployeePositions(Long employeeId,List<EmployeePositionDTO> employeePositionDTOList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveEmployeePositions"))
    .queryParam("employeeId",employeeId)
    .queryParam("employeePositionDTOList",employeePositionDTOList)
;
  restTemplate.put(builder.toUriString(), null);
}


public void addEmployeePositionForTransferredEmployee(Long employeeId,TransferEmployeeRequest transferEmployeeRequest){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addEmployeePositionForTransferredEmployee"))
    .queryParam("employeeId",employeeId)
    .queryParam("transferEmployeeRequest",transferEmployeeRequest)
;
  restTemplate.put(builder.toUriString(), null);
}


public void updateLeaderId(Long employeeId,Long handoverManId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateLeaderId"))
    .queryParam("employeeId",employeeId)
    .queryParam("handoverManId",handoverManId)
;
  restTemplate.put(builder.toUriString(), null);
}


public EmployeePosition getCurrentEmployeePosition(Long employeeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCurrentEmployeePosition"))
    .queryParam("employeeId",employeeId)
;  EmployeePosition aux = restTemplate.getForObject(builder.toUriString(), EmployeePosition.class);

 return aux;
}


}