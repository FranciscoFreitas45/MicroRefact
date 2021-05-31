import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class CommonServiceImpl implements CommonService{

 private RestTemplate restTemplate;

  String url = "http://4";


public Double getMinValue(List<Object[]> data){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMinValue"))
    .queryParam("data",data);
  Double aux = restTemplate.getForObject(builder.toUriString(), Double.class)

 return aux;
}


public Double getMaxValue(List<Object[]> data){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMaxValue"))
    .queryParam("data",data);
  Double aux = restTemplate.getForObject(builder.toUriString(), Double.class)

 return aux;
}


public List<ValueObject> populateLegends(List<Object[]> data,String indicatorId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/populateLegends"))
    .queryParam("data",data)
    .queryParam("indicatorId",indicatorId);
  List<ValueObject> aux = restTemplate.getForObject(builder.toUriString(), List<ValueObject>.class)

 return aux;
}


public void setCssForDataModel(List<ValueObject> list,UtDataModel data,Double value,String indicatorId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCssForDataModel"))
    .queryParam("list",list)
    .queryParam("data",data)
    .queryParam("value",value)
    .queryParam("indicatorId",indicatorId);

  restTemplate.put(builder.toUriString(), null)



}