package DTO;
 import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.concurrent.Immutable;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import de.metas.process.SelectionSize;
import de.metas.util.lang.RepoIdAware;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
public class DocumentIdsSelection {

 public  DocumentIdsSelection EMPTY;

 public  DocumentIdsSelection ALL;

 private  String ALL_String;

 private  ImmutableSet<String> ALL_StringSet;

 private  Splitter SPLITTER_DocumentIds;

 private  boolean all;

 private  ImmutableSet<DocumentId> documentIds;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public DocumentId getSingleDocumentId(){
    if (!isSingleDocumentId()) {
        throw new IllegalStateException("Not a single documentId selection: " + this);
    }
    return documentIds.iterator().next();
}


public DocumentIdsSelection ofCommaSeparatedString(String string){
    if (string == null || string.trim().isEmpty()) {
        return DocumentIdsSelection.EMPTY;
    }
    if (ALL_String.equalsIgnoreCase(string)) {
        return ALL;
    }
    final List<String> stringDocumentIds = SPLITTER_DocumentIds.splitToList(string);
    return ofStringSet(stringDocumentIds);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofCommaSeparatedString"))

.queryParam("string",string);
DocumentIdsSelection aux = restTemplate.getForObject(builder.toUriString(),DocumentIdsSelection.class);
return aux;
}


public Set<Integer> toIntSet(){
    return toSet(DocumentId::toInt);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toIntSet"))

Set<Integer> aux = restTemplate.getForObject(builder.toUriString(),Set<Integer>.class);
return aux;
}


public void forEach(Consumer<DocumentId> action){
    assertNotAll();
    documentIds.forEach(action);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/forEach"))

.queryParam("action",action);
restTemplate.put(builder.toUriString(),null);
}


public DocumentIdsSelection of(Collection<DocumentId> documentIds){
    if (documentIds == null || documentIds.isEmpty()) {
        return EMPTY;
    }
    return new DocumentIdsSelection(false, ImmutableSet.copyOf(documentIds));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("documentIds",documentIds);
DocumentIdsSelection aux = restTemplate.getForObject(builder.toUriString(),DocumentIdsSelection.class);
return aux;
}


}