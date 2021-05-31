import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class ShiftAssignDailyRepositoryImpl implements ShiftAssignDailyRepository{

 private RestTemplate restTemplate;

  String url = "http://2";


public int updateAssignShiftByDate(List<Integer> empIdList,String fromDate,String toDate,int shiftId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateAssignShiftByDate"))
    .queryParam("empIdList",empIdList)
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate)
    .queryParam("shiftId",shiftId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}