package de.metas.ui.web.window.model.lookup;
 import java.util.Optional;
import javax.annotation.Nullable;
import org.adempiere.util.lang.impl.TableRecordReference;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder(toBuilder = true)
public class DocumentZoomIntoInfo {

@NonNull
 private  String tableName;

 private  Integer recordId;

 private  WindowId windowId;


public TableRecordReference toTableRecordReference(){
    if (recordId == null) {
        throw new IllegalStateException("Cannot convert to " + TableRecordReference.class + " when recordId is missing: " + this);
    }
    return TableRecordReference.of(tableName, recordId);
}


public DocumentZoomIntoInfo of(String tableName,int id){
    final Integer idObj = id >= 0 ? id : null;
    return builder().tableName(tableName).recordId(idObj).build();
}


public boolean isRecordIdPresent(){
    return recordId != null;
}


public DocumentZoomIntoInfo overrideWindowIdIfPossible(Optional<WindowId> windowId){
    if (windowId == null || !windowId.isPresent()) {
        return this;
    }
    return toBuilder().windowId(windowId.get()).build();
}


}