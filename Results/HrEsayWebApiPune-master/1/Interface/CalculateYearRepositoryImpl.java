import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class CalculateYearRepositoryImpl implements CalculateYearRepository{

 private RestTemplate restTemplate;

  String url = "http://8";


public List<CalenderYear> getAllCalYearOrderByDesc(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllCalYearOrderByDesc"))
  List<CalenderYear> aux = restTemplate.getForObject(builder.toUriString(), List<CalenderYear>.class)

 return aux;
}


public List<CalenderYear> findByCalYrToDateAndCalYrIdNot(String inputValue,int primaryKey){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCalYrToDateAndCalYrIdNot"))
    .queryParam("inputValue",inputValue)
    .queryParam("primaryKey",primaryKey);
  List<CalenderYear> aux = restTemplate.getForObject(builder.toUriString(), List<CalenderYear>.class)

 return aux;
}


public List<CalenderYear> findByCalYrToDate(String inputValue){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCalYrToDate"))
    .queryParam("inputValue",inputValue);
  List<CalenderYear> aux = restTemplate.getForObject(builder.toUriString(), List<CalenderYear>.class)

 return aux;
}


public List<CalenderYear> findByCalYrFromDateAndCalYrIdNot(String inputValue,int primaryKey){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCalYrFromDateAndCalYrIdNot"))
    .queryParam("inputValue",inputValue)
    .queryParam("primaryKey",primaryKey);
  List<CalenderYear> aux = restTemplate.getForObject(builder.toUriString(), List<CalenderYear>.class)

 return aux;
}


public List<CalenderYear> findByCalYrFromDate(String inputValue){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCalYrFromDate"))
    .queryParam("inputValue",inputValue);
  List<CalenderYear> aux = restTemplate.getForObject(builder.toUriString(), List<CalenderYear>.class)

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public int updateOtherIds(int calYrId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateOtherIds"))
    .queryParam("calYrId",calYrId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public CalenderYear findByCalYrId(int calYrId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCalYrId"))
    .queryParam("calYrId",calYrId);
  CalenderYear aux = restTemplate.getForObject(builder.toUriString(), CalenderYear.class)

 return aux;
}


public int countCalyear(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countCalyear"))
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public CalenderYear findByIsCurrent(int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIsCurrent"))
    .queryParam("i",i);
  CalenderYear aux = restTemplate.getForObject(builder.toUriString(), CalenderYear.class)

 return aux;
}


}