package de.metas.ui.web.picking;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.metas.picking.api.PickingConfig;
import de.metas.picking.api.PickingConfigRepository;
import de.metas.ui.web.view.DefaultViewProfileIdProvider;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.window.datatypes.WindowId;
@Component
public class PickingTerminalDefaultViewProfileIdProvider implements DefaultViewProfileIdProvider{

@Autowired
 private  PickingConfigRepository pickingConfigRepo;


@Override
public ViewProfileId getDefaultProfileIdByWindowId(WindowId windowId){
    if (PickingConstants.WINDOWID_PackageableView.equals(windowId)) {
        final PickingConfig pickingConfig = pickingConfigRepo.getPickingConfig();
        final String defaultProfileIdStr = pickingConfig.getWebuiPickingTerminalViewProfileId();
        return ViewProfileId.fromJson(defaultProfileIdStr);
    } else {
        return null;
    }
}


}