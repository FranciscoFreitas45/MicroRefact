package de.metas.ui.web.handlingunits.process;
 import java.util.stream.Stream;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.quarantine.HULotNumberQuarantineService;
import de.metas.i18n.AdMessageKey;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
public class WEBUI_M_HU_MoveToAnotherWarehouse_ExclQuarantined extends WEBUI_M_HU_MoveToAnotherWarehouse_Helper{

 public  AdMessageKey MSG_WEBUI_HUs_IN_Quarantine;

 private  HULotNumberQuarantineService lotNumberQuarantineService;


public boolean isQuarantineHUs(Stream<I_M_HU> streamSelectedHUs){
    return streamSelectedHUs.filter(huRecord -> lotNumberQuarantineService.isQuarantineHU(huRecord)).findAny().isPresent();
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final boolean quarantineHUs = isQuarantineHUs(streamSelectedHUs(Select.ONLY_TOPLEVEL));
    if (quarantineHUs) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_HUs_IN_Quarantine));
    }
    return super.checkPreconditionsApplicable();
}


@Override
public void assertHUsEligible(){
    if (isQuarantineHUs(streamSelectedHUs(Select.ONLY_TOPLEVEL))) {
        throw new AdempiereException(msgBL.getTranslatableMsgText(MSG_WEBUI_HUs_IN_Quarantine));
    }
}


}