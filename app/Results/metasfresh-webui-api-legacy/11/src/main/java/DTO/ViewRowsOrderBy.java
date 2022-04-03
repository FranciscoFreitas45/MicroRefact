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


public DocumentQueryOrderByList toDocumentQueryOrderByList(){
    return orderBys;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toDocumentQueryOrderByList"))

DocumentQueryOrderByList aux = restTemplate.getForObject(builder.toUriString(),DocumentQueryOrderByList.class);
return aux;
}


}