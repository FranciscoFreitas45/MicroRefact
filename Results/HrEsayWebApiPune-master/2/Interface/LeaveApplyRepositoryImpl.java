import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class LeaveApplyRepositoryImpl implements LeaveApplyRepository{

 private RestTemplate restTemplate;

  String url = "http://1";


public List<LeaveApply> leaveListAddeBySystem(String fromDate,String toDate,int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/leaveListAddeBySystem"))
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate)
    .queryParam("empId",empId);
  List<LeaveApply> aux = restTemplate.getForObject(builder.toUriString(), List<LeaveApply>.class)

 return aux;
}


public List<LeaveApply> getleavetListForAttndace(String fromDate,String toDate,int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getleavetListForAttndace"))
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate)
    .queryParam("empId",empId);
  List<LeaveApply> aux = restTemplate.getForObject(builder.toUriString(), List<LeaveApply>.class)

 return aux;
}


public int reverseupdateNoOfDaysInLeave(int leaveId,float updateNoOfDays,String reason){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reverseupdateNoOfDaysInLeave"))
    .queryParam("leaveId",leaveId)
    .queryParam("updateNoOfDays",updateNoOfDays)
    .queryParam("reason",reason);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int deleteByLeaveId(int leaveId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteByLeaveId"))
    .queryParam("leaveId",leaveId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int updateNoOfDaysInLeave(int leaveId,float updateNoOfDays,String reason){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateNoOfDaysInLeave"))
    .queryParam("leaveId",leaveId)
    .queryParam("updateNoOfDays",updateNoOfDays)
    .queryParam("reason",reason);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public Object saveAndFlush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAndFlush"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public int updateLeaveApply(int leaveId,int trailId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateLeaveApply"))
    .queryParam("leaveId",leaveId)
    .queryParam("trailId",trailId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}