package de.metas.ui.web.globalaction.process;
 import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.Adempiere;
import de.metas.printing.esb.base.util.Check;
import de.metas.process.JavaProcess;
import de.metas.process.Param;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.ui.web.globalaction.GlobalActionEvent;
import de.metas.ui.web.globalaction.GlobalActionHandlerResult;
import de.metas.ui.web.globalaction.GlobalActionsDispatcher;
import de.metas.ui.web.globalaction.OpenViewGlobalActionHandlerResult;
import de.metas.ui.web.process.adprocess.WebuiProcess;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
@WebuiProcess(layoutType = PanelLayoutType.SingleOverlayField)
public class GlobalActionReadProcess extends JavaProcess{

 private  GlobalActionsDispatcher globalActionsDispatcher;

 private  String PARAM_Barcode;

@Param(parameterName = PARAM_Barcode, mandatory = true)
 private String barcode;


public void updateProcessResult(GlobalActionHandlerResult result){
    // Tolerate null result but do nothing
    if (result == null) {
        return;
    }
    if (result instanceof OpenViewGlobalActionHandlerResult) {
        final OpenViewGlobalActionHandlerResult openViewResult = (OpenViewGlobalActionHandlerResult) result;
        final ViewId viewId = openViewResult.getViewId();
        final ViewProfileId viewProfileId = openViewResult.getViewProfileId();
        getResult().setWebuiViewToOpen(WebuiViewToOpen.builder().viewId(viewId.toJson()).profileId(viewProfileId != null ? viewProfileId.toJson() : null).target(ViewOpenTarget.ModalOverlay).build());
    } else {
        throw new AdempiereException("Unknown result: " + result);
    }
}


@Override
public String doIt(){
    if (Check.isEmpty(barcode, true)) {
        throw new FillMandatoryException(PARAM_Barcode);
    }
    final GlobalActionEvent event = GlobalActionEvent.parseQRCode(barcode);
    final GlobalActionHandlerResult result = globalActionsDispatcher.dispatchEvent(event);
    updateProcessResult(result);
    return MSG_OK;
}


}