package de.metas.ui.web.globalaction;
 import javax.annotation.Nullable;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.util.Check;
import lombok.NonNull;
import lombok.Value;
@Value(staticConstructor = "of")
public class OpenViewGlobalActionHandlerResult implements GlobalActionHandlerResult{

 private  String SEPARATOR;

@NonNull
 private ViewId viewId;

@Nullable
 private ViewProfileId viewProfileId;


public String toPayloadString(){
    if (viewProfileId != null) {
        return viewId.toJson() + SEPARATOR + viewProfileId.toJson();
    } else {
        return viewId.toJson();
    }
}


public OpenViewGlobalActionHandlerResult ofPayload(String payload){
    Check.assumeNotEmpty(payload, "payload is not empty");
    final int idx = payload.indexOf(SEPARATOR);
    if (idx > 0) {
        final ViewId viewId = ViewId.ofViewIdString(payload.substring(0, idx));
        final ViewProfileId viewProfileId = ViewProfileId.fromJson(payload.substring(idx + SEPARATOR.length()));
        return of(viewId, viewProfileId);
    } else {
        final ViewId viewId = ViewId.ofViewIdString(payload);
        final ViewProfileId viewProfileId = null;
        return of(viewId, viewProfileId);
    }
}


}