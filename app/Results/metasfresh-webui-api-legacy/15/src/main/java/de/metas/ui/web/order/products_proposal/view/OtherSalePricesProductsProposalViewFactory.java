package de.metas.ui.web.order.products_proposal.view;
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
@ViewFactory(windowId = OtherSalePricesProductsProposalViewFactory.WINDOW_ID_STRING)
public class OtherSalePricesProductsProposalViewFactory extends ProductsProposalViewFactoryTemplate{

 public  String WINDOW_ID_STRING;

 public  WindowId WINDOW_ID;

 private  BPartnerProductStatsService bpartnerProductStatsService;

 private  MoneyService moneyService;

 private  BPartnerProductStatsService bpartnerProductStatsService;

 private  MoneyService moneyService;

 private  LookupDataSource bpartnerLookup;

 private  LookupDataSource productLookup;

 private  DocumentIdIntSequence nextRowIdSequence;

 private  BPartnerId excludeBPartnerId;

 private  Set<ProductId> productIds;


@Override
public ProductsProposalRowsLoader createRowsLoaderFromRecord(TableRecordReference recordRef){
    throw new UnsupportedOperationException();
}


public ProductsProposalRowsData load(){
    final List<ProductsProposalRow> rows = bpartnerProductStatsService.getByProductIds(productIds).stream().map(this::toProductsProposalRowOrNull).filter(Objects::nonNull).sorted(Comparator.comparing(ProductsProposalRow::getProductName).thenComparing(Comparator.comparing(ProductsProposalRow::getLastSalesInvoiceDate)).reversed()).collect(ImmutableList.toImmutableList());
    return ProductsProposalRowsData.builder().nextRowIdSequence(nextRowIdSequence).rows(rows).soTrx(SOTrx.SALES).build();
}


@Override
public List<RelatedProcessDescriptor> getRelatedProcessDescriptors(){
    return ImmutableList.of(createProcessDescriptor(WEBUI_ProductsProposal_AddProductFromBasePriceList.class));
}


@Override
public ViewLayout createViewLayout(ViewLayoutKey key){
    return ViewLayout.builder().setWindowId(key.getWindowId()).setCaption(getProcessCaption(WEBUI_ProductsProposal_ShowProductsSoldToOtherCustomers.class).orElse(null)).allowViewCloseAction(ViewCloseAction.BACK).addElementsFromViewRowClassAndFieldNames(ProductsProposalRow.class, key.getViewDataType(), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_BPartner), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_Product), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_Price), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_Currency), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_LastSalesInvoiceDate)).build();
}


public ProductsProposalView createView(ProductsProposalView parentView,List<ProductsProposalRow> selectedRows){
    final ImmutableSet<ProductId> productIds = selectedRows.stream().map(ProductsProposalRow::getProductId).collect(ImmutableSet.toImmutableSet());
    final ProductsProposalRowsData rowsData = RowsLoader.builder().bpartnerProductStatsService(bpartnerProductStatsService).moneyService(moneyService).excludeBPartnerId(parentView.getBpartnerId().orElse(null)).productIds(productIds).build().load();
    final ProductsProposalView view = ProductsProposalView.builder().windowId(getWindowId()).rowsData(rowsData).processes(getRelatedProcessDescriptors()).initialViewId(parentView.getInitialViewId()).build();
    put(view);
    return view;
}


public ProductsProposalRow toProductsProposalRowOrNull(BPartnerProductStats stats){
    final LastInvoiceInfo lastSalesInvoice = stats.getLastSalesInvoice();
    if (lastSalesInvoice == null) {
        return null;
    }
    return ProductsProposalRow.builder().id(nextRowIdSequence.nextDocumentId()).bpartner(bpartnerLookup.findById(stats.getBpartnerId())).product(productLookup.findById(stats.getProductId())).price(ProductProposalPrice.builder().priceListPrice(moneyService.toAmount(lastSalesInvoice.getPrice())).build()).lastShipmentDays(stats.getLastShipmentInDays()).lastSalesInvoiceDate(lastSalesInvoice.getInvoiceDate()).build();
}


}