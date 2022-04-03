package de.metas.ui.web.view;
 import java.util.HashMap;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class PlainDefaultViewProfileIdProvider implements DefaultViewProfileIdProvider{

 private  HashMap<WindowId,ViewProfileId> defaultProfileIdByWindowId;


public void setDefaultProfileId(WindowId windowId,ViewProfileId profileId){
    if (ViewProfileId.isNull(profileId)) {
        defaultProfileIdByWindowId.remove(windowId);
    } else {
        defaultProfileIdByWindowId.put(windowId, profileId);
    }
}


public void setDefaultProfileIdIfAbsent(WindowId windowId,ViewProfileId profileId){
    defaultProfileIdByWindowId.putIfAbsent(windowId, profileId);
}


@Override
public ViewProfileId getDefaultProfileIdByWindowId(WindowId windowId){
    return defaultProfileIdByWindowId.get(windowId);
}


}