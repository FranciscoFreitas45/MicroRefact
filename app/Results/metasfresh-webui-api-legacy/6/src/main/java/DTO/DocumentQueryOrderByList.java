package DTO;
 import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.model.DocumentQueryOrderBy.FieldValueExtractor;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
public class DocumentQueryOrderByList {

 public  DocumentQueryOrderByList EMPTY;

 private  ImmutableList<DocumentQueryOrderBy> list;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public ImmutableList<DocumentQueryOrderBy> toList(){
    return list;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toList"))

ImmutableList<DocumentQueryOrderBy> aux = restTemplate.getForObject(builder.toUriString(),ImmutableList<DocumentQueryOrderBy>.class);
return aux;
}


public DocumentQueryOrderByList ofList(List<DocumentQueryOrderBy> list){
    return list != null && !list.isEmpty() ? new DocumentQueryOrderByList(list) : EMPTY;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofList"))

.queryParam("list",list);
DocumentQueryOrderByList aux = restTemplate.getForObject(builder.toUriString(),DocumentQueryOrderByList.class);
return aux;
}


public boolean equals(DocumentQueryOrderByList list1,DocumentQueryOrderByList list2){
    return Objects.equals(list1, list2);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))

.queryParam("list1",list1);
.queryParam("list2",list2);
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public Collector<DocumentQueryOrderBy,?,DocumentQueryOrderByList> toDocumentQueryOrderByList(){
    return GuavaCollectors.collectUsingListAccumulator(DocumentQueryOrderByList::ofList);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toDocumentQueryOrderByList"))

Collector<DocumentQueryOrderBy,?,DocumentQueryOrderByList> aux = restTemplate.getForObject(builder.toUriString(),Collector<DocumentQueryOrderBy,?,DocumentQueryOrderByList>.class);
return aux;
}


public boolean isEmpty(){
    return list.isEmpty();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public Stream<DocumentQueryOrderBy> stream(){
    return list.stream();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/stream"))

Stream<DocumentQueryOrderBy> aux = restTemplate.getForObject(builder.toUriString(),Stream<DocumentQueryOrderBy>.class);
return aux;
}


}