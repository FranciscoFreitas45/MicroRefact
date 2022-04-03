package de.metas.ui.web.handlingunits.process;
 import java.math.BigDecimal;
import org.adempiere.model.InterfaceWrapperHelper;
import de.metas.handlingunits.allocation.ILUTUConfigurationFactory;
import de.metas.handlingunits.model.I_M_HU_LUTU_Configuration;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.handlingunits.receiptschedule.IHUReceiptScheduleBL;
import de.metas.printing.esb.base.util.Check;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.util.HUPackingInfoFormatter;
import de.metas.ui.web.handlingunits.util.HUPackingInfos;
import de.metas.util.Services;
public class WEBUI_M_ReceiptSchedule_ReceiveHUs_UsingDefaults extends WEBUI_M_ReceiptSchedule_ReceiveHUs_Base{

 private  ILUTUConfigurationFactory lutuConfigurationFactory;


public String buildDefaultPackingInfo(IProcessPreconditionsContext context){
    final I_M_ReceiptSchedule receiptSchedule = context.getSelectedModel(I_M_ReceiptSchedule.class);
    if (receiptSchedule == null) {
        // no override
        return null;
    }
    final I_M_HU_LUTU_Configuration lutuConfig = getCurrentLUTUConfiguration(receiptSchedule);
    adjustLUTUConfiguration(lutuConfig, receiptSchedule);
    return HUPackingInfoFormatter.newInstance().setShowLU(// NOTE: don't show LU info because makes the whole label to long. see https://github.com/metasfresh/metasfresh-webui-frontend/issues/315#issuecomment-280624562
    false).format(HUPackingInfos.of(lutuConfig));
}


@Override
public I_M_HU_LUTU_Configuration createM_HU_LUTU_Configuration(I_M_HU_LUTU_Configuration template){
    final I_M_HU_LUTU_Configuration lutuConfigurationNew = InterfaceWrapperHelper.copy().setFrom(template).copyToNew(I_M_HU_LUTU_Configuration.class);
    adjustLUTUConfiguration(lutuConfigurationNew, getM_ReceiptSchedule());
    // NOTE: don't save it
    return lutuConfigurationNew;
}


@Override
public boolean isUpdateReceiptScheduleDefaultConfiguration(){
    return false;
}


public void adjustLUTUConfiguration(I_M_HU_LUTU_Configuration lutuConfig,I_M_ReceiptSchedule receiptSchedule){
    if (lutuConfigurationFactory.isNoLU(lutuConfig)) {
        // 
        // Adjust TU
        lutuConfig.setIsInfiniteQtyTU(false);
        lutuConfig.setQtyTU(BigDecimal.ONE);
    } else {
        // 
        // Adjust LU
        lutuConfig.setIsInfiniteQtyLU(false);
        lutuConfig.setQtyLU(BigDecimal.ONE);
        // 
        // Adjust TU
        // * if the standard QtyTU is less than how much is available to be received => enforce the available Qty
        // * else always take the standard QtyTU
        // see https://github.com/metasfresh/metasfresh-webui/issues/228
        {
            final BigDecimal qtyToMoveTU = Services.get(IHUReceiptScheduleBL.class).getQtyToMoveTU(receiptSchedule);
            if (qtyToMoveTU.signum() > 0 && qtyToMoveTU.compareTo(lutuConfig.getQtyTU()) < 0) {
                lutuConfig.setQtyTU(qtyToMoveTU);
            }
        }
    }
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    final String defaultPackingInfo = buildDefaultPackingInfo(context);
    if (Check.isEmpty(defaultPackingInfo, true)) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("no default LU/TU configuration");
    }
    return super.checkPreconditionsApplicable(context).deriveWithCaptionOverride(defaultPackingInfo);
}


}