package de.metas.ui.web.pickingV2.packageable;
 import java.util.Map;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import de.metas.inoutcandidate.model.I_M_ShipmentSchedule;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class PackageableRowsData implements IRowsData<PackageableRow>{

@Getter
 private  DocumentFilterList stickyFilters;

@Getter
 private  DocumentFilterList filters;

 private  ExtendedMemorizingSupplier<PackageableRowsIndex> rowsIndexSupplier;


public PackageableRowsData cast(IRowsData<PackageableRow> rowsData){
    return (PackageableRowsData) rowsData;
}


@Override
public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs){
    return recordRefs.matchesTableName(I_M_ShipmentSchedule.Table_Name) ? DocumentIdsSelection.ALL : DocumentIdsSelection.EMPTY;
}


@Override
public void invalidateAll(){
    rowsIndexSupplier.forget();
}


public PackageableRowsIndex getPackageableRowsIndex(){
    return rowsIndexSupplier.get();
}


@Override
public Map<DocumentId,PackageableRow> getDocumentId2TopLevelRows(){
    return getPackageableRowsIndex().getRowsIndexedById();
}


}