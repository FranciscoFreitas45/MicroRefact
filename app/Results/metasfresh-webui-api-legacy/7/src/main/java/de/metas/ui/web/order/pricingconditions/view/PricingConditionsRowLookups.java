package de.metas.ui.web.order.pricingconditions.view;
 import java.awt.Color;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_Currency;
import org.compiere.model.I_M_DiscountSchemaBreak;
import org.compiere.model.I_M_PricingSystem;
import org.compiere.model.I_M_Product;
import org.compiere.util.Evaluatees;
import de.metas.bpartner.BPartnerId;
import de.metas.cache.CCache;
import de.metas.money.CurrencyId;
import de.metas.order.IOrderLinePricingConditions;
import de.metas.payment.paymentterm.PaymentTermId;
import de.metas.pricing.PricingSystemId;
import de.metas.pricing.conditions.PriceSpecificationType;
import de.metas.product.ProductId;
import de.metas.ui.web.window.datatypes.ColorValue;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.user.UserId;
import de.metas.util.IColorRepository;
import de.metas.util.MFColor;
import de.metas.util.Services;
import lombok.NonNull;
public class PricingConditionsRowLookups {

 private  LookupDataSource bpartnerLookup;

 private  LookupDataSource userLookup;

 private  LookupDataSource productLookup;

 private  LookupDataSource priceTypeLookup;

 private  LookupDataSource pricingSystemLookup;

 private  LookupDataSource paymentTermLookup;

 private  LookupDataSource currencyIdLookup;

 private  CCache<Integer,String> temporaryPriceConditionsColorCache;


public LookupValue lookupBPartner(BPartnerId bpartnerId){
    if (bpartnerId == null) {
        return null;
    }
    return bpartnerLookup.findById(bpartnerId.getRepoId());
}


public String toHexString(MFColor color){
    if (color == null) {
        return null;
    }
    final Color awtColor = color.toFlatColor().getFlatColor();
    return ColorValue.toHexString(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());
}


public LookupValuesList getFieldDropdown(String fieldName){
    return getLookupDataSource(fieldName).findEntities(Evaluatees.empty(), 20);
}


public LookupDataSource getLookupDataSource(String fieldName){
    if (PricingConditionsRow.FIELDNAME_PaymentTerm.equals(fieldName)) {
        return paymentTermLookup;
    } else if (PricingConditionsRow.FIELDNAME_BasePriceType.equals(fieldName)) {
        return priceTypeLookup;
    } else if (PricingConditionsRow.FIELDNAME_BasePricingSystem.equals(fieldName)) {
        return pricingSystemLookup;
    } else if (PricingConditionsRow.FIELDNAME_C_Currency_ID.equals(fieldName)) {
        return currencyIdLookup;
    } else {
        throw new AdempiereException("Field " + fieldName + " does not exist or it's not a lookup field");
    }
}


public PricingConditionsRowLookups newInstance(){
    return new PricingConditionsRowLookups();
}


public LookupValue lookupPaymentTerm(PaymentTermId paymentTermId){
    if (paymentTermId == null) {
        return null;
    }
    return paymentTermLookup.findById(paymentTermId.getRepoId());
}


public LookupValue lookupProduct(ProductId productId){
    if (productId == null) {
        return null;
    }
    return productLookup.findById(productId.getRepoId());
}


public LookupValue lookupCurrency(CurrencyId currencyId){
    if (currencyId == null) {
        return null;
    }
    return currencyIdLookup.findById(currencyId.getRepoId());
}


public String getTemporaryPriceConditionsColor(){
    return temporaryPriceConditionsColorCache.getOrLoad(0, this::retrieveTemporaryPriceConditionsColor);
}


public LookupValuesList getFieldTypeahead(String fieldName,String query){
    return getLookupDataSource(fieldName).findEntities(Evaluatees.empty(), query);
}


public LookupValue lookupPricingSystem(PricingSystemId pricingSystemId){
    if (pricingSystemId == null) {
        return null;
    }
    return pricingSystemLookup.findById(pricingSystemId.getRepoId());
}


public String retrieveTemporaryPriceConditionsColor(){
    final int temporaryPriceConditionsColorId = Services.get(IOrderLinePricingConditions.class).getTemporaryPriceConditionsColorId();
    return toHexString(Services.get(IColorRepository.class).getColorById(temporaryPriceConditionsColorId));
}


public LookupValue lookupPriceType(PriceSpecificationType priceType){
    return priceTypeLookup.findById(priceType.getCode());
}


public LookupValue lookupUser(UserId userId){
    if (userId == null) {
        return null;
    }
    return userLookup.findById(userId.getRepoId());
}


}