package DTO;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import org.adempiere.ad.dao.ISqlQueryFilter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import com.google.common.base.MoreObjects;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
public class SqlParamsCollector {

 private  SqlParamsCollector NOT_COLLECTING;

 private  List<Object> params;

 private  List<Object> paramsRO;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public SqlParamsCollector newInstance(){
    return new SqlParamsCollector(new ArrayList<>());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/newInstance"))

SqlParamsCollector aux = restTemplate.getForObject(builder.toUriString(),SqlParamsCollector.class);
return aux;
}


public List<Object> toList(){
    return paramsRO;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toList"))

List<Object> aux = restTemplate.getForObject(builder.toUriString(),List<Object>.class);
return aux;
}


public String placeholder(Object sqlValue){
    if (params == null) {
        return DB.TO_SQL(sqlValue);
    } else {
        params.add(sqlValue);
        return "?";
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/placeholder"))

.queryParam("sqlValue",sqlValue);
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


public void collect(SqlParamsCollector from){
    collectAll(from.params);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/collect"))

.queryParam("from",from);
restTemplate.put(builder.toUriString(),null);
}


}