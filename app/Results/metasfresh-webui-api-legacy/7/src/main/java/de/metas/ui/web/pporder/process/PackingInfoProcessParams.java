package de.metas.ui.web.pporder.process;
 import org.adempiere.model.InterfaceWrapperHelper.loadOutOfTrx;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.util.Env;
import de.metas.bpartner.BPartnerId;
import de.metas.handlingunits.HUPIItemProductId;
import de.metas.handlingunits.IHUPIItemProductDAO;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.allocation.ILUTUConfigurationFactory;
import de.metas.handlingunits.impl.IDocumentLUTUConfigurationManager;
import de.metas.handlingunits.model.I_M_HU_LUTU_Configuration;
import de.metas.handlingunits.model.I_M_HU_PI;
import de.metas.handlingunits.model.I_M_HU_PI_Item;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.printing.esb.base.util.Check;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.Param;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.ui.web.handlingunits.util.WEBUI_ProcessHelper;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
public class PackingInfoProcessParams {

 private  ILUTUConfigurationFactory lutuConfigurationFactory;

 public  String PARAM_M_HU_PI_Item_Product_ID;

@Param(parameterName = PARAM_M_HU_PI_Item_Product_ID)
 private  int tu_HU_PI_Item_Product_ID;

 public  String PARAM_M_HU_PI_Item_ID;

@Param(parameterName = PARAM_M_HU_PI_Item_ID)
 private  int lu_PI_Item_ID;

 public  String PARAM_QtyCU;

@Param(parameterName = PARAM_QtyCU)
 private  BigDecimal qtyCU;

 public  String PARAM_QtyTU;

@Param(parameterName = PARAM_QtyTU)
 private  BigDecimal qtyTU;

 public  String PARAM_QtyLU;

@Param(parameterName = PARAM_QtyLU)
 private  BigDecimal qtyLU;

 private  IDocumentLUTUConfigurationManager defaultLUTUConfigManager;

 private  I_M_HU_LUTU_Configuration _defaultLUTUConfig;

 private  BigDecimal enforceAvailableQtyTU;

 private  boolean enforceOneLUorTU;

