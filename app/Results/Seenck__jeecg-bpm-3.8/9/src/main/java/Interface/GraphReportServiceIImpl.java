package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.GraphReportServiceI;
public class GraphReportServiceIImpl implements GraphReportServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Map<String,Object> queryCgReportConfig(String reportId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCgReportConfig"))
    .queryParam("reportId",reportId)
;  Map<String,Object> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Object>.class);

 return aux;
}


public List<Map<String,Object>> queryByCgReportSql(String sql,Map params,Map<String,Object> paramData,int page,int rows){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByCgReportSql"))
    .queryParam("sql",sql)
    .queryParam("params",params)
    .queryParam("paramData",paramData)
    .queryParam("page",page)
    .queryParam("rows",rows)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


public long countQueryByCgReportSql(String sql,Map params){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countQueryByCgReportSql"))
    .queryParam("sql",sql)
    .queryParam("params",params)
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


public List<String> getSqlFields(String sql){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSqlFields"))
    .queryParam("sql",sql)
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


}