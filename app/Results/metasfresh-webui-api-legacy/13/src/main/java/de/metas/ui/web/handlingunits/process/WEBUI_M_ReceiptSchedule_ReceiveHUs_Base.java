package de.metas.ui.web.handlingunits.process;
 import java.util.List;
import org.adempiere.model.InterfaceWrapperHelper;
import de.metas.adempiere.form.terminal.TerminalException;
import de.metas.handlingunits.allocation.ILUTUConfigurationFactory;
import de.metas.handlingunits.allocation.ILUTUProducerAllocationDestination;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_LUTU_Configuration;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.handlingunits.receiptschedule.IHUReceiptScheduleBL;
import de.metas.handlingunits.receiptschedule.impl.ReceiptScheduleHUGenerator;
import de.metas.inoutcandidate.api.IReceiptScheduleBL;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.quantity.Quantity;
import de.metas.util.Services;
public class WEBUI_M_ReceiptSchedule_ReceiveHUs_Base extends ReceiptScheduleBasedProcess{


public I_M_HU_LUTU_Configuration getCurrentLUTUConfiguration(I_M_ReceiptSchedule receiptSchedule){
    final I_M_HU_LUTU_Configuration lutuConfig = Services.get(IHUReceiptScheduleBL.class).createLUTUConfigurationManager(receiptSchedule).getCreateLUTUConfiguration();
    // Make sure nobody is overriding the existing configuration
    if (lutuConfig.getM_HU_LUTU_Configuration_ID() > 0) {
        InterfaceWrapperHelper.setSaveDeleteDisabled(lutuConfig, true);
    }
    return lutuConfig;
}


public I_M_ReceiptSchedule getM_ReceiptSchedule(){
    return getRecord(I_M_ReceiptSchedule.class);
}


public I_M_HU_LUTU_Configuration createM_HU_LUTU_Configuration(I_M_HU_LUTU_Configuration template)


public boolean isUpdateReceiptScheduleDefaultConfiguration()


@Override
@RunOutOfTrx
public String doIt(){
    final I_M_ReceiptSchedule receiptSchedule = getM_ReceiptSchedule();
    final ReceiptScheduleHUGenerator huGenerator = ReceiptScheduleHUGenerator.newInstance(this).addM_ReceiptSchedule(receiptSchedule).setUpdateReceiptScheduleDefaultConfiguration(isUpdateReceiptScheduleDefaultConfiguration());
    // 
    // Get/Create the initial LU/TU configuration
    final I_M_HU_LUTU_Configuration lutuConfigurationOrig = getCurrentLUTUConfiguration(receiptSchedule);
    // 
    // Create the effective LU/TU configuration
    final I_M_HU_LUTU_Configuration lutuConfiguration = createM_HU_LUTU_Configuration(lutuConfigurationOrig);
    Services.get(ILUTUConfigurationFactory.class).save(lutuConfiguration);
    huGenerator.setM_HU_LUTU_Configuration(lutuConfiguration);
    // 
    // Calculate the target CUs that we want to allocate
    final ILUTUProducerAllocationDestination lutuProducer = huGenerator.getLUTUProducerAllocationDestination();
    final Quantity qtyCUsTotal = lutuProducer.calculateTotalQtyCU();
    if (qtyCUsTotal.isInfinite()) {
        throw new TerminalException("LU/TU configuration is resulting to infinite quantity: " + lutuConfiguration);
    }
    huGenerator.setQtyToAllocateTarget(qtyCUsTotal);
    // 
    // Generate the HUs
    final List<I_M_HU> hus = huGenerator.generateWithinOwnTransaction();
    openHUsToReceive(hus);
    return MSG_OK;
}


public ProcessPreconditionsResolution checkEligibleForReceivingHUs(I_M_ReceiptSchedule receiptSchedule){
    // Receipt schedule shall not be already closed
    final IReceiptScheduleBL receiptScheduleBL = Services.get(IReceiptScheduleBL.class);
    if (receiptScheduleBL.isClosed(receiptSchedule)) {
        return ProcessPreconditionsResolution.reject("receipt schedule closed");
    }
    // Receipt schedule shall not be about packing materials
    if (receiptSchedule.isPackagingMaterial()) {
        return ProcessPreconditionsResolution.reject("not applying for packing materials");
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    if (context.isNoSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    if (!context.isSingleSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final I_M_ReceiptSchedule receiptSchedule = context.getSelectedModel(I_M_ReceiptSchedule.class);
    // guard against null (might happen if the selected ID is not valid)
    if (receiptSchedule == null) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    return checkEligibleForReceivingHUs(receiptSchedule);
}


}