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


public ViewRowsOrderBy of(DocumentQueryOrderByList orderBys,JSONOptions jsonOpts){
    return new ViewRowsOrderBy(orderBys, jsonOpts);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("orderBys",orderBys);
.queryParam("jsonOpts",jsonOpts);
ViewRowsOrderBy aux = restTemplate.getForObject(builder.toUriString(),ViewRowsOrderBy.class);
return aux;
}


}