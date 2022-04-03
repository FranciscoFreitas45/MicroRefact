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


public boolean isSingleDocumentId(){
    return !all && documentIds.size() == 1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSingleDocumentId"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
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


public boolean isAll(){
    return this == ALL;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isAll"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public DocumentIdsSelection ofStringSet(Collection<String> stringDocumentIds){
    if (stringDocumentIds == null || stringDocumentIds.isEmpty()) {
        return EMPTY;
    }
    final ImmutableSet.Builder<DocumentId> documentIdsBuilder = ImmutableSet.builder();
    for (final String documentIdStr : stringDocumentIds) {
        if (ALL_String.equals(documentIdStr)) {
            return ALL;
        }
        documentIdsBuilder.add(DocumentId.of(documentIdStr));
    }
    final ImmutableSet<DocumentId> documentIds = documentIdsBuilder.build();
    if (documentIds.isEmpty()) {
        return EMPTY;
    }
    return new DocumentIdsSelection(false, documentIds);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofStringSet"))

.queryParam("stringDocumentIds",stringDocumentIds);
DocumentIdsSelection aux = restTemplate.getForObject(builder.toUriString(),DocumentIdsSelection.class);
return aux;
}


}