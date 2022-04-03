package de.metas.ui.web.order.sales.purchasePlanning.view;
 import org.adempiere.model.InterfaceWrapperHelper.loadOutOfTrx;
import org.adempiere.model.InterfaceWrapperHelper.translate;
import javax.annotation.Nullable;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_AttributeSetInstance;
import org.compiere.model.I_M_Product;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.service.IBPartnerDAO;
import de.metas.product.IProductBL;
import de.metas.product.IProductDAO;
import de.metas.product.ProductId;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.util.Check;
import de.metas.util.Services;
public class PurchaseRowLookups {

 private  IProductDAO productsRepo;

 private  IProductBL productBL;

 private  IBPartnerDAO bpartnersRepo;


public LookupValue createProductLookupValue(ProductId productId,String productValue,String productName){
    if (productId == null) {
        return null;
    }
    final I_M_Product product = productsRepo.getById(productId);
    if (product == null) {
        return IntegerLookupValue.unknown(productId.getRepoId());
    }
    final String productValueEffective = !Check.isEmpty(productValue, true) ? productValue.trim() : product.getValue();
    final String productNameEffective = !Check.isEmpty(productName, true) ? productName.trim() : product.getName();
    final String displayName = productValueEffective + "_" + productNameEffective;
    return IntegerLookupValue.of(product.getM_Product_ID(), displayName);
}


public String createUOMLookupValue(I_C_UOM uom){
    return translate(uom, I_C_UOM.class).getUOMSymbol();
}


public String createUOMLookupValueForProductId(ProductId productId){
    if (productId == null) {
        return null;
    }
    final I_C_UOM uom = productBL.getStockUOM(productId);
    if (uom == null) {
        return null;
    }
    return createUOMLookupValue(uom);
}


public PurchaseRowLookups newInstance(){
    return new PurchaseRowLookups();
}


public LookupValue createASILookupValue(AttributeSetInstanceId attributeSetInstanceId){
    if (attributeSetInstanceId == null) {
        return null;
    }
    final I_M_AttributeSetInstance asi = loadOutOfTrx(attributeSetInstanceId.getRepoId(), I_M_AttributeSetInstance.class);
    if (asi == null) {
        return null;
    }
    String description = asi.getDescription();
    if (Check.isEmpty(description, true)) {
        description = "<" + attributeSetInstanceId.getRepoId() + ">";
    }
    return IntegerLookupValue.of(attributeSetInstanceId.getRepoId(), description);
}


public LookupValue createBPartnerLookupValue(BPartnerId bpartnerId){
    if (bpartnerId == null) {
        return null;
    }
    final I_C_BPartner bpartner = bpartnersRepo.getById(bpartnerId);
    if (bpartner == null) {
        return null;
    }
    final String displayName = bpartner.getValue() + "_" + bpartner.getName();
    return IntegerLookupValue.of(bpartner.getC_BPartner_ID(), displayName);
}


}