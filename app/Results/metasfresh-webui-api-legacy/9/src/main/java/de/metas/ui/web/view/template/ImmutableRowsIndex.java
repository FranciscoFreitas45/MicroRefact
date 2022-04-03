package de.metas.ui.web.view.template;
 import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.GuavaCollectors;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
public class ImmutableRowsIndex {

 private  ImmutableList<DocumentId> initialRowIds;

 private  ImmutableList<DocumentId> rowIds;

 private  ImmutableMap<DocumentId,T> rowsById;


public ImmutableRowsIndex<T> addingRow(T rowToAdd){
    final ArrayList<T> resultRows = new ArrayList<>(rowIds.size());
    boolean added = false;
    for (final DocumentId rowId : this.rowIds) {
        if (rowId.equals(rowToAdd.getId())) {
            resultRows.add(rowToAdd);
            added = true;
        } else {
            resultRows.add(rowsById.get(rowId));
        }
    }
    if (!added) {
        resultRows.add(rowToAdd);
        added = true;
    }
    return new ImmutableRowsIndex<>(this.initialRowIds, resultRows);
}


public ImmutableRowsIndex<T> of(List<T> rows){
    final ImmutableList<DocumentId> initialRowIds = rows.stream().map(IViewRow::getId).collect(ImmutableList.toImmutableList());
    return new ImmutableRowsIndex<>(initialRowIds, rows);
}


public Stream<T> streamAllRows(){
    return rowIds.stream().map(rowsById::get);
}


public boolean isRelevantForRefreshing(DocumentId rowId){
    return rowIds.contains(rowId) || initialRowIds.contains(rowId);
}


public ImmutableRowsIndex<T> replacingRows(DocumentIdsSelection oldRowIds,List<T> newRows){
    final LinkedHashMap<DocumentId, T> newRowsToAdd = newRows.stream().collect(GuavaCollectors.toMapByKey(LinkedHashMap::new, IViewRow::getId));
    final ArrayList<T> resultRows = new ArrayList<>(rowIds.size());
    for (final DocumentId rowId : this.rowIds) {
        if (oldRowIds.contains(rowId)) {
            final T newRowToAdd = newRowsToAdd.remove(rowId);
            if (newRowToAdd != null) {
                resultRows.add(newRowToAdd);
            }
        } else {
            resultRows.add(rowsById.get(rowId));
        }
    }
    resultRows.addAll(newRowsToAdd.values());
    return new ImmutableRowsIndex<>(this.initialRowIds, resultRows);
}


public ImmutableSet<ID> getRecordIdsToRefresh(DocumentIdsSelection rowIds,Function<DocumentId,ID> idMapper){
    if (rowIds.isEmpty()) {
        return ImmutableSet.of();
    } else if (rowIds.isAll()) {
        return Stream.concat(this.rowIds.stream(), this.initialRowIds.stream()).map(idMapper).collect(ImmutableSet.toImmutableSet());
    } else {
        return rowIds.stream().filter(this::isRelevantForRefreshing).map(idMapper).collect(ImmutableSet.toImmutableSet());
    }
}


public ImmutableMap<DocumentId,T> getDocumentId2TopLevelRows(){
    return streamAllRows().collect(GuavaCollectors.toImmutableMapByKey(IViewRow::getId));
}


}