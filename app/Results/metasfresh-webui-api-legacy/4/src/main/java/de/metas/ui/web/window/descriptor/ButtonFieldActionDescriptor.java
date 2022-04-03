package de.metas.ui.web.window.descriptor;
 import de.metas.ui.web.process.ProcessId;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class ButtonFieldActionDescriptor {

@NonNull
 private  ButtonFieldActionType actionType;

 private  ProcessId processId;

 private  String zoomIntoTableIdFieldName;


public ButtonFieldActionType getActionType(){
    return actionType;
}


public ButtonFieldActionDescriptor processCall(ProcessId processId){
    // N/A
    final String zoomIntoTableIdFieldName = null;
    return new ButtonFieldActionDescriptor(ButtonFieldActionType.processCall, processId, zoomIntoTableIdFieldName);
}


public ProcessId getProcessId(){
    return processId;
}


public ButtonFieldActionDescriptor genericZoomInto(String zoomIntoTableIdFieldName){
    // N/A
    final ProcessId processId = null;
    return new ButtonFieldActionDescriptor(ButtonFieldActionType.genericZoomInto, processId, zoomIntoTableIdFieldName);
}


public String getZoomIntoTableIdFieldName(){
    return zoomIntoTableIdFieldName;
}


}