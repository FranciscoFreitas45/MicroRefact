package de.metas.ui.web.order.products_proposal.view;
 import java.util.List;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.I_C_Order;
import org.compiere.util.TimeUtil;
import com.google.common.collect.ImmutableList;
import de.metas.bpartner.BPGroupId;
import de.metas.bpartner.product.stats.BPartnerProductStatsService;
import de.metas.bpartner.service.IBPartnerDAO;
import de.metas.i18n.ITranslatableString;
import de.metas.order.OrderId;
import de.metas.pricing.rules.campaign_price.CampaignPriceService;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.order.products_proposal.campaign_price.CampaignPriceProvider;
import de.metas.ui.web.order.products_proposal.campaign_price.CampaignPriceProviders;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRow;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowsLoader;
import de.metas.ui.web.order.products_proposal.process.WEBUI_Order_ProductsProposal_Launcher;
import de.metas.ui.web.order.products_proposal.process.WEBUI_ProductsProposal_Delete;
import de.metas.ui.web.order.products_proposal.process.WEBUI_ProductsProposal_SaveProductPriceToCurrentPriceListVersion;
import de.metas.ui.web.order.products_proposal.process.WEBUI_ProductsProposal_ShowProductsSoldToOtherCustomers;
import de.metas.ui.web.order.products_proposal.process.WEBUI_ProductsProposal_ShowProductsToAddFromBasePriceList;
import de.metas.ui.web.order.products_proposal.service.Order;
import de.metas.ui.web.order.products_proposal.service.OrderLinesFromProductProposalsProducer;
import de.metas.ui.web.order.products_proposal.service.OrderProductProposalsService;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper.ClassViewColumnOverrides;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Services;
import lombok.NonNull;
@ViewFactory(windowId = OrderProductsProposalViewFactory.WINDOW_ID_STRING)
public class OrderProductsProposalViewFactory extends ProductsProposalViewFactoryTemplate{

 static  String WINDOW_ID_STRING;

 public  WindowId WINDOW_ID;

 private  IBPartnerDAO bpartnersRepo;

 private  OrderProductProposalsService orderProductProposalsService;

 private  BPartnerProductStatsService bpartnerProductStatsService;

 private  CampaignPriceService campaignPriceService;


@Override
public ProductsProposalRowsLoader createRowsLoaderFromRecord(TableRecordReference recordRef){
    recordRef.assertTableName(I_C_Order.Table_Name);
    final OrderId orderId = OrderId.ofRepoId(recordRef.getRecord_ID());
    return createRowsLoaderFromOrderId(orderId);
}


@Override
public List<RelatedProcessDescriptor> getRelatedProcessDescriptors(){
    return ImmutableList.of(createProcessDescriptor(WEBUI_ProductsProposal_SaveProductPriceToCurrentPriceListVersion.class), createProcessDescriptor(WEBUI_ProductsProposal_ShowProductsToAddFromBasePriceList.class), createProcessDescriptor(WEBUI_ProductsProposal_ShowProductsSoldToOtherCustomers.class), createProcessDescriptor(WEBUI_ProductsProposal_Delete.class));
}


@Override
public void beforeViewClose(ViewId viewId,ViewCloseAction closeAction){
    if (ViewCloseAction.DONE.equals(closeAction)) {
        final ProductsProposalView view = getById(viewId);
        if (view.getOrderId() != null) {
            createOrderLines(view);
        }
    }
}


public void createOrderLines(ProductsProposalView view){
    OrderLinesFromProductProposalsProducer.builder().orderId(view.getOrderId().get()).rows(view.getAllRows()).build().produce();
}


@Override
public ViewLayout createViewLayout(ViewLayoutKey key){
    final ITranslatableString caption = getProcessCaption(WEBUI_Order_ProductsProposal_Launcher.class).orElse(null);
    return ViewLayout.builder().setWindowId(key.getWindowId()).setCaption(caption).allowViewCloseAction(ViewCloseAction.CANCEL).allowViewCloseAction(ViewCloseAction.DONE).setFocusOnFieldName(ProductsProposalRow.FIELD_Qty).addElementsFromViewRowClassAndFieldNames(ProductsProposalRow.class, key.getViewDataType(), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_Product), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_Qty), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_PackDescription), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_ASI), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_LastShipmentDays), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_Price), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_Currency), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_IsCampaignPrice), ClassViewColumnOverrides.ofFieldName(ProductsProposalRow.FIELD_Description)).build();
}


public CampaignPriceProvider createCampaignPriceProvider(Order order){
    if (!order.getSoTrx().isSales()) {
        return CampaignPriceProviders.none();
    }
    if (order.getCountryId() == null) {
        return CampaignPriceProviders.none();
    }
    if (!bpartnersRepo.isCampaignPriceAllowed(order.getBpartnerId())) {
        return CampaignPriceProviders.none();
    }
    final BPGroupId bpGroupId = bpartnersRepo.getBPGroupIdByBPartnerId(order.getBpartnerId());
    return CampaignPriceProviders.standard().campaignPriceService(campaignPriceService).bpartnerId(order.getBpartnerId()).bpGroupId(bpGroupId).pricingSystemId(order.getPricingSystemId()).countryId(order.getCountryId()).currencyId(order.getCurrencyId()).date(TimeUtil.asLocalDate(order.getDatePromised())).build();
}


public ProductsProposalRowsLoader createRowsLoaderFromOrderId(OrderId orderId){
    final Order order = orderProductProposalsService.getOrderById(orderId);
    final CampaignPriceProvider campaignPriceProvider = createCampaignPriceProvider(order);
    return ProductsProposalRowsLoader.builder().bpartnerProductStatsService(bpartnerProductStatsService).campaignPriceProvider(campaignPriceProvider).priceListVersionId(order.getPriceListVersionId()).order(order).bpartnerId(order.getBpartnerId()).soTrx(order.getSoTrx()).build();
}


}