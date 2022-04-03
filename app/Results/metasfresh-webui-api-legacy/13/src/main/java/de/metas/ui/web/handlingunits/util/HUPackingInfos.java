package de.metas.ui.web.handlingunits.util;
 import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_LUTU_Configuration;
import de.metas.handlingunits.model.X_M_HU_PI_Version;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.util.Check;
import de.metas.util.Services;
public class HUPackingInfos {


public IHUPackingInfo of(IHUProductStorage huProductStorage){
    return new VHUPackingInfo(huProductStorage);
}


}