package DTO;
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
public class PackageableRowsData implements IRowsData<PackageableRow>{

 public  PackageableRowsData EMPTY;

 private  ExtendedMemorizingSupplier<Map<DocumentId,PackageableRow>> topLevelRows;

 private  ImmutableListMultimap<TableRecordReference,DocumentId> initialDocumentIdsByRecordRef;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


@Override
public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs){
    return recordRefs.streamIds(I_M_ShipmentSchedule.Table_Name, ShipmentScheduleId::ofRepoId).flatMap(this::streamDocumentIdsForShipmentScheduleId).collect(DocumentIdsSelection.toDocumentIdsSelection());
}


@Override
public Map<DocumentId,PackageableRow> getDocumentId2TopLevelRows(){
    return topLevelRows.get();
}


public PackageableRowsData cast(IRowsData<PackageableRow> rowsData){
    return (PackageableRowsData) rowsData;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cast"))

.queryParam("rowsData",rowsData);
PackageableRowsData aux = restTemplate.getForObject(builder.toUriString(),PackageableRowsData.class);
return aux;
}


}