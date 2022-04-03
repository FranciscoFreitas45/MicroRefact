package de.metas.ui.web.window.invalidation;
 import java.util.HashSet;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class IncludedDocumentToInvalidate {

@Getter
 private  String tableName;

 private  boolean invalidateAll;

 private  HashSet<Integer> recordIds;


public void invalidateAll(){
    if (invalidateAll) {
        return;
    } else {
        invalidateAll = true;
        recordIds.clear();
    }
}


public void addRecordId(int recordId){
    if (!invalidateAll) {
        recordIds.add(recordId);
    }
}


public DocumentIdsSelection toDocumentIdsSelection(){
    return invalidateAll ? DocumentIdsSelection.ALL : DocumentIdsSelection.ofIntSet(recordIds);
}


}