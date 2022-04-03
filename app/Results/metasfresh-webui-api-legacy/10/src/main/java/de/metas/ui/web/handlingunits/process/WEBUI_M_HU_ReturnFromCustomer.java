package de.metas.ui.web.handlingunits.process;
 import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.inout.IHUInOutBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.util.Services;
public class WEBUI_M_HU_ReturnFromCustomer extends HUEditorProcessTemplateimplements IProcessPrecondition{

 private  List<I_M_HU> husToReturn;


@Override
public String doIt(){
    husToReturn = streamSelectedHUs(Select.ONLY_TOPLEVEL).collect(ImmutableList.toImmutableList());
    if (husToReturn.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    }
    Services.get(IHUInOutBL.class).createCustomerReturnInOutForHUs(husToReturn);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!isHUEditorView()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not the HU view");
    }
    if (!streamSelectedHUIds(Select.ONLY_TOPLEVEL).findAny().isPresent()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(WEBUI_HU_Constants.MSG_WEBUI_ONLY_TOP_LEVEL_HU));
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    if (husToReturn != null && !husToReturn.isEmpty()) {
        getView().removeHUsAndInvalidate(husToReturn);
    }
}


}