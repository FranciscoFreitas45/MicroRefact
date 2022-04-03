package de.metas.ui.web.pickingslotsClearing.process;
 import java.util.List;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.shipmentschedule.api.HUShippingFacade;
import de.metas.handlingunits.shipmentschedule.async.GenerateInOutFromHU.BillAssociatedInvoiceCandidates;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.shipping.model.I_M_ShipperTransportation;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.util.Check;
public class WEBUI_PackingHUsView_AddHUsToShipperTransportationShipAndInvoice extends PackingHUsViewBasedProcess{

@Param(parameterName = I_M_ShipperTransportation.COLUMNNAME_M_ShipperTransportation_ID, mandatory = true)
 private  int shipperTransportationId;


@Override
public String doIt(){
    Check.assume(shipperTransportationId > 0, "shipperTransportationId > 0");
    final List<I_M_HU> hus = retrieveEligibleHUs();
    HUShippingFacade.builder().hus(hus).addToShipperTransportationId(shipperTransportationId).completeShipments(true).invoiceMode(BillAssociatedInvoiceCandidates.IF_INVOICE_SCHEDULE_PERMITS).createShipperDeliveryOrders(true).build().generateShippingDocuments();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (getSelectedRowIds().isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    final boolean eligibleHUsFound = streamEligibleHURows().findAny().isPresent();
    if (!eligibleHUsFound) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(WEBUI_HU_Constants.MSG_WEBUI_ONLY_TOP_LEVEL_HU));
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    if (!success) {
        return;
    }
    getView().invalidateAll();
}


}