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


}