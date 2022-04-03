package DTO;
 import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.window.datatypes.json.JSONNullValue;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
public class DocumentId implements Serializable{

 private  int NEW_ID;

 public  String NEW_ID_STRING;

 public  DocumentId NEW;

 private  String DOCUMENT_ID_PREFIX;

 private  String COMPOSED_KEY_SEPARATOR;

 private  Splitter COMPOSED_KEY_SPLITTER;

 private  int idInt;

 private  String idStr;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public DocumentId of(RepoIdAware id){
    return of(id.getRepoId());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("id",id);
DocumentId aux = restTemplate.getForObject(builder.toUriString(),DocumentId.class);
return aux;
}


}