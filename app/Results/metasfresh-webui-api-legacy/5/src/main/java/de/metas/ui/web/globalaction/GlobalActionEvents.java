package de.metas.ui.web.globalaction;
 import javax.annotation.Nullable;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
@UtilityClass
public class GlobalActionEvents {


public GlobalActionEvent openView(ViewId viewId,ViewProfileId profileId){
    final String payload = OpenViewGlobalActionHandlerResult.of(viewId, profileId).toPayloadString();
    return GlobalActionEvent.builder().type(GlobalActionType.OPEN_VIEW).payload(payload).build();
}


}