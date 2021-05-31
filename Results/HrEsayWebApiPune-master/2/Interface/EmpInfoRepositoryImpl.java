import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class EmpInfoRepositoryImpl implements EmpInfoRepository{

 private RestTemplate restTemplate;

  String url = "http://17";


public List<EmpInfo> getEmpListLocId(String fromDate,String toDate,List<Integer> locId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpListLocId"))
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate)
    .queryParam("locId",locId);
  List<EmpInfo> aux = restTemplate.getForObject(builder.toUriString(), List<EmpInfo>.class)

 return aux;
}


public List<EmpInfo> getEmpListAlllocId(String fromDate,List<Integer> locId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpListAlllocId"))
    .queryParam("fromDate",fromDate)
    .queryParam("locId",locId);
  List<EmpInfo> aux = restTemplate.getForObject(builder.toUriString(), List<EmpInfo>.class)

 return aux;
}


public List<EmpInfo> getEmpListForHod(String fromDate,int userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpListForHod"))
    .queryParam("fromDate",fromDate)
    .queryParam("userId",userId);
  List<EmpInfo> aux = restTemplate.getForObject(builder.toUriString(), List<EmpInfo>.class)

 return aux;
}


public List<EmpInfo> getEmpListAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpListAll"))
  List<EmpInfo> aux = restTemplate.getForObject(builder.toUriString(), List<EmpInfo>.class)

 return aux;
}


}