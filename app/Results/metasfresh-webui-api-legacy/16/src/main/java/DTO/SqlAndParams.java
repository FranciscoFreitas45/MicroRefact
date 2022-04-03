package DTO;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.util.Check;
import lombok.NonNull;
import lombok.Value;
public class SqlAndParams {

 private  String sql;

 private  List<Object> sqlParams;

 private  StringBuilder sql;

 private  ArrayList<Object> sqlParams;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public int getParametersCount(){
    return sqlParams != null ? sqlParams.size() : 0;
}


public Object[] getSqlParamsArray(){
    return sqlParams == null ? null : sqlParams.toArray();
}


public SqlAndParams of(CharSequence sql,Object sqlParamsArray){
    return new SqlAndParams(sql, sqlParamsArray);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("sql",sql);
.queryParam("sqlParamsArray",sqlParamsArray);
SqlAndParams aux = restTemplate.getForObject(builder.toUriString(),SqlAndParams.class);
return aux;
}


}