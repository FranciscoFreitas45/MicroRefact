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


public void collectAll(Collection<? extends Object> sqlParams){
    if (sqlParams == null || sqlParams.isEmpty()) {
        return;
    }
    if (params == null) {
        throw new IllegalStateException("Cannot append " + sqlParams + " to not collecting params");
    }
    params.addAll(sqlParams);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/collectAll"))

.queryParam("sqlParams",sqlParams);
restTemplate.put(builder.toUriString(),null);
}


}