package de.metas.ui.web.handlingunits.process;
 import org.adempiere.model.InterfaceWrapperHelper.loadOutOfTrx;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;
import org.springframework.context.annotation.Profile;
import com.google.common.collect.ImmutableList;
import de.metas.Profiles;
import de.metas.handlingunits.IHUContextFactory;
import de.metas.handlingunits.IMutableHUContext;
import de.metas.handlingunits.allocation.IAllocationRequest;
import de.metas.handlingunits.allocation.IAllocationSource;
import de.metas.handlingunits.allocation.impl.AllocationUtils;
import de.metas.handlingunits.allocation.impl.HULoader;
import de.metas.handlingunits.allocation.impl.HUProducerDestination;
import de.metas.handlingunits.exceptions.HUException;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.handlingunits.receiptschedule.IHUReceiptScheduleBL;
import de.metas.inoutcandidate.api.IReceiptScheduleBL;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.quantity.Quantity;
import de.metas.quantity.StockQtyAndUOMQty;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.NonNull;
@Profile(Profiles.PROFILE_Webui)
public class WEBUI_M_ReceiptSchedule_ReceiveCUs extends ReceiptScheduleBasedProcess{

 private  IHUReceiptScheduleBL huReceiptScheduleBL;

 private  IReceiptScheduleBL receiptScheduleBL;

 private  boolean allowMultipleReceiptsSchedules;

 private  boolean allowNoQuantityAvailable;


public Stream<I_M_ReceiptSchedule> streamReceiptSchedulesToReceive(){
    return retrieveSelectedRecordsQueryBuilder(I_M_ReceiptSchedule.class).create().stream(I_M_ReceiptSchedule.class);
}


public void setAllowNoQuantityAvailable(boolean allowNoQuantityAvailable){
    this.allowNoQuantityAvailable = allowNoQuantityAvailable;
}


public BigDecimal getEffectiveQtyToReceive(I_M_ReceiptSchedule rs){
    BigDecimal defaultAvailableQtyToReceive = getDefaultAvailableQtyToReceive(rs);
    return defaultAvailableQtyToReceive;
}


public void setAllowMultipleReceiptsSchedules(boolean allowMultipleReceiptsSchedules){
    this.allowMultipleReceiptsSchedules = allowMultipleReceiptsSchedules;
}


@Override
@RunOutOfTrx
public String doIt(){
    final List<I_M_HU> hus = streamReceiptSchedulesToReceive().map(this::createPlanningVHU).filter(hu -> hu != null).collect(GuavaCollectors.toImmutableList());
    openHUsToReceive(hus);
    return MSG_OK;
}


public I_M_HU createPlanningVHU(I_M_ReceiptSchedule receiptSchedule){
    // 
    // Create allocation request for the quantity user entered
    final IAllocationRequest allocationRequest = createAllocationRequest(receiptSchedule);
    if (allocationRequest == null || allocationRequest.isZeroQty()) {
        return null;
    }
    // task 09717
    // make sure the attributes are initialized in case of multiple row selection, also
    huReceiptScheduleBL.setInitialAttributeValueDefaults(allocationRequest, ImmutableList.of(receiptSchedule));
    // 
    // Allocation Source: our receipt schedule
    final IAllocationSource allocationSource = huReceiptScheduleBL.createAllocationSource(receiptSchedule);
    // 
    // Allocation Destination: HU producer which will create 1 VHU
    final HUProducerDestination huProducer = HUProducerDestination.ofVirtualPI();
    // 
    // Transfer Qty
    HULoader.of(allocationSource, huProducer).setAllowPartialUnloads(false).setAllowPartialLoads(false).load(allocationRequest);
    // 
    // Get created VHU and return it
    final List<I_M_HU> hus = huProducer.getCreatedHUs();
    if (hus == null || hus.size() != 1) {
        throw new HUException("One and only one VHU was expected but we got: " + hus);
    }
    final I_M_HU vhu = hus.get(0);
    InterfaceWrapperHelper.setTrxName(vhu, ITrx.TRXNAME_None);
    return vhu;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    if (context.isNoSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    // 
    // Check if we are allowed to select multiple lines
    if (!allowMultipleReceiptsSchedules && context.isMoreThanOneSelected()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select only one line");
    }
    // 
    // Fetch the receipt schedules which have some qty available for receiving
    final List<I_M_ReceiptSchedule> receiptSchedules = context.getSelectedModels(I_M_ReceiptSchedule.class).stream().filter(receiptSchedule -> allowNoQuantityAvailable || getDefaultAvailableQtyToReceive(receiptSchedule).signum() > 0).collect(ImmutableList.toImmutableList());
    if (receiptSchedules.isEmpty()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("nothing to receive");
    }
    // 
    // Make sure each of them are eligible for receiving
    {
        final ProcessPreconditionsResolution rejectResolution = receiptSchedules.stream().map(receiptSchedule -> WEBUI_M_ReceiptSchedule_ReceiveHUs_Base.checkEligibleForReceivingHUs(receiptSchedule)).filter(resolution -> !resolution.isAccepted()).findFirst().orElse(null);
        if (rejectResolution != null) {
            return rejectResolution;
        }
    }
    // 
    // If more than one line selected, make sure the lines make sense together
    // * enforce same BPartner (task https://github.com/metasfresh/metasfresh-webui/issues/207)
    if (receiptSchedules.size() > 1) {
        final long bpartnersCount = receiptSchedules.stream().map(receiptSchedule -> receiptScheduleBL.getC_BPartner_Effective_ID(receiptSchedule)).distinct().count();
        if (bpartnersCount != 1) {
            return ProcessPreconditionsResolution.rejectWithInternalReason("select only one BPartner");
        }
    }
    // 
    return ProcessPreconditionsResolution.accept();
}


public IAllocationRequest createAllocationRequest(I_M_ReceiptSchedule rs){
    // Get Qty
    final BigDecimal qty = getEffectiveQtyToReceive(rs);
    if (qty.signum() <= 0) {
        // nothing to do
        return null;
    }
    final IMutableHUContext huContextInitial = Services.get(IHUContextFactory.class).createMutableHUContextForProcessing(getCtx());
    final IAllocationRequest allocationRequest = AllocationUtils.createAllocationRequestBuilder().setHUContext(huContextInitial).setDateAsToday().setProduct(loadOutOfTrx(rs.getM_Product_ID(), I_M_Product.class)).setQuantity(new Quantity(qty, loadOutOfTrx(rs.getC_UOM_ID(), I_C_UOM.class))).setFromReferencedModel(rs).setForceQtyAllocation(true).create();
    return allocationRequest;
}


public BigDecimal getDefaultAvailableQtyToReceive(I_M_ReceiptSchedule rs){
    final StockQtyAndUOMQty qty = receiptScheduleBL.getQtyToMove(rs);
    return qty == null || qty.signum() <= 0 ? BigDecimal.ZERO : qty.getStockQty().toBigDecimal();
}


}