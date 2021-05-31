import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class IGuestServiceImpl implements IGuestService{

 private RestTemplate restTemplate;

  String url = "http://14";


public Guest findGuestByIdCard(String idCard){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findGuestByIdCard"))
    .queryParam("idCard",idCard);
  Guest aux = restTemplate.getForObject(builder.toUriString(), Guest.class)

 return aux;
}


public void save(Guest guest){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("guest",guest);

  restTemplate.put(builder.toUriString(), null)



public List<Guest> findGuestByRoomNo(String roomNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findGuestByRoomNo"))
    .queryParam("roomNo",roomNo);
  List<Guest> aux = restTemplate.getForObject(builder.toUriString(), List<Guest>.class)

 return aux;
}


}