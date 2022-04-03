package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CgDynamGraphServiceI;
public class CgDynamGraphServiceIImpl implements CgDynamGraphServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://15";


public Map<String,Object> queryCgDynamGraphConfig(String reportId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCgDynamGraphConfig"))
    .queryParam("reportId",reportId)
;  Map<String,Object> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Object>.class);

 return aux;
}


public List<Map<String,Object>> queryByCgDynamGraphSql(String sql,Map params,Map<String,Object> paramData){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByCgDynamGraphSql"))
    .queryParam("sql",sql)
    .queryParam("params",params)
    .queryParam("paramData",paramData)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


public long countQueryByCgDynamGraphSql(String sql,Map params,Map<String,Object> paramData){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countQueryByCgDynamGraphSql"))
    .queryParam("sql",sql)
    .queryParam("params",params)
    .queryParam("paramData",paramData)
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


}