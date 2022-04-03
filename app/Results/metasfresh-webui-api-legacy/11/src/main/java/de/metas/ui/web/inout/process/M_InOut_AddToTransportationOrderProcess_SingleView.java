package de.metas.ui.web.inout.process;
 import org.adempiere.exceptions.AdempiereException;
import org.compiere.SpringContextHolder;
import com.google.common.collect.ImmutableList;
import de.metas.document.engine.DocStatus;
import de.metas.handlingunits.model.I_M_InOut;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IMsgBL;
import de.metas.inout.InOutId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.JavaProcess;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.shipping.model.I_M_ShipperTransportation;
import de.metas.shipping.model.ShipperTransportationId;
import de.metas.util.Services;
import lombok.NonNull;
public class M_InOut_AddToTransportationOrderProcess_SingleView extends JavaProcessimplements IProcessPrecondition{

@Param(parameterName = "M_ShipperTransportation_ID", mandatory = true)
 private  I_M_ShipperTransportation transportationOrder;

 public  AdMessageKey ALL_SELECTED_SHIPMENTS_SHOULD_BE_COMPLETED_MSG;


@Override
public String doIt(){
    final DocStatus docStatus = DocStatus.ofCode(transportationOrder.getDocStatus());
    if (docStatus.isCompleted()) {
        // this error should not be thrown since we have AD_Val_Rule for the parameter
        throw new AdempiereException("Transportation Order should not be closed");
    }
    final InOutToTransportationOrderService service = SpringContextHolder.instance.getBean(InOutToTransportationOrderService.class);
    service.addShipmentsToTransportationOrder(ShipperTransportationId.ofRepoId(transportationOrder.getM_ShipperTransportation_ID()), ImmutableList.of(InOutId.ofRepoId(this.getRecord_ID())));
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    final I_M_InOut inOut = context.getSelectedModel(I_M_InOut.class);
    final DocStatus docStatus = DocStatus.ofCode(inOut.getDocStatus());
    if (!docStatus.isCompleted()) {
        return ProcessPreconditionsResolution.reject(Services.get(IMsgBL.class).getTranslatableMsgText(ALL_SELECTED_SHIPMENTS_SHOULD_BE_COMPLETED_MSG));
    }
    return ProcessPreconditionsResolution.accept();
}


}