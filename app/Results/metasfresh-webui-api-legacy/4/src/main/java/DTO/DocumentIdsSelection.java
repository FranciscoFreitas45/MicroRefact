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


public boolean isEmpty(){
    return this == EMPTY;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
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


public boolean isAll(){
    return this == ALL;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isAll"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public ImmutableSet<T> toSet(Function<DocumentId,T> mapper){
    assertNotAll();
    if (documentIds.isEmpty()) {
        return ImmutableSet.of();
    }
    return documentIds.stream().map(mapper).collect(ImmutableSet.toImmutableSet());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toSet"))

.queryParam("mapper",mapper);
ImmutableSet<T> aux = restTemplate.getForObject(builder.toUriString(),ImmutableSet<T>.class);
return aux;
}


public DocumentIdsSelection ofIntSet(Collection<Integer> intDocumentIds){
    if (intDocumentIds == null || intDocumentIds.isEmpty()) {
        return EMPTY;
    }
    final ImmutableSet<DocumentId> documentIds = intDocumentIds.stream().map(idInt -> DocumentId.of(idInt)).collect(ImmutableSet.toImmutableSet());
    return new DocumentIdsSelection(false, documentIds);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofIntSet"))

.queryParam("intDocumentIds",intDocumentIds);
DocumentIdsSelection aux = restTemplate.getForObject(builder.toUriString(),DocumentIdsSelection.class);
return aux;
}


}