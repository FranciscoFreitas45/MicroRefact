import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class DoctorServiceImpl implements DoctorService{

 private RestTemplate restTemplate;

  String url = "http://0";


public List<Doctor> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
  List<Doctor> aux = restTemplate.getForObject(builder.toUriString(), List<Doctor>.class)

 return aux;
}


}