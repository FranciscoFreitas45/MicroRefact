package de.metas.ui.web.pporder.process;
 import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.eevolution.api.IPPOrderDAO;
import de.metas.fresh.ordercheckup.OrderCheckupBarcode;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.order.OrderLineId;
import de.metas.process.JavaProcess;
import de.metas.process.Param;
import de.metas.process.ProcessExecutionResult.RecordsToOpen.OpenTarget;
import de.metas.ui.web.pporder.PPOrderConstants;
import de.metas.ui.web.process.adprocess.WebuiProcess;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.util.Services;
@WebuiProcess(layoutType = PanelLayoutType.SingleOverlayField)
public class WEBUI_PP_Order_IssueReceipt_BarcodeLauncher extends JavaProcess{

@Param(parameterName = "Barcode", mandatory = true)
 private  String p_Barcode;


@Override
public String doIt(){
    final OrderLineId orderLineId = OrderCheckupBarcode.fromBarcodeString(p_Barcode).getOrderLineId();
    final PPOrderId ppOrderId = Services.get(IPPOrderDAO.class).retrievePPOrderIdByOrderLineId(orderLineId);
    if (ppOrderId == null) {
        throw new AdempiereException("@NotFound@ @PP_Order_ID@");
    }
    final TableRecordReference ppOrderRef = TableRecordReference.of(org.eevolution.model.I_PP_Order.Table_Name, ppOrderId);
    getResult().setRecordToOpen(ppOrderRef, PPOrderConstants.AD_WINDOW_ID_IssueReceipt.toInt(), OpenTarget.GridView);
    return MSG_OK;
}


}