package de.metas.ui.web.quickinput.forecastline;
 import org.adempiere.model.InterfaceWrapperHelper.load;
import org.adempiere.model.InterfaceWrapperHelper.save;
import java.math.BigDecimal;
import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.api.IAttributeSetInstanceBL;
import org.adempiere.mm.attributes.api.ImmutableAttributeSet;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_M_AttributeSetInstance;
import org.compiere.model.I_M_Forecast;
import org.compiere.model.I_M_ForecastLine;
import org.compiere.model.I_M_Product;
import com.google.common.collect.ImmutableSet;
import de.metas.adempiere.gui.search.HUPackingAwareCopy.ASICopyMode;
import de.metas.adempiere.gui.search.IHUPackingAware;
import de.metas.adempiere.gui.search.IHUPackingAwareBL;
import de.metas.adempiere.gui.search.impl.ForecastLineHUPackingAware;
import de.metas.adempiere.gui.search.impl.PlainHUPackingAware;
import de.metas.ui.web.quickinput.IQuickInputProcessor;
import de.metas.ui.web.quickinput.QuickInput;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.sql.ProductLookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.ProductLookupDescriptor.ProductAndAttributes;
import de.metas.util.Services;
import lombok.NonNull;
public class ForecastLineQuickInputProcessor implements IQuickInputProcessor{

 private  IHUPackingAwareBL huPackingAwareBL;


@Override
public Set<DocumentId> process(QuickInput quickInput){
    final I_M_Forecast forecast = quickInput.getRootDocumentAs(I_M_Forecast.class);
    final I_M_ForecastLine forecastLine = InterfaceWrapperHelper.newInstance(I_M_ForecastLine.class, forecast);
    forecastLine.setM_Forecast(forecast);
    updateForecastLine(forecastLine, quickInput);
    save(forecastLine);
    final DocumentId documentId = DocumentId.of(forecastLine.getM_ForecastLine_ID());
    return ImmutableSet.of(documentId);
}


public int createASI(ProductAndAttributes productAndAttributes){
    final ImmutableAttributeSet attributes = productAndAttributes.getAttributes();
    if (attributes.isEmpty()) {
        return -1;
    }
    final IAttributeSetInstanceBL asiBL = Services.get(IAttributeSetInstanceBL.class);
    final I_M_AttributeSetInstance asi = asiBL.createASIWithASFromProductAndInsertAttributeSet(productAndAttributes.getProductId(), attributes);
    return asi.getM_AttributeSetInstance_ID();
}


public PlainHUPackingAware createAndInitHuPackingAware(I_M_Forecast forecast,IForecastLineQuickInput quickInput){
    final PlainHUPackingAware huPackingAware = new PlainHUPackingAware();
    huPackingAware.setC_BPartner_ID(forecast.getC_BPartner_ID());
    huPackingAware.setInDispute(false);
    final ProductAndAttributes productAndAttributes = ProductLookupDescriptor.toProductAndAttributes(quickInput.getM_Product_ID());
    final I_M_Product product = load(productAndAttributes.getProductId(), I_M_Product.class);
    huPackingAware.setM_Product_ID(product.getM_Product_ID());
    huPackingAware.setC_UOM_ID(product.getC_UOM_ID());
    huPackingAware.setM_AttributeSetInstance_ID(createASI(productAndAttributes));
    final int piItemProductId = quickInput.getM_HU_PI_Item_Product_ID();
    huPackingAware.setM_HU_PI_Item_Product_ID(piItemProductId);
    return huPackingAware;
}


public PlainHUPackingAware validateNewHuPackingAware(PlainHUPackingAware huPackingAware){
    if (huPackingAware.getQty() == null || huPackingAware.getQty().signum() <= 0) {
        throw new AdempiereException("Qty shall be greather than zero");
    }
    if (huPackingAware.getQtyTU() == null || huPackingAware.getQtyTU().signum() <= 0) {
        throw new AdempiereException("QtyTU shall be greather than zero");
    }
    return huPackingAware;
}


public void updateForecastLine(I_M_ForecastLine forecastLine,QuickInput fromQuickInput){
    final I_M_Forecast forecast = fromQuickInput.getRootDocumentAs(I_M_Forecast.class);
    final IForecastLineQuickInput fromForecastLineQuickInput = fromQuickInput.getQuickInputDocumentAs(IForecastLineQuickInput.class);
    final IHUPackingAware quickInputPackingAware = createQuickInputPackingAware(forecast, fromForecastLineQuickInput);
    final IHUPackingAware orderLinePackingAware = ForecastLineHUPackingAware.of(forecastLine);
    huPackingAwareBL.prepareCopyFrom(quickInputPackingAware).overridePartner(false).asiCopyMode(// because we just created the ASI
    ASICopyMode.CopyID).copyTo(orderLinePackingAware);
}


public IHUPackingAware createQuickInputPackingAware(I_M_Forecast forecast,IForecastLineQuickInput quickInput){
    final PlainHUPackingAware huPackingAware = createAndInitHuPackingAware(forecast, quickInput);
    final BigDecimal quickInputQty = quickInput.getQty();
    if (quickInputQty == null || quickInputQty.signum() <= 0) {
        throw new AdempiereException("Qty shall be greather than zero");
    }
    huPackingAwareBL.computeAndSetQtysForNewHuPackingAware(huPackingAware, quickInputQty);
    return validateNewHuPackingAware(huPackingAware);
}


}