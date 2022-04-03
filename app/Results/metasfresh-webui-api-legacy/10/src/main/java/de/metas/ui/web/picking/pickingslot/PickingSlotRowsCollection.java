package de.metas.ui.web.picking.pickingslot;
 import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.GuavaCollectors;
import lombok.NonNull;
import lombok.ToString;
public class PickingSlotRowsCollection {

 private  int DEFAULT_PAGE_LENGTH;

 private  ExtendedMemorizingSupplier<PickingSlotRowsIndex> rowsSupplier;

 private  ImmutableMap<PickingSlotRowId,PickingSlotRow> rowsById;

 private  ImmutableMap<PickingSlotRowId,PickingSlotRowId> rowId2rootRowId;


public List<PickingSlotRow> getPage(int firstRow,int pageLength){
    return getRowsIndex().stream().skip(firstRow >= 0 ? firstRow : 0).limit(pageLength > 0 ? pageLength : DEFAULT_PAGE_LENGTH).collect(ImmutableList.toImmutableList());
}


public PickingSlotRowsIndex getRowsIndex(){
    return rowsSupplier.get();
}


public PickingSlotRowId getRootRowIdWhichIncludes(PickingSlotRowId rowId){
    return getRowsIndex().getRootRowId(rowId);
}


public Stream<PickingSlotRow> streamByIds(DocumentIdsSelection rowIds){
    if (rowIds.isAll()) {
        return getRowsIndex().stream();
    } else {
        return rowIds.stream().map(this::getById);
    }
}


public PickingSlotRow getRow(PickingSlotRowId rowId){
    return rowsById.get(rowId);
}


public PickingSlotRow getRootRow(PickingSlotRowId rowId){
    final PickingSlotRowId rootRowId = getRootRowId(rowId);
    if (rootRowId == null) {
        return null;
    }
    return getRow(rootRowId);
}


public PickingSlotRowsCollection ofSupplier(Supplier<List<PickingSlotRow>> rowsSupplier){
    return new PickingSlotRowsCollection(rowsSupplier);
}


public void invalidateAll(){
    rowsSupplier.forget();
}


public long size(){
    return rowsById.size();
}


public PickingSlotRow getById(PickingSlotRowId rowId){
    if (rowId.getPickingSlotId() == null) {
        return assertRowNotNull(rowId, getRowsIndex().getRow(rowId));
    }
    final PickingSlotRowId pickingSlotRowId = PickingSlotRowId.ofPickingSlotId(rowId.getPickingSlotId());
    final PickingSlotRow pickingSlotRow = getRowsIndex().getRow(pickingSlotRowId);
    if (java.util.Objects.equals(rowId, pickingSlotRowId)) {
        return assertRowNotNull(pickingSlotRowId, pickingSlotRow);
    }
    return pickingSlotRow.findIncludedRowById(rowId).orElseThrow(() -> new EntityNotFoundException("Row not found").setParameter("pickingSlotRow", pickingSlotRow).setParameter("rowId", rowId));
}


public Stream<PickingSlotRow> stream(){
    return rowsById.values().stream();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(rowsSupplier).toString();
}


public PickingSlotRowId getRootRowId(PickingSlotRowId rowId){
    return rowId2rootRowId.get(rowId);
}


public PickingSlotRow getRootRowWhichIncludes(PickingSlotRowId rowId){
    final PickingSlotRow rootRow = getRowsIndex().getRootRow(rowId);
    if (rootRow == null) {
        throw new AdempiereException("No root row found which includes " + rowId);
    }
    return rootRow;
}


public PickingSlotRow assertRowNotNull(PickingSlotRowId pickingSlotRowId,PickingSlotRow pickingSlotRow){
    if (pickingSlotRow == null) {
        throw new EntityNotFoundException("Row not found").setParameter("pickingSlotRowId", pickingSlotRowId);
    }
    return pickingSlotRow;
}


public Stream<Map.Entry<PickingSlotRowId,PickingSlotRowId>> streamChild2RootRowIdsRecursivelly(PickingSlotRow row){
    final PickingSlotRowId rootRowId = row.getPickingSlotRowId();
    return row.streamThisRowAndIncludedRowsRecursivelly().map(PickingSlotRow::getPickingSlotRowId).map(includedRowId -> GuavaCollectors.entry(includedRowId, rootRowId));
}


}