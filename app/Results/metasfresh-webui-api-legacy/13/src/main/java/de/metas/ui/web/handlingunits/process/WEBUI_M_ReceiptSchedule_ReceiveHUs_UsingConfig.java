package de.metas.ui.web.handlingunits.process;
 import java.math.BigDecimal;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.model.InterfaceWrapperHelper;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.allocation.ILUTUConfigurationFactory;
import de.metas.handlingunits.model.I_M_HU_LUTU_Configuration;
import de.metas.handlingunits.model.I_M_HU_PI;
import de.metas.handlingunits.model.I_M_HU_PI_Item;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.handlingunits.model.I_M_HU_PI_Version;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.handlingunits.model.X_M_HU_PI_Version;
import de.metas.handlingunits.receiptschedule.IHUReceiptScheduleBL;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.Param;
import de.metas.util.Services;
public class WEBUI_M_ReceiptSchedule_ReceiveHUs_UsingConfig extends WEBUI_M_ReceiptSchedule_ReceiveHUs_Baseimplements IProcessDefaultParametersProvider{

 private  I_M_HU_LUTU_Configuration _defaultLUTUConfiguration;

 private  String PARAM_IsSaveLUTUConfiguration;

@Param(parameterName = PARAM_IsSaveLUTUConfiguration)
 private  boolean p_IsSaveLUTUConfiguration;

 private  String PARAM_M_HU_PI_Item_Product_ID;

@Param(parameterName = PARAM_M_HU_PI_Item_Product_ID)
 private  int p_M_HU_PI_Item_Product_ID;

 private  String PARAM_M_LU_HU_PI_ID;

@Param(parameterName = PARAM_M_LU_HU_PI_ID)
 private  int p_M_LU_HU_PI_ID;

 private  String PARAM_QtyCU;

@Param(parameterName = PARAM_QtyCU)
 private  BigDecimal p_QtyCU;

 private  String PARAM_QtyTU;

@Param(parameterName = PARAM_QtyTU)
 private  BigDecimal p_QtyTU;

 private  String PARAM_QtyLU;

@Param(parameterName = PARAM_QtyLU)
 private  BigDecimal p_QtyLU;


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    switch(parameter.getColumnName()) {
        case PARAM_M_HU_PI_Item_Product_ID:
            return getDefaultLUTUConfiguration().getM_HU_PI_Item_Product_ID();
        case PARAM_M_LU_HU_PI_ID:
            return getDefaultLUTUConfiguration().getM_LU_HU_PI_ID();
        case PARAM_QtyCU:
            return getDefaultLUTUConfiguration().getQtyCU();
        case PARAM_QtyTU:
            return getDefaultLUTUConfiguration().getQtyTU();
        case PARAM_QtyLU:
            return getDefaultLUTUConfiguration().getQtyLU();
        default:
            return DEFAULT_VALUE_NOTAVAILABLE;
    }
}


