package de.metas.ui.web.view.template;
 import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import org.adempiere.util.lang.SynchronizedMutable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
public class SynchronizedRowsIndexHolder {

 private  SynchronizedMutable<ImmutableRowsIndex<T>> holder;


public void compute(UnaryOperator<ImmutableRowsIndex<T>> remappingFunction){
    holder.compute(remappingFunction);
}


public SynchronizedRowsIndexHolder<T> of(List<T> rows){
    final ImmutableRowsIndex<T> initialRowIndex = ImmutableRowsIndex.of(rows);
    return new SynchronizedRowsIndexHolder<>(initialRowIndex);
}


public Predicate<DocumentId> isRelevantForRefreshingByDocumentId(){
    final ImmutableRowsIndex<T> rows = holder.getValue();
    return rows::isRelevantForRefreshing;
}


public ImmutableSet<ID> getRecordIdsToRefresh(DocumentIdsSelection rowIds,Function<DocumentId,ID> idMapper){
    return holder.getValue().getRecordIdsToRefresh(rowIds, idMapper);
}


public ImmutableMap<DocumentId,T> getDocumentId2TopLevelRows(){
    return holder.getValue().getDocumentId2TopLevelRows();
}


}