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


public boolean isAll(){
    return this == ALL;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isAll"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public int size(){
    assertNotAll();
    return documentIds.size();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/size"))

int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux;
}


public boolean isEmpty(){
    return this == EMPTY;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public Collector<DocumentId,?,DocumentIdsSelection> toDocumentIdsSelection(){
    final Supplier<Set<DocumentId>> supplier = LinkedHashSet::new;
    final BiConsumer<Set<DocumentId>, DocumentId> accumulator = Set::add;
    final BinaryOperator<Set<DocumentId>> combiner = (l, r) -> {
        l.addAll(r);
        return l;
    };
    final Function<Set<DocumentId>, DocumentIdsSelection> finisher = DocumentIdsSelection::of;
    return Collector.of(supplier, accumulator, combiner, finisher);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toDocumentIdsSelection"))

Collector<DocumentId,?,DocumentIdsSelection> aux = restTemplate.getForObject(builder.toUriString(),Collector<DocumentId,?,DocumentIdsSelection>.class);
return aux;
}


public Stream<DocumentId> stream(){
    assertNotAll();
    return documentIds.stream();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/stream"))

Stream<DocumentId> aux = restTemplate.getForObject(builder.toUriString(),Stream<DocumentId>.class);
return aux;
}


public boolean isSingleDocumentId(){
    return !all && documentIds.size() == 1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSingleDocumentId"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isMoreThanOneDocumentId(){
    return all || documentIds.size() > 1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isMoreThanOneDocumentId"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public Set<Integer> toIntSet(){
    return toSet(DocumentId::toInt);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toIntSet"))

Set<Integer> aux = restTemplate.getForObject(builder.toUriString(),Set<Integer>.class);
return aux;
}


}