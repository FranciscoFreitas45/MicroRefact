import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class CommonFunctionServiceImpl implements CommonFunctionService{

 private RestTemplate restTemplate;

  String url = "http://9";


public Integer findDateInWeekEnd(String fromDate,String toDate,List<WeeklyOff> weeklyList,List<WeeklyOffShit> weeklyOffShitList,int locationId,int weekendCatId,int empId,List<WeeklyOffShit> weeklyOffShitFromList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findDateInWeekEnd"))
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate)
    .queryParam("weeklyList",weeklyList)
    .queryParam("weeklyOffShitList",weeklyOffShitList)
    .queryParam("locationId",locationId)
    .queryParam("weekendCatId",weekendCatId)
    .queryParam("empId",empId)
    .queryParam("weeklyOffShitFromList",weeklyOffShitFromList);
  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class)

 return aux;
}


public Integer findDateInHoliday(String fromDate,String toDate,List<Holiday> holidayList,int locationId,int holidayCatId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findDateInHoliday"))
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate)
    .queryParam("holidayList",holidayList)
    .queryParam("locationId",locationId)
    .queryParam("holidayCatId",holidayCatId);
  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class)

 return aux;
}


public LeaveStsAndLeaveId findDateInLeave(String fromDate,List<LeaveApply> leavetList,int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findDateInLeave"))
    .queryParam("fromDate",fromDate)
    .queryParam("leavetList",leavetList)
    .queryParam("empId",empId);
  LeaveStsAndLeaveId aux = restTemplate.getForObject(builder.toUriString(), LeaveStsAndLeaveId.class)

 return aux;
}


public int findDateInOptionalHoliday(String format,List<EmpListForHolidayApprove> optionalHolidayList,int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findDateInOptionalHoliday"))
    .queryParam("format",format)
    .queryParam("optionalHolidayList",optionalHolidayList)
    .queryParam("empId",empId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public List<String> getDatesOfWeeklyOfForShiftingDate(String fromDate,String toDate,List<WeeklyOff> weeklyOfflist,int locationId,int holidayCatId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDatesOfWeeklyOfForShiftingDate"))
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate)
    .queryParam("weeklyOfflist",weeklyOfflist)
    .queryParam("locationId",locationId)
    .queryParam("holidayCatId",holidayCatId);
  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class)

 return aux;
}


}