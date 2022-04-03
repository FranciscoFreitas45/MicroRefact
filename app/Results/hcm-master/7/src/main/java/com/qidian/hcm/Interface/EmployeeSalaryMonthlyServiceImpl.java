package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.EmployeeSalaryMonthlyService;
public class EmployeeSalaryMonthlyServiceImpl implements EmployeeSalaryMonthlyService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<SalaryItemMonthlyResponse> getSalaryMonthly(Long employeeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSalaryMonthly"))
    .queryParam("employeeId",employeeId)
;  List<SalaryItemMonthlyResponse> aux = restTemplate.getForObject(builder.toUriString(), List<SalaryItemMonthlyResponse>.class);

 return aux;
}


public List<SalaryItemAccountingDTO> getEmployeeAccounting(Long employeeId,SalaryItemAccountingDTO salaryItemMonthlyRequest){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmployeeAccounting"))
    .queryParam("employeeId",employeeId)
    .queryParam("salaryItemMonthlyRequest",salaryItemMonthlyRequest)
;  List<SalaryItemAccountingDTO> aux = restTemplate.getForObject(builder.toUriString(), List<SalaryItemAccountingDTO>.class);

 return aux;
}


public void saveDetailInfo(Long employeeId,List<SalaryItemMonthlyRequest> salaryItemMonthlyRequestList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveDetailInfo"))
    .queryParam("employeeId",employeeId)
    .queryParam("salaryItemMonthlyRequestList",salaryItemMonthlyRequestList)
;
  restTemplate.put(builder.toUriString(), null);
}


public void salaryExclude(EmployeeListRequest employeeListRequest){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/salaryExclude"))
    .queryParam("employeeListRequest",employeeListRequest)
;
  restTemplate.put(builder.toUriString(), null);
}


public void salaryInclude(EmployeeListRequest employeeListRequest){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/salaryInclude"))
    .queryParam("employeeListRequest",employeeListRequest)
;
  restTemplate.put(builder.toUriString(), null);
}


public Page<EmployeeSalaryPageResponse> getEmployeeSalaryPage(FilterEmployeesSalaryRequest filterEmployeesSalary){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmployeeSalaryPage"))
    .queryParam("filterEmployeesSalary",filterEmployeesSalary)
;  Page<EmployeeSalaryPageResponse> aux = restTemplate.getForObject(builder.toUriString(), Page<EmployeeSalaryPageResponse>.class);

 return aux;
}


}