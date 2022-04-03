package de.metas.ui.web.window.datatypes;
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
@Immutable
@ToString
@EqualsAndHashCode
public class DocumentIdsSelection {

 public  DocumentIdsSelection EMPTY;

 public  DocumentIdsSelection ALL;

 private  String ALL_String;

 private  ImmutableSet<String> ALL_StringSet;

 private  Splitter SPLITTER_DocumentIds;

 private  boolean all;

 private  ImmutableSet<DocumentId> documentIds;


public Set<String> toJsonSet(){
    if (all) {
        return ALL_StringSet;
    }
    return toSet(DocumentId::toJson);
}


public DocumentIdsSelection toDocumentIdsSelectionWithOnlyIntegerDocumentIds(){
    if (all) {
        return this;
    } else if (documentIds.isEmpty()) {
        return this;
    } else {
        final ImmutableSet<DocumentId> intDocumentIds = documentIds.stream().filter(documentId -> documentId != null && documentId.isInt()).collect(ImmutableSet.toImmutableSet());
        if (documentIds.size() == intDocumentIds.size()) {
            return this;
        } else {
            return new DocumentIdsSelection(false, intDocumentIds);
        }
    }
}


public void forEach(Consumer<DocumentId> action){
    assertNotAll();
    documentIds.forEach(action);
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
}


public String toCommaSeparatedString(){
    if (all) {
        return ALL_String;
    }
    return documentIds.stream().map(DocumentId::toJson).collect(Collectors.joining(","));
}


public boolean isEmpty(){
    return this == EMPTY;
}


public boolean isAll(){
    return this == ALL;
}


public ImmutableSet<ID> toIds(Function<Integer,ID> idMapper){
    return toSet(idMapper.compose(DocumentId::toInt));
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
}


public boolean isMoreThanOneDocumentId(){
    return all || documentIds.size() > 1;
}


public DocumentIdsSelection fromNullable(DocumentId documentId){
    return documentId != null ? new DocumentIdsSelection(false, ImmutableSet.of(documentId)) : EMPTY;
}


public ImmutableSet<T> toSet(Function<DocumentId,T> mapper){
    assertNotAll();
    if (documentIds.isEmpty()) {
        return ImmutableSet.of();
    }
    return documentIds.stream().map(mapper).collect(ImmutableSet.toImmutableSet());
}


public boolean contains(DocumentId documentId){
    return all || documentIds.contains(documentId);
}


public int size(){
    assertNotAll();
    return documentIds.size();
}


public boolean isSingleDocumentId(){
    return !all && documentIds.size() == 1;
}


public Stream<DocumentId> stream(){
    assertNotAll();
    return documentIds.stream();
}


public DocumentIdsSelection of(Collection<DocumentId> documentIds){
    if (documentIds == null || documentIds.isEmpty()) {
        return EMPTY;
    }
    return new DocumentIdsSelection(false, ImmutableSet.copyOf(documentIds));
}


public DocumentIdsSelection ofIntSet(Collection<Integer> intDocumentIds){
    if (intDocumentIds == null || intDocumentIds.isEmpty()) {
        return EMPTY;
    }
    final ImmutableSet<DocumentId> documentIds = intDocumentIds.stream().map(idInt -> DocumentId.of(idInt)).collect(ImmutableSet.toImmutableSet());
    return new DocumentIdsSelection(false, documentIds);
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
}


public DocumentId getSingleDocumentId(){
    if (!isSingleDocumentId()) {
        throw new IllegalStateException("Not a single documentId selection: " + this);
    }
    return documentIds.iterator().next();
}


public void assertNotAll(){
    if (all) {
        throw new IllegalStateException("method not supported for ALL selection");
    }
}


public SelectionSize toSelectionSize(){
    if (isAll()) {
        return SelectionSize.ofAll();
    }
    return SelectionSize.ofSize(size());
}


public Set<Integer> toIntSet(){
    return toSet(DocumentId::toInt);
}


}