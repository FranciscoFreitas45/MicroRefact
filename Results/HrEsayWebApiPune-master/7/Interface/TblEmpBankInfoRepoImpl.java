import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class TblEmpBankInfoRepoImpl implements TblEmpBankInfoRepo{

 private RestTemplate restTemplate;

  String url = "http://9";


public int deleteEmpBankInfo(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteEmpBankInfo"))
    .queryParam("empId",empId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public TblEmpBankInfo findByEmpIdAndDelStatus(int empId,int del){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmpIdAndDelStatus"))
    .queryParam("empId",empId)
    .queryParam("del",del);
  TblEmpBankInfo aux = restTemplate.getForObject(builder.toUriString(), TblEmpBankInfo.class)

 return aux;
}


}