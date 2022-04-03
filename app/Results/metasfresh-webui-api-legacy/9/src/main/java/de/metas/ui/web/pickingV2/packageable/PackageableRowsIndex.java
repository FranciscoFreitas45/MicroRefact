package de.metas.ui.web.pickingV2.packageable;
 import java.util.Collection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.GuavaCollectors;
import lombok.NonNull;
public class PackageableRowsIndex {

 private  ImmutableMap<DocumentId,PackageableRow> rowsById;

 private  ImmutableListMultimap<ShipmentScheduleId,PackageableRow> rowsByShipmentScheduleId;


public ImmutableMap<DocumentId,PackageableRow> getRowsIndexedById(){
    return rowsById;
}


public ImmutableList<PackageableRow> getRowsByShipmentScheduleId(ShipmentScheduleId shipmentScheduleId){
    return rowsByShipmentScheduleId.get(shipmentScheduleId);
}


public PackageableRowsIndex of(Collection<PackageableRow> rows){
    return new PackageableRowsIndex(rows);
}


public ImmutableSet<DocumentId> getRowIdsByShipmentScheduleId(ShipmentScheduleId shipmentScheduleId){
    return getRowsByShipmentScheduleId(shipmentScheduleId).stream().map(PackageableRow::getId).collect(ImmutableSet.toImmutableSet());
}


}