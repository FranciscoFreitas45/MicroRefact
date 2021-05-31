import javax.persistence.Entity;
import javax.persistence.Id;
public class GetEmployeeAuthorityWise {

 private  int empId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public int getEmpId(){
    return empId;
}


@Override
public String toString(){
    return "GetEmployeeAuthorityWise [empId=" + empId + ", getEmpId()=" + getEmpId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ empId).concat("/toString"));

String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


}