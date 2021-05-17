import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class UtDataRepositoryImpl implements UtDataRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public void save(Iterable<UtData> utDatas){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("save"))
    .queryParam("utDatas",utDatas);

  restTemplate.put(builder.toUriString(), null)



public List<UtData> getData(int subSector,int timePeriod,int indicator,int unit,int subgroup){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("getData"))
    .queryParam("subSector",subSector)
    .queryParam("timePeriod",timePeriod)
    .queryParam("indicator",indicator)
    .queryParam("unit",unit)
    .queryParam("subgroup",subgroup);
  List<UtData> aux = restTemplate.getForObject(builder.toUriString(), List<UtData>.class)

 return aux;
}


public List<Object[]> getByTimePeriod(int timeperiodId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("getByTimePeriod"))
    .queryParam("timeperiodId",timeperiodId);
  List<Object[]> aux = restTemplate.getForObject(builder.toUriString(), List<Object[]>.class)

 return aux;
}


public List<Object[]> findDataByTimePeriodAndArea(Integer timeperiodId,List<Integer> areaList,Integer[] iusIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findDataByTimePeriodAndArea"))
    .queryParam("timeperiodId",timeperiodId)
    .queryParam("areaList",areaList)
    .queryParam("iusIds",iusIds);
  List<Object[]> aux = restTemplate.getForObject(builder.toUriString(), List<Object[]>.class)

 return aux;
}


public List<Object[]> getABRCCData(List<String> areaNames){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("getABRCCData"))
    .queryParam("areaNames",areaNames);
  List<Object[]> aux = restTemplate.getForObject(builder.toUriString(), List<Object[]>.class)

 return aux;
}


}