package de.metas.ui.web.order.products_proposal.view;
 import java.util.List;
import java.util.function.Supplier;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableList;
import de.metas.bpartner.product.stats.BPartnerProductStatsService;
import de.metas.i18n.ITranslatableString;
import de.metas.logging.LogManager;
import de.metas.pricing.PriceListVersionId;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.order.products_proposal.filters.ProductsProposalViewFilter;
import de.metas.ui.web.order.products_proposal.filters.ProductsProposalViewFilters;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRow;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowsData;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowsLoader;
import de.metas.ui.web.order.products_proposal.process.WEBUI_ProductsProposal_AddProductFromBasePriceList;
import de.metas.ui.web.order.products_proposal.process.WEBUI_ProductsProposal_ShowProductsToAddFromBasePriceList;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
@ViewFactory(windowId = BasePLVProductsProposalViewFactory.WINDOW_ID_STRING)
public class BasePLVProductsProposalViewFactory extends ProductsProposalViewFactoryTemplate{

 private  Logger logger;

 public  String WINDOW_ID_STRING;

 public  WindowId WINDOW_ID;

 private  BPartnerProductStatsService bpartnerProductStatsService;


@Override
public ProductsProposalView filterView(IView view,JSONFilterViewRequest filterViewRequest,Supplier<IViewsRepository> viewsRepo){
    final ProductsProposalView productsProposalView = ProductsProposalView.cast(view);
    final ProductsProposalViewFilter filter = ProductsProposalViewFilters.extractPackageableViewFilterVO(filterViewRequest);
    productsProposalView.filter(filter);
    return productsProposalView;
}


@Override
public ProductsProposalRowsLoader createRowsLoaderFromRecord(TableRecordReference recordRef){
    throw new UnsupportedOperationException();
}


@Override
public List<RelatedProcessDescriptor> getRelatedProcessDescriptors(){
    return ImmutableList.of(createProcessDescriptor(WEBUI_ProductsProposal_AddProductFromBasePriceList.class));
}


@Override
public ViewLayout createViewLayout(ViewLayoutKey key){
    final ITranslatableString caption = getProcessCaption(WEBUI_ProductsProposal_ShowProductsToAddFromBasePriceList.class).orElse(null);
    return ViewLayout.builder().setWindowId(key.getWindowId()).setCaption(caption).allowViewCloseAction(ViewCloseAction.BACK).setFilters(ProductsProposalViewFilters.getDescriptors().getAll()).addElementsFromViewRowClass(ProductsProposalRow.class, key.getViewDataType()).removeElementByFieldName(ProductsProposalRow.FIELD_Qty).build();
}


public ProductsProposalView createView(ProductsProposalView parentView){
    final PriceListVersionId basePriceListVersionId = parentView.getBasePriceListVersionIdOrFail();
    final ProductsProposalRowsData rowsData = ProductsProposalRowsLoader.builder().bpartnerProductStatsService(bpartnerProductStatsService).priceListVersionId(basePriceListVersionId).productIdsToExclude(parentView.getProductIds()).bpartnerId(parentView.getBpartnerId().orElse(null)).soTrx(parentView.getSoTrx()).build().load();
    logger.debug("loaded ProductsProposalRowsData with size={} for basePriceListVersionId={}", basePriceListVersionId, rowsData.size());
    final ProductsProposalView view = ProductsProposalView.builder().windowId(getWindowId()).rowsData(rowsData).processes(getRelatedProcessDescriptors()).initialViewId(parentView.getInitialViewId()).build();
    put(view);
    return view;
}


}