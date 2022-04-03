package de.metas.ui.web.process;
 import java.io.File;
import org.compiere.util.Util;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.Value;
@Value
public class ProcessInstanceResult {

 private  DocumentId instanceId;

 private  String summary;

 private  boolean error;

 private  ResultAction action;

@NonNull
 private  String filename;

@NonNull
 private  String contentType;

@NonNull
@Setter(AccessLevel.NONE)
@Getter(AccessLevel.NONE)
 private  File tempFile;

@NonNull
 private  ViewId viewId;

 private  ViewProfileId profileId;

@Builder.Default
 private  boolean modalOverlay;

@NonNull
 private  ViewId viewId;

 private  ViewProfileId profileId;

@NonNull
 private  CreateViewRequest createViewRequest;

@NonNull
 private  DocumentPath documentPath;

 private  boolean modal;

@NonNull
 private  ViewId viewId;

@NonNull
 private  DocumentIdsSelection rowIds;

@NonNull
 private  String code;


public byte[] getReportData(){
    return Util.readBytes(tempFile);
}


public ProcessInstanceResultBuilder builder(DocumentId instanceId){
    return new ProcessInstanceResultBuilder().instanceId(instanceId);
}


public T getAction(Class<T> actionType){
    final ResultAction action = getAction();
    if (action == null) {
        throw new IllegalStateException("No action defined");
    }
    if (!actionType.isAssignableFrom(action.getClass())) {
        throw new IllegalStateException("Action is not of type " + actionType + " but " + action.getClass());
    }
    @SuppressWarnings("unchecked")
    final T actionCasted = (T) action;
    return actionCasted;
}


public ProcessInstanceResult ok(DocumentId instanceId){
    return builder(instanceId).build();
}


public ProcessInstanceResult error(DocumentId instanceId,Throwable error){
    return builder(instanceId).error(true).summary(error.getLocalizedMessage()).build();
}


public boolean isSuccess(){
    return !isError();
}


}