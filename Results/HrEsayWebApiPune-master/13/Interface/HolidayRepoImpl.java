import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class HolidayRepoImpl implements HolidayRepo{

 private RestTemplate restTemplate;

  String url = "http://10";


public Object saveAndFlush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAndFlush"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public Object saveAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAll"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public List<Holiday> getHolidayListByDates(List<String> dates,int catId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getHolidayListByDates"))
    .queryParam("dates",dates)
    .queryParam("catId",catId);
  List<Holiday> aux = restTemplate.getForObject(builder.toUriString(), List<Holiday>.class)

 return aux;
}


public Holiday findByHolidayIdAndDelStatus(int holidayId,int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByHolidayIdAndDelStatus"))
    .queryParam("holidayId",holidayId)
    .queryParam("i",i);
  Holiday aux = restTemplate.getForObject(builder.toUriString(), Holiday.class)

 return aux;
}


public List<Holiday> getHolidayByYearIdAndCateId(int yearId,int catId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getHolidayByYearIdAndCateId"))
    .queryParam("yearId",yearId)
    .queryParam("catId",catId);
  List<Holiday> aux = restTemplate.getForObject(builder.toUriString(), List<Holiday>.class)

 return aux;
}


public int deleteHoliday(int holidayId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteHoliday"))
    .queryParam("holidayId",holidayId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int deleteHolidayByGroup(int yearId,int catid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteHolidayByGroup"))
    .queryParam("yearId",yearId)
    .queryParam("catid",catid);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}