package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.JdbcDao;
public class JdbcDaoImpl implements JdbcDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<Map<String,Object>> findForJdbc(String sql,Object objs){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findForJdbc"))
    .queryParam("sql",sql)
    .queryParam("objs",objs)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


public List<Map<String,Object>> findForListMap(String sql,Map parameters,int page,int rows){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findForListMap"))
    .queryParam("sql",sql)
    .queryParam("parameters",parameters)
    .queryParam("page",page)
    .queryParam("rows",rows)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


public long findForLong(String sql,Map parameters){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findForLong"))
    .queryParam("sql",sql)
    .queryParam("parameters",parameters)
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


}