package de.metas.ui.web.quickinput.invoiceline;
 import de.metas.adempiere.model.I_C_Invoice;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.product.ProductId;
import de.metas.ui.web.quickinput.QuickInput;
import de.metas.ui.web.quickinput.field.DefaultPackingItemCriteria;
import de.metas.ui.web.quickinput.field.PackingItemProductFieldHelper;
import lombok.NonNull;
import org.adempiere.ad.callout.api.ICalloutField;
import org.springframework.stereotype.Component;
import java.util.Optional;
@Component
public class InvoiceLineQuickInputCallout {

 private  PackingItemProductFieldHelper packingItemProductFieldHelper;


public void onProductChange(ICalloutField calloutField){
    final QuickInput quickInput = QuickInput.getQuickInputOrNull(calloutField);
    if (quickInput == null) {
        return;
    }
    if (!quickInput.hasField(IInvoiceLineQuickInput.COLUMNNAME_M_HU_PI_Item_Product_ID)) {
        return;
    }
    final IInvoiceLineQuickInput invoiceLineQuickInput = quickInput.getQuickInputDocumentAs(IInvoiceLineQuickInput.class);
    final ProductId productId = ProductId.ofRepoIdOrNull(invoiceLineQuickInput.getM_Product_ID());
    if (productId == null) {
        return;
    }
    final I_C_Invoice invoice = quickInput.getRootDocumentAs(I_C_Invoice.class);
    final Optional<DefaultPackingItemCriteria> defaultPackingItemCriteria = DefaultPackingItemCriteria.of(invoice, productId);
    final I_M_HU_PI_Item_Product huPIItemProduct = defaultPackingItemCriteria.flatMap(packingItemProductFieldHelper::getDefaultPackingMaterial).orElse(null);
    invoiceLineQuickInput.setM_HU_PI_Item_Product(huPIItemProduct);
}


}