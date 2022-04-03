package de.metas.ui.web.process;
 import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Preconditions;
import de.metas.process.AdProcessId;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
@EqualsAndHashCode(exclude = { "json" })
public class ProcessId {

 private  String json;

 public  String PROCESSHANDLERTYPE_AD_Process;

 private  String processHandlerType;

 private  String processId;

 private  int processIdAsInt;


public ProcessId ofAD_Process_ID(AdProcessId adProcessId){
    return new ProcessId(PROCESSHANDLERTYPE_AD_Process, adProcessId.getRepoId());
}


@JsonValue
public String toJson(){
    return json;
}


public AdProcessId toAdProcessId(){
    final AdProcessId adProcessId = toAdProcessIdOrNull();
    if (adProcessId == null) {
        throw new AdempiereException("Cannot convert " + this + " to " + AdProcessId.class.getSimpleName() + " because the processHanderType=" + processHandlerType + " is not supported");
    }
    return adProcessId;
}


public DocumentId toDocumentId(){
    return DocumentId.ofString(toJson());
}


public ProcessId of(String processHandlerType,String processId){
    return new ProcessId(processHandlerType, processId);
}


public String getProcessHandlerType(){
    return processHandlerType;
}


public String getProcessId(){
    return processId;
}


public AdProcessId toAdProcessIdOrNull(){
    if (// was set by the creator of this instance
    this.processIdAsInt > 0) {
        return AdProcessId.ofRepoId(this.processIdAsInt);
    } else if (// can be derived via standard handler type
    PROCESSHANDLERTYPE_AD_Process.contentEquals(getProcessHandlerType())) {
        return AdProcessId.ofRepoId(getProcessIdAsInt());
    } else // nothing we can do here
    {
        return null;
    }
}


public ProcessId fromJson(String json){
    return new ProcessId(json);
}


@Override
public String toString(){
    return toJson();
}


public int getProcessIdAsInt(){
    int processIdAsInt = this.processIdAsInt;
    if (processIdAsInt <= 0) {
        final String processIdStr = getProcessId();
        processIdAsInt = Integer.parseInt(processIdStr);
        if (processIdAsInt <= 0) {
            throw new IllegalStateException("processId cannot be converted to int: " + processIdStr);
        }
        this.processIdAsInt = processIdAsInt;
    }
    return processIdAsInt;
}


}