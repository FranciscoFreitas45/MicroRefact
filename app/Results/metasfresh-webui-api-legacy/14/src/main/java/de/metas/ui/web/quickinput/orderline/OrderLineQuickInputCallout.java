package de.metas.ui.web.quickinput.orderline;
 import java.util.Optional;
import de.metas.ui.web.quickinput.field.DefaultPackingItemCriteria;
import de.metas.ui.web.quickinput.field.PackingItemProductFieldHelper;
import org.adempiere.ad.callout.api.ICalloutField;
import org.compiere.SpringContextHolder;
import de.metas.adempiere.model.I_C_Order;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.ShipmentAllocationBestBeforePolicy;
import de.metas.bpartner.service.IBPartnerBL;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.product.ProductId;
import de.metas.ui.web.quickinput.QuickInput;
import de.metas.ui.web.quickinput.field.DefaultPackingItemCriteria;
import de.metas.ui.web.quickinput.field.PackingItemProductFieldHelper;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.sql.ProductLookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.ProductLookupDescriptor.ProductAndAttributes;
import lombok.Builder;
import lombok.NonNull;
import org.adempiere.ad.callout.api.ICalloutField;
import org.compiere.SpringContextHolder;
import java.util.Optional;
public class OrderLineQuickInputCallout {

 private  IBPartnerBL bpartnersService;

 private  PackingItemProductFieldHelper packingItemProductFieldHelper;


public void onProductChanged(ICalloutField calloutField){
    final QuickInput quickInput = QuickInput.getQuickInputOrNull(calloutField);
    if (quickInput == null) {
        return;
    }
    updateM_HU_PI_Item_Product(quickInput);
    updateBestBeforePolicy(quickInput);
}


public void updateM_HU_PI_Item_Product(QuickInput quickInput){
    if (!quickInput.hasField(IOrderLineQuickInput.COLUMNNAME_M_HU_PI_Item_Product_ID)) {
        // there are users whose systems don't have M_HU_PI_Item_Product_ID in their quick-input
        return;
    }
    final IOrderLineQuickInput quickInputModel = quickInput.getQuickInputDocumentAs(IOrderLineQuickInput.class);
    final LookupValue productLookupValue = quickInputModel.getM_Product_ID();
    if (productLookupValue == null) {
        return;
    }
    final ProductAndAttributes productAndAttributes = ProductLookupDescriptor.toProductAndAttributes(productLookupValue);
    final ProductId quickInputProductId = productAndAttributes.getProductId();
    final I_C_Order order = quickInput.getRootDocumentAs(I_C_Order.class);
    final Optional<DefaultPackingItemCriteria> defaultPackingItemCriteria = DefaultPackingItemCriteria.of(order, quickInputProductId);
    final I_M_HU_PI_Item_Product huPIItemProduct = defaultPackingItemCriteria.flatMap(packingItemProductFieldHelper::getDefaultPackingMaterial).orElse(null);
    quickInputModel.setM_HU_PI_Item_Product(huPIItemProduct);
}


public void updateBestBeforePolicy(QuickInput quickInput){
    if (!quickInput.hasField(IOrderLineQuickInput.COLUMNNAME_ShipmentAllocation_BestBefore_Policy)) {
        return;
    }
    final I_C_Order order = quickInput.getRootDocumentAs(I_C_Order.class);
    final BPartnerId bpartnerId = BPartnerId.ofRepoIdOrNull(order.getC_BPartner_ID());
    if (bpartnerId == null) {
        return;
    }
    final ShipmentAllocationBestBeforePolicy bestBeforePolicy = bpartnersService.getBestBeforePolicy(bpartnerId);
    final IOrderLineQuickInput quickInputModel = quickInput.getQuickInputDocumentAs(IOrderLineQuickInput.class);
    quickInputModel.setShipmentAllocation_BestBefore_Policy(bestBeforePolicy.getCode());
}


}