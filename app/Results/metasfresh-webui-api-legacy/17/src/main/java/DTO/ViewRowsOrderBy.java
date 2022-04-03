package DTO;
 import java.util.Comparator;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
public class ViewRowsOrderBy {

 private  DocumentQueryOrderByList orderBys;

 private  JSONOptions jsonOpts;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public ViewRowsOrderBy parseString(String orderBysListStr,JSONOptions jsonOpts){
    final DocumentQueryOrderByList orderBys = DocumentQueryOrderByList.parse(orderBysListStr);
    return of(orderBys, jsonOpts);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/parseString"))

.queryParam("orderBysListStr",orderBysListStr);
.queryParam("jsonOpts",jsonOpts);
ViewRowsOrderBy aux = restTemplate.getForObject(builder.toUriString(),ViewRowsOrderBy.class);
return aux;
}


}