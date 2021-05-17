import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SlotServiceImpl implements SlotService{

 private RestTemplate restTemplate;

  String url = "http://1";


public Long countAllByIsAvailableAndDoctorAndDateBetween(boolean isAvailable,Doctor doctor,LocalDate dateBegin,LocalDate dateEnd){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countAllByIsAvailableAndDoctorAndDateBetween"))
    .queryParam("isAvailable",isAvailable)
    .queryParam("doctor",doctor)
    .queryParam("dateBegin",dateBegin)
    .queryParam("dateEnd",dateEnd);
  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class)

 return aux;
}


public Long countAllByIsAvailableAndDoctorSpecialityAndDateBetween(boolean isAvailable,Speciality speciality,LocalDate dateBegin,LocalDate dateEnd){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countAllByIsAvailableAndDoctorSpecialityAndDateBetween"))
    .queryParam("isAvailable",isAvailable)
    .queryParam("speciality",speciality)
    .queryParam("dateBegin",dateBegin)
    .queryParam("dateEnd",dateEnd);
  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class)

 return aux;
}


}