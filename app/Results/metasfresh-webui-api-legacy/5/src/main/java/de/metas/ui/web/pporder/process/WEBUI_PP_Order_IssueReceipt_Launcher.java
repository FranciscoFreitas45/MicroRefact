package de.metas.ui.web.pporder.process;
 import java.util.Objects;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.eevolution.model.I_PP_Order;
import org.eevolution.model.X_PP_Order;
import de.metas.process.IProcessPrecondition;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.JavaProcess;
import de.metas.process.ProcessExecutionResult.RecordsToOpen.OpenTarget;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pporder.PPOrderConstants;
public class WEBUI_PP_Order_IssueReceipt_Launcher extends JavaProcessimplements IProcessPrecondition{


@Override
public String doIt(){
    final TableRecordReference ppOrderRef = TableRecordReference.of(I_PP_Order.Table_Name, getRecord_ID());
    getResult().setRecordToOpen(ppOrderRef, PPOrderConstants.AD_WINDOW_ID_IssueReceipt.toInt(), OpenTarget.GridView);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    if (Objects.equals(PPOrderConstants.AD_WINDOW_ID_IssueReceipt.toAdWindowIdOrNull(), context.getAdWindowId())) {
        // we did already launch the IssueReceipt window
        return ProcessPreconditionsResolution.rejectWithInternalReason("Already within the window " + PPOrderConstants.AD_WINDOW_ID_IssueReceipt);
    }
    if (!context.isSingleSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final I_PP_Order ppOrder = context.getSelectedModel(I_PP_Order.class);
    if (!X_PP_Order.DOCSTATUS_Completed.equals(ppOrder.getDocStatus())) {
        return ProcessPreconditionsResolution.reject("not completed");
    }
    return ProcessPreconditionsResolution.accept();
}


}