package de.metas.ui.web.picking.pickingslot.PickingSlotRowsCollection;
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
@ToString
public class PickingSlotRowsIndex {

 private  ImmutableMap<PickingSlotRowId,PickingSlotRow> rowsById;

 private  ImmutableMap<PickingSlotRowId,PickingSlotRowId> rowId2rootRowId;


public long size(){
    return rowsById.size();
}


public Stream<PickingSlotRow> stream(){
    return rowsById.values().stream();
}


public PickingSlotRowId getRootRowId(PickingSlotRowId rowId){
    return rowId2rootRowId.get(rowId);
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


public Stream<Map.Entry<PickingSlotRowId,PickingSlotRowId>> streamChild2RootRowIdsRecursivelly(PickingSlotRow row){
    final PickingSlotRowId rootRowId = row.getPickingSlotRowId();
    return row.streamThisRowAndIncludedRowsRecursivelly().map(PickingSlotRow::getPickingSlotRowId).map(includedRowId -> GuavaCollectors.entry(includedRowId, rootRowId));
}


}