 private  boolean enforcePhysicalTU;


public void insertPhysicalFallbackTU(I_M_HU_LUTU_Configuration defaultLUTUConfig){
    final BPartnerId bpartnerId = ILUTUConfigurationFactory.extractBPartnerIdOrNull(defaultLUTUConfig);
    final ProductId productId = ProductId.ofRepoId(defaultLUTUConfig.getM_Product_ID());
    final List<I_M_HU_PI_Item_Product> availableHUPIItemProductRecords = WEBUI_ProcessHelper.retrieveHUPIItemProductRecords(Env.getCtx(), productId, bpartnerId, // includeVirtualItem == false
    false);
    Check.errorIf(availableHUPIItemProductRecords.isEmpty(), "There is no non-virtual M_HU_PI_Item_Product value for the given product and bPartner; product={}; bPartner={}", productId, bpartnerId);
    final I_M_HU_PI_Item_Product pip = availableHUPIItemProductRecords.get(0);
    defaultLUTUConfig.setM_HU_PI_Item_Product_ID(pip.getM_HU_PI_Item_Product_ID());
    defaultLUTUConfig.setM_TU_HU_PI_ID(pip.getM_HU_PI_Item().getM_HU_PI_Version().getM_HU_PI_ID());
    defaultLUTUConfig.setQtyCU(pip.getQty());
    final List<I_M_HU_PI_Item> luPIItems = getAvailableLuPIItems(pip, bpartnerId);
    if (luPIItems.isEmpty()) {
        defaultLUTUConfig.setM_LU_HU_PI_Item(null);
        defaultLUTUConfig.setM_LU_HU_PI(null);
    } else {
        final I_M_HU_PI_Item luPiItem = luPIItems.get(0);
        defaultLUTUConfig.setM_LU_HU_PI_Item(luPiItem);
        defaultLUTUConfig.setQtyTU(luPiItem.getQty());
        defaultLUTUConfig.setM_LU_HU_PI_ID(luPiItem.getM_HU_PI_Version().getM_HU_PI_ID());
    }
}


public LookupValuesList getM_HU_PI_Item_IDs(I_M_HU_PI_Item_Product pip){
    if (pip == null) {
        return LookupValuesList.EMPTY;
    }
    final List<I_M_HU_PI_Item> luPIItems = getAvailableLuPIItems(pip, ILUTUConfigurationFactory.extractBPartnerIdOrNull(getDefaultLUTUConfig()));
    return luPIItems.stream().map(luPIItem -> IntegerLookupValue.of(luPIItem.getM_HU_PI_Item_ID(), WEBUI_ProcessHelper.buildHUPIItemString(luPIItem))).collect(LookupValuesList.collect());
}


public void setLuPiItemId(int lu_PI_Item_ID){
    this.lu_PI_Item_ID = lu_PI_Item_ID;
}


public BigDecimal getQtyLU(){
    return qtyLU;
}


public void setQtyLU(BigDecimal qtyLU){
    this.qtyLU = qtyLU;
}


public I_M_HU_LUTU_Configuration createAndSaveNewLUTUConfig(){
    final I_M_HU_LUTU_Configuration defaultLUTUConfig = getDefaultLUTUConfig();
    // Validate parameters
    // not mandatory
    final int lu_PI_Item_ID = getLuPiItemId();
    final HUPIItemProductId M_HU_PI_Item_Product_ID = getTU_HU_PI_Item_Product_ID();
    final BigDecimal qtyCU = getQtyCU();
    final BigDecimal qtyTU = M_HU_PI_Item_Product_ID.isVirtualHU() ? BigDecimal.ONE : this.qtyTU;
    if (qtyCU == null || qtyCU.signum() <= 0) {
        throw new FillMandatoryException(PARAM_QtyCU);
    }
    if (qtyTU == null || qtyTU.signum() <= 0) {
        throw new FillMandatoryException(PARAM_QtyTU);
    }
    final I_M_HU_LUTU_Configuration lutuConfigNew = InterfaceWrapperHelper.copy().setFrom(defaultLUTUConfig).copyToNew(I_M_HU_LUTU_Configuration.class);
    // CU
    lutuConfigNew.setQtyCU(qtyCU);
    lutuConfigNew.setIsInfiniteQtyCU(false);
    // TU
    configureLUTUConfigTU(lutuConfigNew, M_HU_PI_Item_Product_ID, qtyTU);
    // LU
    configureLUTUConfigLU(lutuConfigNew, lu_PI_Item_ID);
    lutuConfigurationFactory.save(lutuConfigNew);
    return lutuConfigNew;
}


public void adjustDefaultLUTUConfig_EnforceAvailableTUs(I_M_HU_LUTU_Configuration defaultLUTUConfig,BigDecimal availableQtyTU){
    // 
    // TU
    {
        if (availableQtyTU.signum() > 0 && availableQtyTU.compareTo(defaultLUTUConfig.getQtyTU()) < 0) {
            defaultLUTUConfig.setQtyTU(availableQtyTU);
        }
    }
    // 
    // LU
    {
        final int qtyLU;
        if (availableQtyTU.signum() <= 0) {
            qtyLU = 0;
        } else {
            qtyLU = lutuConfigurationFactory.calculateQtyLUForTotalQtyTUs(defaultLUTUConfig, availableQtyTU);
        }
        defaultLUTUConfig.setQtyLU(BigDecimal.valueOf(qtyLU));
    }
}


public LookupValuesList getM_HU_PI_Item_Products(){
    final I_M_HU_LUTU_Configuration defaultLUTUConfig = getDefaultLUTUConfig();
    final ProductId productId = ProductId.ofRepoId(defaultLUTUConfig.getM_Product_ID());
    final BPartnerId bpartnerId = ILUTUConfigurationFactory.extractBPartnerIdOrNull(defaultLUTUConfig);
    final boolean includeVirtualItem = !enforcePhysicalTU;
    final LookupValuesList huPIItemProducts = WEBUI_ProcessHelper.retrieveHUPIItemProducts(Env.getCtx(), productId, bpartnerId, includeVirtualItem);
    return huPIItemProducts;
}


public Quantity calculateTotalQtyCUs(I_M_HU_LUTU_Configuration lutuConfig){
    final Quantity qtyCUsTotal = Services.get(ILUTUConfigurationFactory.class).calculateQtyCUsTotal(lutuConfig);
    if (qtyCUsTotal.isZero()) {
        throw new AdempiereException("Zero quantity to receive");
    } else if (qtyCUsTotal.isInfinite()) {
        throw new AdempiereException("Quantity to receive was not determined");
    }
    return qtyCUsTotal;
}


public void setQtyTU(BigDecimal qtyTU){
    this.qtyTU = qtyTU;
}


public List<I_M_HU_PI_Item> getAvailableLuPIItems(I_M_HU_PI_Item_Product pip,BPartnerId bpartnerId){
    final IHandlingUnitsDAO handlingUnitsDAO = Services.get(IHandlingUnitsDAO.class);
    final I_M_HU_PI piOfCurrentPip = pip.getM_HU_PI_Item().getM_HU_PI_Version().getM_HU_PI();
    final List<I_M_HU_PI_Item> luPIItems = handlingUnitsDAO.retrieveParentPIItemsForParentPI(piOfCurrentPip, // huUnitType
    null, bpartnerId);
    return luPIItems;
}


public I_M_HU_LUTU_Configuration getDefaultLUTUConfig(){
    if (_defaultLUTUConfig == null) {
        final I_M_HU_LUTU_Configuration defaultLUTUConfig = defaultLUTUConfigManager.getCreateLUTUConfiguration();
        // 
        // Apply adjustment rules to default LU/TU config
        if (enforceOneLUorTU) {
            adjustDefaultLUTUConfig_EnforceOneLUorTU(defaultLUTUConfig, enforceAvailableQtyTU);
        } else if (enforceAvailableQtyTU != null) {
            // IMPORTANT: this rule shall be applied if not enforceOneLUorTU
            adjustDefaultLUTUConfig_EnforceAvailableTUs(defaultLUTUConfig, enforceAvailableQtyTU);
        }
        if (enforcePhysicalTU) {
            // check if we need to do something
            boolean needToFallback;
            if (defaultLUTUConfig.getM_HU_PI_Item_Product_ID() <= 0) {
                // no piip specified at all
                needToFallback = true;
            } else {
                // check if the piip that we got is the virtual one. If yes, we need to fallback
                final IHUPIItemProductDAO hupiItemProductDAO = Services.get(IHUPIItemProductDAO.class);
                needToFallback = defaultLUTUConfig.getM_HU_PI_Item_Product_ID() == hupiItemProductDAO.retrieveVirtualPIMaterialItemProduct(Env.getCtx()).getM_HU_PI_Item_Product_ID();
            }
            if (needToFallback) {
                insertPhysicalFallbackTU(defaultLUTUConfig);
            }
        }
        // 
        // Make sure nobody is overriding the existing configuration
        if (defaultLUTUConfig.getM_HU_LUTU_Configuration_ID() > 0) {
            InterfaceWrapperHelper.setSaveDeleteDisabled(defaultLUTUConfig, true);
        }
        _defaultLUTUConfig = defaultLUTUConfig;
    }
    return _defaultLUTUConfig;
}


public I_M_HU_LUTU_Configuration createNewLUTUConfigFromDefaultsOnly(){
    final I_M_HU_LUTU_Configuration defaultLUTUConfigNewCopy = InterfaceWrapperHelper.copy().setFrom(getDefaultLUTUConfig()).copyToNew(I_M_HU_LUTU_Configuration.class);
    lutuConfigurationFactory.save(defaultLUTUConfigNewCopy);
    return defaultLUTUConfigNewCopy;
}


public Object getParameterDefaultValue(String parameterName){
    final I_M_HU_LUTU_Configuration defaultLUTUConfig = getDefaultLUTUConfig();
    switch(parameterName) {
        case PARAM_M_HU_PI_Item_Product_ID:
            return defaultLUTUConfig.getM_HU_PI_Item_Product_ID();
        case PARAM_M_HU_PI_Item_ID:
            return defaultLUTUConfig.getM_LU_HU_PI_Item_ID();
        case PARAM_QtyCU:
            return defaultLUTUConfig.getQtyCU();
        case PARAM_QtyTU:
            return defaultLUTUConfig.getQtyTU();
        case PARAM_QtyLU:
            return defaultLUTUConfig.getQtyLU();
        default:
            return IProcessDefaultParametersProvider.DEFAULT_VALUE_NOTAVAILABLE;
    }
}


public HUPIItemProductId getTU_HU_PI_Item_Product_ID(){
    if (tu_HU_PI_Item_Product_ID <= 0) {
        throw new FillMandatoryException(PARAM_M_HU_PI_Item_Product_ID);
    }
    return HUPIItemProductId.ofRepoId(tu_HU_PI_Item_Product_ID);
}


public void configureLUTUConfigTU(I_M_HU_LUTU_Configuration lutuConfigNew,HUPIItemProductId M_HU_PI_Item_Product_ID,BigDecimal qtyTU){
    final I_M_HU_PI_Item_Product tuPIItemProduct = Services.get(IHUPIItemProductDAO.class).getById(M_HU_PI_Item_Product_ID);
    final I_M_HU_PI tuPI = tuPIItemProduct.getM_HU_PI_Item().getM_HU_PI_Version().getM_HU_PI();
    lutuConfigNew.setM_HU_PI_Item_Product_ID(tuPIItemProduct.getM_HU_PI_Item_Product_ID());
    lutuConfigNew.setM_TU_HU_PI(tuPI);
    lutuConfigNew.setQtyTU(qtyTU);
    lutuConfigNew.setIsInfiniteQtyTU(false);
}


public int getLuPiItemId(){
    return lu_PI_Item_ID;
}


public void adjustDefaultLUTUConfig_EnforceOneLUorTU(I_M_HU_LUTU_Configuration defaultLUTUConfig,BigDecimal availableQtyTU){
    if (lutuConfigurationFactory.isNoLU(defaultLUTUConfig)) {
        // 
        // Adjust TU
        defaultLUTUConfig.setIsInfiniteQtyTU(false);
        defaultLUTUConfig.setQtyTU(BigDecimal.ONE);
    } else {
        // 
        // Adjust LU
        defaultLUTUConfig.setIsInfiniteQtyLU(false);
        defaultLUTUConfig.setQtyLU(BigDecimal.ONE);
        // 
        // Adjust TU
        // * if the standard QtyTU is less than how much is available to be received => enforce the available Qty
        // * else always take the standard QtyTU
        // see https://github.com/metasfresh/metasfresh-webui/issues/228
        {
            if (availableQtyTU != null && availableQtyTU.signum() > 0 && availableQtyTU.compareTo(defaultLUTUConfig.getQtyTU()) < 0) {
                defaultLUTUConfig.setQtyTU(availableQtyTU);
            }
        }
    }
}


public void setTU_HU_PI_Item_Product_ID(int tu_HU_PI_Item_Product_ID){
    this.tu_HU_PI_Item_Product_ID = tu_HU_PI_Item_Product_ID;
}


public void setQtyCU(BigDecimal qtyCU){
    this.qtyCU = qtyCU;
}


public void configureLUTUConfigLU(I_M_HU_LUTU_Configuration lutuConfigNew,int lu_PI_Item_ID){
    if (lu_PI_Item_ID > 0) {
        final BigDecimal qtyLU = getQtyLU();
        if (qtyLU == null || qtyLU.signum() <= 0) {
            throw new FillMandatoryException(PARAM_QtyLU);
        }
        final I_M_HU_PI_Item luPI_Item = loadOutOfTrx(lu_PI_Item_ID, I_M_HU_PI_Item.class);
        lutuConfigNew.setM_LU_HU_PI_Item(luPI_Item);
        lutuConfigNew.setM_LU_HU_PI(luPI_Item.getM_HU_PI_Version().getM_HU_PI());
        lutuConfigNew.setQtyLU(qtyLU);
        lutuConfigNew.setIsInfiniteQtyLU(false);
    } else {
        lutuConfigNew.setM_LU_HU_PI(null);
        lutuConfigNew.setM_LU_HU_PI_Item(null);
        lutuConfigNew.setQtyLU(BigDecimal.ZERO);
    }
}


public BigDecimal getQtyCU(){
    return qtyCU;
}


}