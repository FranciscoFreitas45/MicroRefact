package de.metas.ui.web.picking.packageable;
 import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Maps;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.inoutcandidate.model.I_M_ShipmentSchedule;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class PackageableRowsData implements IRowsData<PackageableRow>{

 public  PackageableRowsData EMPTY;

 private  ExtendedMemorizingSupplier<Map<DocumentId,PackageableRow>> topLevelRows;

 private  ImmutableListMultimap<TableRecordReference,DocumentId> initialDocumentIdsByRecordRef;


public PackageableRowsData ofSupplier(Supplier<List<PackageableRow>> rowsSupplier){
    return new PackageableRowsData(rowsSupplier);
}


public PackageableRowsData cast(IRowsData<PackageableRow> rowsData){
    return (PackageableRowsData) rowsData;
}


public Stream<DocumentId> streamDocumentIdsForShipmentScheduleId(ShipmentScheduleId shipmentScheduleId){
    final TableRecordReference recordRefEffective = PackageableRow.createTableRecordReferenceFromShipmentScheduleId(shipmentScheduleId);
    return initialDocumentIdsByRecordRef.get(recordRefEffective).stream();
}


@Override
public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs){
    return recordRefs.streamIds(I_M_ShipmentSchedule.Table_Name, ShipmentScheduleId::ofRepoId).flatMap(this::streamDocumentIdsForShipmentScheduleId).collect(DocumentIdsSelection.toDocumentIdsSelection());
}


@Override
public void invalidateAll(){
    topLevelRows.forget();
}


@Override
public Map<DocumentId,PackageableRow> getDocumentId2TopLevelRows(){
    return topLevelRows.get();
}


}