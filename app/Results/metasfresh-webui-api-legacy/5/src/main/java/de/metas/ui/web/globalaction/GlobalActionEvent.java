package de.metas.ui.web.globalaction;
 import com.google.common.base.Strings;
import de.metas.process.ProcessExecutionResult;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class GlobalActionEvent {

@NonNull
 private GlobalActionType type;

 private String payload;

 private  String SEPARATOR;


public GlobalActionEvent parseQRCode(String eventStr){
    final int idx = eventStr.indexOf(SEPARATOR);
    if (idx > 0) {
        final String typeStr = eventStr.substring(0, idx);
        final GlobalActionType type = GlobalActionType.forCode(typeStr);
        final String payload = Strings.emptyToNull(eventStr.substring(idx + SEPARATOR.length()));
        return builder().type(type).payload(payload).build();
    } else {
        final GlobalActionType type = GlobalActionType.forCode(eventStr);
        return builder().type(type).build();
    }
}


public String toQRCodeString(){
    if (payload == null || payload.isEmpty()) {
        return type.getCode();
    } else {
        return type.getCode() + SEPARATOR + payload;
    }
}


@Override
@Deprecated
public String toString(){
    return toQRCodeString();
}


public ProcessExecutionResult.DisplayQRCode toDisplayQRCodeProcessResult(){
    return ProcessExecutionResult.DisplayQRCode.builder().code(toQRCodeString()).build();
}


}