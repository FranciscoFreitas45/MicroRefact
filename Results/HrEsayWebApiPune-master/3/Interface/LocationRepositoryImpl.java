import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class LocationRepositoryImpl implements LocationRepository{

 private RestTemplate restTemplate;

  String url = "http://13";


public Object saveAndFlush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAndFlush"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public List<Location> findByDelStatus(int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDelStatus"))
    .queryParam("i",i);
  List<Location> aux = restTemplate.getForObject(builder.toUriString(), List<Location>.class)

 return aux;
}


public List<Location> findByDelStatusAndCompIdOrderByLocIdDesc(int i,int companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDelStatusAndCompIdOrderByLocIdDesc"))
    .queryParam("i",i)
    .queryParam("companyId",companyId);
  List<Location> aux = restTemplate.getForObject(builder.toUriString(), List<Location>.class)

 return aux;
}


public int deleteLocation(int locId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteLocation"))
    .queryParam("locId",locId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public Location findByLocIdAndDelStatus(int locId,int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByLocIdAndDelStatus"))
    .queryParam("locId",locId)
    .queryParam("i",i);
  Location aux = restTemplate.getForObject(builder.toUriString(), Location.class)

 return aux;
}


public List<Location> getLocationsByIds(List<Location> locIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLocationsByIds"))
    .queryParam("locIds",locIds);
  List<Location> aux = restTemplate.getForObject(builder.toUriString(), List<Location>.class)

 return aux;
}


}