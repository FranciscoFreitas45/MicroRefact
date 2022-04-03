package br.com.fatecmogidascruzes.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.Interface.EmployeeWebService;
public class EmployeeWebServiceImpl implements EmployeeWebService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<EmployeeEntryDTO> getEnabledByCourse(Course course){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEnabledByCourse"))
    .queryParam("course",course)
;  List<EmployeeEntryDTO> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeeEntryDTO>.class);

 return aux;
}


public List<EmployeeEntryDTO> getEnabledProfessors(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEnabledProfessors"))
;  List<EmployeeEntryDTO> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeeEntryDTO>.class);

 return aux;
}


}