package de.metas.vertical.cables.webui.quickinput;
 import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.mm.attributes.api.IAttributeSetInstanceBL;
import org.adempiere.mm.attributes.api.ImmutableAttributeSet;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.I_M_AttributeSetInstance;
import org.eevolution.api.IProductBOMDAO;
import org.eevolution.model.I_PP_Product_BOM;
import com.google.common.collect.ImmutableSet;
import de.metas.adempiere.callout.OrderFastInput;
import de.metas.adempiere.model.I_C_Order;
import de.metas.product.ProductId;
import de.metas.ui.web.quickinput.IQuickInputProcessor;
import de.metas.ui.web.quickinput.QuickInput;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.Services;
import de.metas.vertical.cables.CablesConstants;
public class CableSalesOrderLineQuickInputProcessor implements IQuickInputProcessor{

 private  IAttributeSetInstanceBL asiBL;


public void updateOrderLine_CableProduct(I_C_OrderLine newOrderLine,ICablesOrderLineQuickInput fromQuickInputModel){
    final ProductId productId = getBOMProductId(fromQuickInputModel);
    final BigDecimal cableLength = fromQuickInputModel.getCableLength();
    if (cableLength.signum() <= 0) {
        throw new FillMandatoryException(ICablesOrderLineQuickInput.COLUMNNAME_CableLength);
    }
    final BigDecimal qty = fromQuickInputModel.getQty();
    if (qty.signum() <= 0) {
        throw new FillMandatoryException(ICablesOrderLineQuickInput.COLUMNNAME_Qty);
    }
    final I_M_AttributeSetInstance asi = asiBL.createASIWithASFromProductAndInsertAttributeSet(productId, ImmutableAttributeSet.builder().attributeValue(CablesConstants.ATTRIBUTE_CableLength, cableLength).build());
    newOrderLine.setM_Product_ID(productId.getRepoId());
    newOrderLine.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
    newOrderLine.setQtyEntered(qty);
}


@Override
public Set<DocumentId> process(QuickInput quickInput){
    final I_C_Order order = quickInput.getRootDocumentAs(I_C_Order.class);
    final Properties ctx = InterfaceWrapperHelper.getCtx(order);
    final I_C_OrderLine newOrderLine = OrderFastInput.addOrderLine(ctx, order, orderLine -> updateOrderLine(orderLine, quickInput));
    final int newOrderLineId = newOrderLine.getC_OrderLine_ID();
    final DocumentId documentId = DocumentId.of(newOrderLineId);
    return ImmutableSet.of(documentId);
}


public void updateOrderLine_RegularProduct(I_C_OrderLine newOrderLine,ICablesOrderLineQuickInput fromQuickInputModel){
    if (fromQuickInputModel.getPlug1_Product_ID() <= 0) {
        throw new FillMandatoryException(ICablesOrderLineQuickInput.COLUMNNAME_Plug1_Product_ID);
    }
    final ProductId productId = ProductId.ofRepoId(fromQuickInputModel.getPlug1_Product_ID());
    final BigDecimal qty = fromQuickInputModel.getQty();
    if (qty.signum() <= 0) {
        throw new FillMandatoryException(ICablesOrderLineQuickInput.COLUMNNAME_Qty);
    }
    newOrderLine.setM_Product_ID(productId.getRepoId());
    newOrderLine.setQtyEntered(qty);
}


public void updateOrderLine(I_C_OrderLine newOrderLine,QuickInput fromQuickInput){
    final ICablesOrderLineQuickInput fromQuickInputModel = fromQuickInput.getQuickInputDocumentAs(ICablesOrderLineQuickInput.class);
    if (isCableProduct(fromQuickInputModel)) {
        updateOrderLine_CableProduct(newOrderLine, fromQuickInputModel);
    } else {
        updateOrderLine_RegularProduct(newOrderLine, fromQuickInputModel);
    }
}


public ProductId getBOMProductId(ICablesOrderLineQuickInput quickInputModel){
    final ProductId plugProduct1Id = ProductId.ofRepoId(quickInputModel.getPlug1_Product_ID());
    final ProductId plugProduct2Id = ProductId.ofRepoId(quickInputModel.getPlug2_Product_ID());
    final ProductId cableProductId = ProductId.ofRepoId(quickInputModel.getCable_Product_ID());
    final IProductBOMDAO productBOMsRepo = Services.get(IProductBOMDAO.class);
    final List<I_PP_Product_BOM> boms = productBOMsRepo.retrieveBOMsContainingExactProducts(plugProduct1Id.getRepoId(), cableProductId.getRepoId(), plugProduct2Id.getRepoId());
    if (boms.isEmpty()) {
        throw new AdempiereException("@NotFound@ @PP_Product_BOM_ID@");
    } else if (boms.size() > 1) {
        final String bomValues = boms.stream().map(I_PP_Product_BOM::getValue).collect(Collectors.joining(", "));
        throw new AdempiereException("More than one BOMs found: " + bomValues);
    } else {
        final I_PP_Product_BOM bom = boms.get(0);
        return ProductId.ofRepoId(bom.getM_Product_ID());
    }
}


public boolean isCableProduct(ICablesOrderLineQuickInput quickInputModel){
    return quickInputModel.getPlug1_Product_ID() > 0 && quickInputModel.getCable_Product_ID() > 0 && quickInputModel.getPlug2_Product_ID() > 0;
}


}