@Override
public I_M_HU_LUTU_Configuration createM_HU_LUTU_Configuration(I_M_HU_LUTU_Configuration template){
    // Validate parameters
    final int M_LU_HU_PI_ID = p_M_LU_HU_PI_ID;
    final int M_HU_PI_Item_Product_ID = p_M_HU_PI_Item_Product_ID;
    final BigDecimal qtyCU = p_QtyCU;
    final BigDecimal qtyTU = p_QtyTU;
    final BigDecimal qtyLU = p_QtyLU;
    if (M_HU_PI_Item_Product_ID <= 0) {
        throw new FillMandatoryException(PARAM_M_HU_PI_Item_Product_ID);
    }
    if (qtyCU == null || qtyCU.signum() <= 0) {
        throw new FillMandatoryException(PARAM_QtyCU);
    }
    if (qtyTU == null || qtyTU.signum() <= 0) {
        throw new FillMandatoryException(PARAM_QtyTU);
    }
    final I_M_HU_LUTU_Configuration lutuConfigurationNew = InterfaceWrapperHelper.copy().setFrom(template).copyToNew(I_M_HU_LUTU_Configuration.class);
    // 
    // CU
    lutuConfigurationNew.setQtyCU(qtyCU);
    lutuConfigurationNew.setIsInfiniteQtyCU(false);
    // 
    // TU
    final I_M_HU_PI_Item_Product tuPIItemProduct = InterfaceWrapperHelper.create(getCtx(), M_HU_PI_Item_Product_ID, I_M_HU_PI_Item_Product.class, ITrx.TRXNAME_None);
    final I_M_HU_PI tuPI = tuPIItemProduct.getM_HU_PI_Item().getM_HU_PI_Version().getM_HU_PI();
    lutuConfigurationNew.setM_HU_PI_Item_Product_ID(tuPIItemProduct.getM_HU_PI_Item_Product_ID());
    lutuConfigurationNew.setM_TU_HU_PI(tuPI);
    lutuConfigurationNew.setQtyTU(qtyTU);
    lutuConfigurationNew.setIsInfiniteQtyTU(false);
    // 
    // LU
    if (M_LU_HU_PI_ID > 0) {
        if (qtyLU == null || qtyLU.signum() <= 0) {
            throw new FillMandatoryException(PARAM_QtyLU);
        }
        final I_M_HU_PI luPI = InterfaceWrapperHelper.create(getCtx(), M_LU_HU_PI_ID, I_M_HU_PI.class, ITrx.TRXNAME_None);
        final IHandlingUnitsDAO handlingUnitsDAO = Services.get(IHandlingUnitsDAO.class);
        final I_M_HU_PI_Version luPIV = handlingUnitsDAO.retrievePICurrentVersion(luPI);
        final I_M_HU_PI_Item luPI_Item = handlingUnitsDAO.retrieveParentPIItemsForParentPI(tuPI, X_M_HU_PI_Version.HU_UNITTYPE_LoadLogistiqueUnit, ILUTUConfigurationFactory.extractBPartnerIdOrNull(lutuConfigurationNew)).stream().filter(piItem -> piItem.getM_HU_PI_Version_ID() == luPIV.getM_HU_PI_Version_ID()).findFirst().orElseThrow(() -> new AdempiereException(tuPI.getName() + " cannot be loaded to " + luPI.getName()));
        lutuConfigurationNew.setM_LU_HU_PI(luPI);
        lutuConfigurationNew.setM_LU_HU_PI_Item(luPI_Item);
        lutuConfigurationNew.setQtyLU(qtyLU);
        lutuConfigurationNew.setIsInfiniteQtyLU(false);
    } else {
        lutuConfigurationNew.setM_LU_HU_PI(null);
        lutuConfigurationNew.setM_LU_HU_PI_Item(null);
        lutuConfigurationNew.setQtyLU(BigDecimal.ZERO);
    }
    // InterfaceWrapperHelper.save(lutuConfigurationNew); // expected to not be saved (important)
    return lutuConfigurationNew;
}


public void adjustLUTUConfiguration(I_M_HU_LUTU_Configuration lutuConfig,I_M_ReceiptSchedule fromReceiptSchedule){
    // 
    // TU
    final BigDecimal qtyToReceiveTU = Services.get(IHUReceiptScheduleBL.class).getQtyToMoveTU(fromReceiptSchedule);
    {
        if (qtyToReceiveTU.signum() > 0 && qtyToReceiveTU.compareTo(lutuConfig.getQtyTU()) < 0) {
            lutuConfig.setQtyTU(qtyToReceiveTU);
        }
    }
    // 
    // LU
    {
        final int qtyLU;
        if (qtyToReceiveTU.signum() <= 0) {
            qtyLU = 0;
        } else {
            final ILUTUConfigurationFactory lutuConfigurationFactory = Services.get(ILUTUConfigurationFactory.class);
            qtyLU = lutuConfigurationFactory.calculateQtyLUForTotalQtyTUs(lutuConfig, qtyToReceiveTU);
        }
        lutuConfig.setQtyLU(BigDecimal.valueOf(qtyLU));
    }
}


@Override
public boolean isUpdateReceiptScheduleDefaultConfiguration(){
    return p_IsSaveLUTUConfiguration;
}


public I_M_HU_LUTU_Configuration getDefaultLUTUConfiguration(){
    if (_defaultLUTUConfiguration == null) {
        final I_M_ReceiptSchedule receiptSchedule = getM_ReceiptSchedule();
        final I_M_HU_LUTU_Configuration defaultLUTUConfiguration = getCurrentLUTUConfiguration(receiptSchedule);
        adjustLUTUConfiguration(defaultLUTUConfiguration, receiptSchedule);
        _defaultLUTUConfiguration = defaultLUTUConfiguration;
    }
    return _defaultLUTUConfiguration;
}


}