package de.metas.ui.web.order.sales.purchasePlanning.view;
 import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
public class PurchaseRowsCollection {

 private  int DEFAULT_PAGE_LENGTH;

 private  ConcurrentMap<PurchaseRowId,PurchaseRow> topLevelRowsById;


public void updateRow(PurchaseRowId idOfRowToPatch,PurchaseRowChangeRequest request,PurchaseGroupRowEditor editor){
    topLevelRowsById.compute(idOfRowToPatch.toGroupRowId(), (groupRowId, groupRow) -> {
        if (groupRow == null) {
            throw new EntityNotFoundException("Row not found").appendParametersToMessage().setParameter("rowId", groupRowId);
        }
        final PurchaseRow newGroupRow = groupRow.copy();
        if (idOfRowToPatch.isGroupRowId()) {
            final PurchaseRowId includedRowId = null;
            editor.edit(newGroupRow, includedRowId, request);
        } else {
            final PurchaseRowId includedRowId = idOfRowToPatch;
            editor.edit(newGroupRow, includedRowId, request);
        }
        return newGroupRow;
    });
}


public PurchaseRowsCollection ofSupplier(PurchaseRowsSupplier rowsSupplier){
    return new PurchaseRowsCollection(rowsSupplier);
}


public PurchaseRow getToplevelRowById(PurchaseRowId topLevelRowId){
    final PurchaseRow topLevelRow = topLevelRowsById.get(topLevelRowId);
    if (topLevelRow != null) {
        return topLevelRow;
    }
    throw new EntityNotFoundException("topLevelRow not found").appendParametersToMessage().setParameter("topLevelRowId", topLevelRowId);
}


public Stream<? extends IViewRow> streamTopLevelRowsByIds(DocumentIdsSelection rowIds){
    if (rowIds.isAll()) {
        return topLevelRowsById.values().stream();
    }
    return rowIds.stream().map(this::getById);
}


public List<PurchaseRow> getAll(){
    // there are not so many, so we can afford to return all of them
    return ImmutableList.copyOf(topLevelRowsById.values());
}


public long size(){
    return topLevelRowsById.size();
}


public PurchaseRow getById(PurchaseRowId rowId){
    if (rowId.isGroupRowId()) {
        return getToplevelRowById(rowId);
    } else if (rowId.isLineRowId()) {
        return getToplevelRowById(rowId.toGroupRowId()).getIncludedRowById(rowId);
    } else {
        return getToplevelRowById(rowId.toGroupRowId()).getIncludedRowById(rowId.toLineRowId()).getIncludedRowById(rowId);
    }
}


public void edit(PurchaseRow editableGroupRow,PurchaseRowId includedRowId,PurchaseRowChangeRequest request)


public List<PurchaseRow> getPage(int firstRow,int pageLength){
    return topLevelRowsById.values().stream().skip(firstRow >= 0 ? firstRow : 0).limit(pageLength > 0 ? pageLength : DEFAULT_PAGE_LENGTH).collect(ImmutableList.toImmutableList());
}


public void patchRow(PurchaseRowId idOfRowToPatch,PurchaseRowChangeRequest request){
    final PurchaseGroupRowEditor editorToUse = PurchaseRow::changeIncludedRow;
    updateRow(idOfRowToPatch, request, editorToUse);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(topLevelRowsById).toString();
}


}