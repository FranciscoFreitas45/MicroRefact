package de.metas.ui.web.inout.process;
 import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.SpringContextHolder;
import com.google.common.collect.ImmutableList;
import de.metas.document.engine.DocStatus;
import de.metas.handlingunits.model.I_M_InOut;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IMsgBL;
import de.metas.inout.IInOutDAO;
import de.metas.inout.InOutId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.shipping.model.I_M_ShipperTransportation;
import de.metas.shipping.model.ShipperTransportationId;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
@SuppressWarnings("NullableProblems")
public class M_InOut_AddToTransportationOrderProcess_GridView extends ViewBasedProcessTemplateimplements IProcessPrecondition{

@Param(parameterName = "M_ShipperTransportation_ID", mandatory = true)
 private  I_M_ShipperTransportation transportationOrder;

 public  AdMessageKey ALL_SELECTED_SHIPMENTS_SHOULD_BE_COMPLETED_MSG;


public ImmutableList<I_M_InOut> getSelectedInOuts(){
    final IInOutDAO inOutDAO = Services.get(IInOutDAO.class);
    return getSelectedRowIds().stream().map(rowId -> inOutDAO.getById(InOutId.ofRepoId(rowId.toInt()), I_M_InOut.class)).collect(GuavaCollectors.toImmutableList());
}


@Override
public String doIt(){
    final DocStatus docStatus = DocStatus.ofCode(transportationOrder.getDocStatus());
    if (docStatus.isCompleted()) {
        // this error should not be thrown since we have AD_Val_Rule for the parameter
        throw new AdempiereException("Transportation Order should not be closed");
    }
    final ImmutableList<InOutId> inOutIds = getSelectedInOuts().stream().map(it -> InOutId.ofRepoId(it.getM_InOut_ID())).collect(GuavaCollectors.toImmutableList());
    final InOutToTransportationOrderService service = SpringContextHolder.instance.getBean(InOutToTransportationOrderService.class);
    service.addShipmentsToTransportationOrder(ShipperTransportationId.ofRepoId(transportationOrder.getM_ShipperTransportation_ID()), inOutIds);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (getSelectedRowIds().isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    final List<I_M_InOut> inOuts = getSelectedInOuts();
    for (final I_M_InOut inOut : inOuts) {
        final DocStatus docStatus = DocStatus.ofCode(inOut.getDocStatus());
        if (!docStatus.isCompleted()) {
            return ProcessPreconditionsResolution.reject(Services.get(IMsgBL.class).getTranslatableMsgText(ALL_SELECTED_SHIPMENTS_SHOULD_BE_COMPLETED_MSG));
        }
    }
    return ProcessPreconditionsResolution.accept();
}


}