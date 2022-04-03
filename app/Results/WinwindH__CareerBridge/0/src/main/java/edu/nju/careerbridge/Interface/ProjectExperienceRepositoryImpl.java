package edu.nju.careerbridge.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import edu.nju.careerbridge.Interface.ProjectExperienceRepository;
public class ProjectExperienceRepositoryImpl implements ProjectExperienceRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void deleteByPhone(String phone){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteByPhone"))
    .queryParam("phone",phone)
;
  restTemplate.put(builder.toUriString(), null);
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<ProjectExperience> findByPhone(String phone){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByPhone"))
    .queryParam("phone",phone)
;  List<ProjectExperience> aux = restTemplate.getForObject(builder.toUriString(), List<ProjectExperience>.class);

 return aux;
}


}