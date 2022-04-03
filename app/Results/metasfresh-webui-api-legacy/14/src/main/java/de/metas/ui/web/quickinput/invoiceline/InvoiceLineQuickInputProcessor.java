package de.metas.ui.web.quickinput.invoiceline;
 import com.google.common.collect.ImmutableSet;
import de.metas.adempiere.gui.search.IHUPackingAwareBL;
import de.metas.adempiere.gui.search.impl.InvoiceLineHUPackingAware;
import de.metas.adempiere.gui.search.impl.PlainHUPackingAware;
import de.metas.adempiere.model.I_C_InvoiceLine;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner_product.IBPartnerProductBL;
import de.metas.handlingunits.HUPIItemProductId;
import de.metas.invoice.IInvoiceLineBL;
import de.metas.product.ProductId;
import de.metas.ui.web.quickinput.IQuickInputProcessor;
import de.metas.ui.web.quickinput.QuickInput;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.uom.UomId;
import de.metas.util.Services;
import org.adempiere.invoice.service.IInvoiceBL;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_C_Invoice;
import java.util.Set;
public class InvoiceLineQuickInputProcessor implements IQuickInputProcessor{

 private  IHUPackingAwareBL huPackingAwareBL;


@Override
public Set<DocumentId> process(QuickInput quickInput){
    final IInvoiceBL invoiceBL = Services.get(IInvoiceBL.class);
    final IInvoiceLineBL invoiceLineBL = Services.get(IInvoiceLineBL.class);
    final I_C_Invoice invoice = quickInput.getRootDocumentAs(I_C_Invoice.class);
    final IInvoiceLineQuickInput invoiceLineQuickInput = quickInput.getQuickInputDocumentAs(IInvoiceLineQuickInput.class);
    // 3834
    final ProductId productId = ProductId.ofRepoId(invoiceLineQuickInput.getM_Product_ID());
    final BPartnerId partnerId = BPartnerId.ofRepoId(invoice.getC_BPartner_ID());
    Services.get(IBPartnerProductBL.class).assertNotExcludedFromSaleToCustomer(productId, partnerId);
    final I_C_InvoiceLine invoiceLine = InterfaceWrapperHelper.newInstance(I_C_InvoiceLine.class, invoice);
    invoiceLine.setC_Invoice(invoice);
    invoiceBL.setProductAndUOM(invoiceLine, invoiceLineQuickInput.getM_Product_ID());
    final PlainHUPackingAware huPackingAware = new PlainHUPackingAware();
    huPackingAware.setBpartnerId(partnerId);
    huPackingAware.setInDispute(false);
    huPackingAware.setProductId(productId);
    huPackingAware.setUomId(UomId.ofRepoIdOrNull(invoiceLine.getC_UOM_ID()));
    huPackingAware.setPiItemProductId(invoiceLineQuickInput.getM_HU_PI_Item_Product_ID() > 0 ? HUPIItemProductId.ofRepoId(invoiceLineQuickInput.getM_HU_PI_Item_Product_ID()) : HUPIItemProductId.VIRTUAL_HU);
    huPackingAwareBL.computeAndSetQtysForNewHuPackingAware(huPackingAware, invoiceLineQuickInput.getQty());
    huPackingAwareBL.prepareCopyFrom(huPackingAware).overridePartner(false).copyTo(InvoiceLineHUPackingAware.of(invoiceLine));
    invoiceLineBL.updatePrices(invoiceLine);
    // invoiceBL.setLineNetAmt(invoiceLine); // not needed; will be called on save
    // invoiceBL.setTaxAmt(invoiceLine);// not needed; will be called on save
    InterfaceWrapperHelper.save(invoiceLine);
    final DocumentId documentId = DocumentId.of(invoiceLine.getC_InvoiceLine_ID());
    return ImmutableSet.of(documentId);
}


}