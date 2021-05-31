import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class EmployeeMasterRepositoryImpl implements EmployeeMasterRepository{

 private RestTemplate restTemplate;

  String url = "http://3";


public int empEmpShiftGroupUpdate(List<Integer> empIdList,String upDateId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/empEmpShiftGroupUpdate"))
    .queryParam("empIdList",empIdList)
    .queryParam("upDateId",upDateId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int empEmpCategoryUpdate(List<Integer> empIdList,String upDateId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/empEmpCategoryUpdate"))
    .queryParam("empIdList",empIdList)
    .queryParam("upDateId",upDateId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int empSkillUpdate(List<Integer> empIdList,String skillId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/empSkillUpdate"))
    .queryParam("empIdList",empIdList)
    .queryParam("skillId",skillId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int weekHoliCat(List<Integer> empIdList,String holiCatId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/weekHoliCat"))
    .queryParam("empIdList",empIdList)
    .queryParam("holiCatId",holiCatId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int assignShift(List<Integer> empIdList,String shiftId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/assignShift"))
    .queryParam("empIdList",empIdList)
    .queryParam("shiftId",shiftId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int assignLocation(List<Integer> empIdList,String locId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/assignLocation"))
    .queryParam("empIdList",empIdList)
    .queryParam("locId",locId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int assignEmpType(List<Integer> empIdList,String typeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/assignEmpType"))
    .queryParam("empIdList",empIdList)
    .queryParam("typeId",typeId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int assignDesignation(List<Integer> empIdList,String desnId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/assignDesignation"))
    .queryParam("empIdList",empIdList)
    .queryParam("desnId",desnId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int assignDept(List<Integer> empIdList,String deptId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/assignDept"))
    .queryParam("empIdList",empIdList)
    .queryParam("deptId",deptId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int assignComapny(List<Integer> empIdList,String compId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/assignComapny"))
    .queryParam("empIdList",empIdList)
    .queryParam("compId",compId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int assignHoliCat(List<Integer> empIdList,String holiCatId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/assignHoliCat"))
    .queryParam("empIdList",empIdList)
    .queryParam("holiCatId",holiCatId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public List<EmployeeMaster> getEmpSalAssign(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpSalAssign"))
  List<EmployeeMaster> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeeMaster>.class)

 return aux;
}


public List<EmployeeMaster> findByEmpTypeAndDelStatus(int empTypeId,int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmpTypeAndDelStatus"))
    .queryParam("empTypeId",empTypeId)
    .queryParam("i",i);
  List<EmployeeMaster> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeeMaster>.class)

 return aux;
}


}