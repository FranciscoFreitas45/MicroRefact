import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetPayDedListRepoImpl implements GetPayDedListRepo{

 private RestTemplate restTemplate;

  String url = "http://22";


public List<GetPayDedList> getPayDedList(int month,int year,List<Integer> empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPayDedList"))
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("empIds",empIds);
  List<GetPayDedList> aux = restTemplate.getForObject(builder.toUriString(), List<GetPayDedList>.class)

 return aux;
}


public List<GetPayDedList> getBonusList(String date,List<Integer> empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBonusList"))
    .queryParam("date",date)
    .queryParam("empIds",empIds);
  List<GetPayDedList> aux = restTemplate.getForObject(builder.toUriString(), List<GetPayDedList>.class)

 return aux;
}


public List<GetPayDedList> getLoanList(String date,List<Integer> empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLoanList"))
    .queryParam("date",date)
    .queryParam("empIds",empIds);
  List<GetPayDedList> aux = restTemplate.getForObject(builder.toUriString(), List<GetPayDedList>.class)

 return aux;
}


public List<GetPayDedList> getPartialLoanList(int month,int year,List<Integer> empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPartialLoanList"))
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("empIds",empIds);
  List<GetPayDedList> aux = restTemplate.getForObject(builder.toUriString(), List<GetPayDedList>.class)

 return aux;
}


public List<GetPayDedList> getEncashLeaveAmtList(int month,int year,List<Integer> empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEncashLeaveAmtList"))
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("empIds",empIds);
  List<GetPayDedList> aux = restTemplate.getForObject(builder.toUriString(), List<GetPayDedList>.class)

 return aux;
}


}