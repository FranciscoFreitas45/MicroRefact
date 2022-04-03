package de.metas.ui.web.window.model;
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
@EqualsAndHashCode
@ToString
public class DocumentQueryOrderByList {

 public  DocumentQueryOrderByList EMPTY;

 private  ImmutableList<DocumentQueryOrderBy> list;


public Comparator<T> toComparator(FieldValueExtractor<T> fieldValueExtractor,JSONOptions jsonOpts){
    // used in case orderBys is empty or whatever else goes wrong
    final Comparator<T> noopComparator = (o1, o2) -> 0;
    return stream().map(orderBy -> orderBy.<T>asComparator(fieldValueExtractor, jsonOpts)).reduce(Comparator::thenComparing).orElse(noopComparator);
}


public Stream<DocumentQueryOrderBy> stream(){
    return list.stream();
}


public void forEach(Consumer<DocumentQueryOrderBy> consumer){
    list.forEach(consumer);
}


public boolean equals(DocumentQueryOrderByList list1,DocumentQueryOrderByList list2){
    return Objects.equals(list1, list2);
}


public boolean isEmpty(){
    return list.isEmpty();
}


public Collector<DocumentQueryOrderBy,?,DocumentQueryOrderByList> toDocumentQueryOrderByList(){
    return GuavaCollectors.collectUsingListAccumulator(DocumentQueryOrderByList::ofList);
}


public DocumentQueryOrderByList parse(String orderBysListStr){
    if (Check.isEmpty(orderBysListStr, true)) {
        return EMPTY;
    }
    return Splitter.on(',').trimResults().omitEmptyStrings().splitToList(orderBysListStr).stream().map(DocumentQueryOrderBy::parse).collect(toDocumentQueryOrderByList());
}


public ImmutableList<DocumentQueryOrderBy> toList(){
    return list;
}


public DocumentQueryOrderByList ofList(List<DocumentQueryOrderBy> list){
    return list != null && !list.isEmpty() ? new DocumentQueryOrderByList(list) : EMPTY;
}


}