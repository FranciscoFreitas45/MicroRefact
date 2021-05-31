import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class HolidayMasterRepoImpl implements HolidayMasterRepo{

 private RestTemplate restTemplate;

  String url = "http://13";


public List<HolidayMaster> getHolidaysForDash(String currDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getHolidaysForDash"))
    .queryParam("currDate",currDate);
  List<HolidayMaster> aux = restTemplate.getForObject(builder.toUriString(), List<HolidayMaster>.class)

 return aux;
}


public List<HolidayMaster> getUserApplicableHoliday(String date,int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserApplicableHoliday"))
    .queryParam("date",date)
    .queryParam("empId",empId);
  List<HolidayMaster> aux = restTemplate.getForObject(builder.toUriString(), List<HolidayMaster>.class)

 return aux;
}


}