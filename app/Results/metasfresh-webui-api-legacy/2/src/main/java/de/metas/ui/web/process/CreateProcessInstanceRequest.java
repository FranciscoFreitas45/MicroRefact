package de.metas.ui.web.process;
 import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.view.ViewRowIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Immutable
public class CreateProcessInstanceRequest {

 private  ProcessId processId;

 private  DocumentPath singleDocumentPath;

 private  List<DocumentPath> selectedIncludedDocumentPaths;

 private  ViewRowIdsSelection viewRowIdsSelection;

 private  ViewRowIdsSelection parentViewRowIdsSelection;

 private  ViewRowIdsSelection childViewRowIdsSelection;


public void assertProcessIdEquals(ProcessId expectedProcessId){
    if (!Objects.equals(processId, expectedProcessId)) {
        throw new IllegalArgumentException("Request's processId is not valid. It shall be " + expectedProcessId + " but it was " + processId);
    }
}


public int getProcessIdAsInt(){
    return processId.getProcessIdAsInt();
}


}