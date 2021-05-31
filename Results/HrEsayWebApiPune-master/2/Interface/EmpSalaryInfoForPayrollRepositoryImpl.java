import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class EmpSalaryInfoForPayrollRepositoryImpl implements EmpSalaryInfoForPayrollRepository{

 private RestTemplate restTemplate;

  String url = "http://19";


public List<EmpSalaryInfoForPayroll> getListForfixunfixAttendance(int month,int year,int isFixed,String sts,List<Integer> locId,int typeId,int deptId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getListForfixunfixAttendance"))
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("isFixed",isFixed)
    .queryParam("sts",sts)
    .queryParam("locId",locId)
    .queryParam("typeId",typeId)
    .queryParam("deptId",deptId);
  List<EmpSalaryInfoForPayroll> aux = restTemplate.getForObject(builder.toUriString(), List<EmpSalaryInfoForPayroll>.class)

 return aux;
}


public List<EmpSalaryInfoForPayroll> getListForfixunfixAttendanceDeptId(int month,int year,int isFixed,String sts,List<Integer> locId,int deptId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getListForfixunfixAttendanceDeptId"))
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("isFixed",isFixed)
    .queryParam("sts",sts)
    .queryParam("locId",locId)
    .queryParam("deptId",deptId);
  List<EmpSalaryInfoForPayroll> aux = restTemplate.getForObject(builder.toUriString(), List<EmpSalaryInfoForPayroll>.class)

 return aux;
}


public List<EmpSalaryInfoForPayroll> getListForfixunfixAttendanceTypeId(int month,int year,int isFixed,String sts,List<Integer> locId,int typeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getListForfixunfixAttendanceTypeId"))
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("isFixed",isFixed)
    .queryParam("sts",sts)
    .queryParam("locId",locId)
    .queryParam("typeId",typeId);
  List<EmpSalaryInfoForPayroll> aux = restTemplate.getForObject(builder.toUriString(), List<EmpSalaryInfoForPayroll>.class)

 return aux;
}


}