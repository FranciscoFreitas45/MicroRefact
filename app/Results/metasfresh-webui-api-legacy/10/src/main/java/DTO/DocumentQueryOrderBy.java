package DTO;
 import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import de.metas.ui.web.window.datatypes.json.JSONNullValue;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;
public class DocumentQueryOrderBy {

 private  String fieldName;

 private  boolean ascending;

 private  boolean nullsLast;

 public  ValueComparator ASCENDING_NULLS_FIRST;

 public  ValueComparator ASCENDING_NULLS_LAST;

 public  ValueComparator DESCENDING_NULLS_FIRST;

 public  ValueComparator DESCENDING_NULLS_LAST;

 private  boolean ascending;

 private  boolean nullsLast;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public boolean getDefaultNullsLastByAscending(boolean ascending){
    // always nulls last
    return true;
}


public Object getFieldValue(T object,String fieldName,JSONOptions jsonOpts)


public DocumentQueryOrderBy byFieldName(String fieldName,boolean ascending){
    final boolean nullsLast = getDefaultNullsLastByAscending(ascending);
    return new DocumentQueryOrderBy(fieldName, ascending, nullsLast);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/byFieldName"))

.queryParam("fieldName",fieldName);
.queryParam("ascending",ascending);
DocumentQueryOrderBy aux = restTemplate.getForObject(builder.toUriString(),DocumentQueryOrderBy.class);
return aux;
}


}