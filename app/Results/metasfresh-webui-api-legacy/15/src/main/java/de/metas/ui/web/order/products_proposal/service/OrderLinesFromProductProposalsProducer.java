package de.metas.ui.web.order.products_proposal.service;
 import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_OrderLine;
import org.compiere.util.Env;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.adempiere.callout.OrderFastInput;
import de.metas.adempiere.gui.search.HUPackingAwareCopy.ASICopyMode;
import de.metas.adempiere.gui.search.IHUPackingAware;
import de.metas.adempiere.gui.search.IHUPackingAwareBL;
import de.metas.adempiere.gui.search.impl.OrderLineHUPackingAware;
import de.metas.adempiere.gui.search.impl.PlainHUPackingAware;
import de.metas.bpartner.BPartnerId;
import de.metas.logging.LogManager;
import de.metas.order.IOrderDAO;
import de.metas.order.OrderId;
import de.metas.order.OrderLineId;
import de.metas.product.IProductBL;
import de.metas.ui.web.order.products_proposal.model.ProductProposalPrice;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRow;
import de.metas.uom.UomId;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
public class OrderLinesFromProductProposalsProducer {

 private  Logger logger;

 private  ITrxManager trxManager;

 private  IOrderDAO ordersRepo;

 private  IHUPackingAwareBL huPackingAwareBL;

 private  IProductBL productBL;

 private  OrderId orderId;

 private  ImmutableList<ProductsProposalRow> rows;


public PlainHUPackingAware createAndInitHUPackingAware(I_C_Order order,ProductsProposalRow fromRow){
    final PlainHUPackingAware huPackingAware = new PlainHUPackingAware();
    huPackingAware.setBpartnerId(BPartnerId.ofRepoId(order.getC_BPartner_ID()));
    huPackingAware.setInDispute(false);
    final UomId uomId = productBL.getStockUOMId(fromRow.getProductId());
    huPackingAware.setProductId(fromRow.getProductId());
    huPackingAware.setUomId(uomId);
    // huPackingAware.setM_AttributeSetInstance_ID(...);
    huPackingAware.setPiItemProductId(fromRow.getPackingMaterialId());
    return huPackingAware;
}


public void produceInTrx(){
    final Properties ctx = Env.getCtx();
    final I_C_Order order = ordersRepo.getById(orderId);
    final ImmutableMap<OrderLineId, I_C_OrderLine> existingOrderLines = Maps.uniqueIndex(ordersRepo.retrieveOrderLines(orderId, I_C_OrderLine.class), orderLineRecord -> OrderLineId.ofRepoId(orderLineRecord.getC_OrderLine_ID()));
    for (final ProductsProposalRow row : rows) {
        final I_C_OrderLine existingOrderLine = row.getExistingOrderLineId() != null ? existingOrderLines.get(row.getExistingOrderLineId()) : null;
        if (existingOrderLine == null) {
            if (row.isQtySet()) {
                OrderFastInput.addOrderLine(ctx, order, orderLine -> updateOrderLine(order, orderLine, row));
            } else {
            // if qty is not set, don't create the row
            }
        } else {
            if (row.isQtySet()) {
                updateOrderLine(order, existingOrderLine, row);
                ordersRepo.save(existingOrderLine);
            } else {
                ordersRepo.delete(existingOrderLine);
            }
        }
    }
}


public void validateNewHUPackingAware(PlainHUPackingAware huPackingAware){
    if (huPackingAware.getQty() == null || huPackingAware.getQty().signum() <= 0) {
        logger.warn("Invalid Qty={} for {}", huPackingAware.getQty(), huPackingAware);
        // TODO trl
        throw new AdempiereException("Qty shall be greather than zero");
    }
    if (huPackingAware.getQtyTU() == null || huPackingAware.getQtyTU().signum() <= 0) {
        logger.warn("Invalid QtyTU={} for {}", huPackingAware.getQtyTU(), huPackingAware);
        // TODO trl
        throw new AdempiereException("QtyTU shall be greather than zero");
    }
}


public void updateOrderLine(I_C_Order order,I_C_OrderLine newOrderLine,ProductsProposalRow fromRow){
    final IHUPackingAware rowPackingAware = createHUPackingAware(order, fromRow);
    final IHUPackingAware orderLinePackingAware = OrderLineHUPackingAware.of(newOrderLine);
    huPackingAwareBL.prepareCopyFrom(rowPackingAware).overridePartner(false).asiCopyMode(// because we just created the ASI
    ASICopyMode.CopyID).copyTo(orderLinePackingAware);
    final ProductProposalPrice price = fromRow.getPrice();
    // IMPORTANT: manual price is always true because we want to make sure the price the sales guy saw in proposals list is the price which gets into order line
    newOrderLine.setIsManualPrice(true);
    newOrderLine.setPriceEntered(price.getUserEnteredPriceValue());
    newOrderLine.setDescription(fromRow.getDescription());
}


public void produce(){
    if (rows.isEmpty()) {
        return;
    }
    trxManager.runInNewTrx(this::produceInTrx);
}


public IHUPackingAware createHUPackingAware(I_C_Order order,ProductsProposalRow fromRow){
    final PlainHUPackingAware huPackingAware = createAndInitHUPackingAware(order, fromRow);
    final BigDecimal qty = fromRow.getQty();
    if (qty == null || qty.signum() <= 0) {
        // TODO trl
        throw new AdempiereException("Qty shall be greather than zero");
    }
    huPackingAwareBL.computeAndSetQtysForNewHuPackingAware(huPackingAware, qty);
    validateNewHUPackingAware(huPackingAware);
    return huPackingAware;
}


}