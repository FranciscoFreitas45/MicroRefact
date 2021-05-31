import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PayDeductionDetailsRepoImpl implements PayDeductionDetailsRepo{

 private RestTemplate restTemplate;

  String url = "http://20";


public List<PayDeductionDetails> getEmpPayDedAllEmp(String fromYear,String fromMonth,String toYear,String toMonth){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpPayDedAllEmp"))
    .queryParam("fromYear",fromYear)
    .queryParam("fromMonth",fromMonth)
    .queryParam("toYear",toYear)
    .queryParam("toMonth",toMonth);
  List<PayDeductionDetails> aux = restTemplate.getForObject(builder.toUriString(), List<PayDeductionDetails>.class)

 return aux;
}


public List<PayDeductionDetails> getEmpPayDed(String fromYear,String fromMonth,String toYear,String toMonth,int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpPayDed"))
    .queryParam("fromYear",fromYear)
    .queryParam("fromMonth",fromMonth)
    .queryParam("toYear",toYear)
    .queryParam("toMonth",toMonth)
    .queryParam("empId",empId);
  List<PayDeductionDetails> aux = restTemplate.getForObject(builder.toUriString(), List<PayDeductionDetails>.class)

 return aux;
}


}