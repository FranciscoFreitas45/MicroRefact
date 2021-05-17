import org.springframework.web.client.RestTemplate;
public class SpecialityRequestImpl implements SpecialityRequest{

 private RestTemplate restTemplate;


public Speciality getSpeciality(Long Long){
 Speciality aux = restTemplate.getForObject('http://2/Doctor/{id}/Speciality/getSpeciality',Speciality.class,Long);
return aux;
}


public void setSpeciality(Speciality speciality,Long Long){
 restTemplate.put('http://2/Doctor/{id}/Speciality/setSpeciality',speciality,Long);
 return ;
}


}