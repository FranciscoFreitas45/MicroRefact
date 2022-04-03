package de.metas.ui.web.pporder;
 import java.util.List;
import java.util.stream.Stream;
import javax.annotation.concurrent.Immutable;
import org.eevolution.api.PPOrderPlanningStatus;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
@Immutable
public class PPOrderLinesViewData {

 private  ITranslatableString description;

 private  PPOrderPlanningStatus planningStatus;

 private  ImmutableList<PPOrderLineRow> records;

 private  ImmutableMap<DocumentId,PPOrderLineRow> allRecordsById;


public PPOrderPlanningStatus getPlanningStatus(){
    return planningStatus;
}


public long size(){
    return records.size();
}


public PPOrderLineRow getById(PPOrderLineRowId rowId){
    final DocumentId documentId = rowId.toDocumentId();
    final PPOrderLineRow row = allRecordsById.get(documentId);
    if (row == null) {
        throw new EntityNotFoundException("No document found for rowId=" + rowId);
    }
    return row;
}


public Stream<PPOrderLineRow> stream(){
    return records.stream();
}


public void indexByIdRecursively(ImmutableMap.Builder<DocumentId,PPOrderLineRow> collector,PPOrderLineRow row){
    collector.put(row.getRowId().toDocumentId(), row);
    row.getIncludedRows().forEach(includedRow -> indexByIdRecursively(collector, includedRow));
}


public Stream<PPOrderLineRow> streamRecursive(PPOrderLineRow row){
    return row.getIncludedRows().stream().map(includedRow -> streamRecursive(includedRow)).reduce(Stream.of(row), Stream::concat);
}


public Stream<PPOrderLineRow> streamByIds(DocumentIdsSelection documentIds){
    if (documentIds == null || documentIds.isEmpty()) {
        return Stream.empty();
    }
    if (documentIds.isAll()) {
        return records.stream();
    }
    return documentIds.stream().distinct().map(documentId -> allRecordsById.get(documentId)).filter(document -> document != null);
}


public ITranslatableString getDescription(){
    return description;
}


public ImmutableMap<DocumentId,PPOrderLineRow> buildRecordsByIdMap(List<PPOrderLineRow> rows){
    if (rows.isEmpty()) {
        return ImmutableMap.of();
    }
    final ImmutableMap.Builder<DocumentId, PPOrderLineRow> rowsById = ImmutableMap.builder();
    rows.forEach(row -> indexByIdRecursively(rowsById, row));
    return rowsById.build();
}


}