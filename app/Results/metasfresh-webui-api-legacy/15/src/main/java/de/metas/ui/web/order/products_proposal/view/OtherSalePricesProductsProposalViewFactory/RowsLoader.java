package de.metas.ui.web.order.products_proposal.view.OtherSalePricesProductsProposalViewFactory;
 import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_M_Product;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.product.stats.BPartnerProductStats;
import de.metas.bpartner.product.stats.BPartnerProductStats.LastInvoiceInfo;
import de.metas.bpartner.product.stats.BPartnerProductStatsService;
import de.metas.lang.SOTrx;
import de.metas.money.MoneyService;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.product.ProductId;
import de.metas.ui.web.order.products_proposal.model.ProductProposalPrice;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRow;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowsData;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowsLoader;
import de.metas.ui.web.order.products_proposal.process.WEBUI_ProductsProposal_AddProductFromBasePriceList;
import de.metas.ui.web.order.products_proposal.process.WEBUI_ProductsProposal_ShowProductsSoldToOtherCustomers;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper.ClassViewColumnOverrides;
import de.metas.ui.web.window.datatypes.DocumentIdIntSequence;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
public class RowsLoader {

 private  BPartnerProductStatsService bpartnerProductStatsService;

 private  MoneyService moneyService;

 private  LookupDataSource bpartnerLookup;

 private  LookupDataSource productLookup;

 private  DocumentIdIntSequence nextRowIdSequence;

 private  BPartnerId excludeBPartnerId;

 private  Set<ProductId> productIds;


public ProductsProposalRowsData load(){
    final List<ProductsProposalRow> rows = bpartnerProductStatsService.getByProductIds(productIds).stream().map(this::toProductsProposalRowOrNull).filter(Objects::nonNull).sorted(Comparator.comparing(ProductsProposalRow::getProductName).thenComparing(Comparator.comparing(ProductsProposalRow::getLastSalesInvoiceDate)).reversed()).collect(ImmutableList.toImmutableList());
    return ProductsProposalRowsData.builder().nextRowIdSequence(nextRowIdSequence).rows(rows).soTrx(SOTrx.SALES).build();
}


public ProductsProposalRow toProductsProposalRowOrNull(BPartnerProductStats stats){
    final LastInvoiceInfo lastSalesInvoice = stats.getLastSalesInvoice();
    if (lastSalesInvoice == null) {
        return null;
    }
    return ProductsProposalRow.builder().id(nextRowIdSequence.nextDocumentId()).bpartner(bpartnerLookup.findById(stats.getBpartnerId())).product(productLookup.findById(stats.getProductId())).price(ProductProposalPrice.builder().priceListPrice(moneyService.toAmount(lastSalesInvoice.getPrice())).build()).lastShipmentDays(stats.getLastShipmentInDays()).lastSalesInvoiceDate(lastSalesInvoice.getInvoiceDate()).build();
}


}