package de.metas.ui.web.pickingslotsClearing;
 import java.util.List;
import javax.annotation.Nullable;
import org.adempiere.ad.window.api.IADWindowDAO;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableList;
import de.metas.bpartner.BPartnerId;
import de.metas.cache.CCache;
import de.metas.picking.api.PickingSlotQuery;
import de.metas.picking.api.PickingSlotQuery.PickingSlotQueryBuilder;
import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.ui.web.picking.pickingslot.PickingSlotViewRepository;
import de.metas.ui.web.pickingslotsClearing.process.WEBUI_PickingSlotsClearingView_TakeOutCUsAndAddToTU;
import de.metas.ui.web.pickingslotsClearing.process.WEBUI_PickingSlotsClearingView_TakeOutHU;
import de.metas.ui.web.pickingslotsClearing.process.WEBUI_PickingSlotsClearingView_TakeOutHUAndAddToNewHU;
import de.metas.ui.web.pickingslotsClearing.process.WEBUI_PickingSlotsClearingView_TakeOutMultiHUsAndAddToNewHU;
import de.metas.ui.web.pickingslotsClearing.process.WEBUI_PickingSlotsClearingView_TakeOutTUAndAddToLU;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.IncludedViewLayout;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
@ViewFactory(windowId = PickingSlotsClearingViewFactory.WINDOW_ID_STRING)
public class PickingSlotsClearingViewFactory implements IViewFactory{

 static  String WINDOW_ID_STRING;

 public  WindowId WINDOW_ID;

 private  IADProcessDAO adProcessDAO;

@Autowired
 private  PickingSlotViewRepository pickingSlotRepo;

 private  CCache<Integer,DocumentFilterDescriptorsProvider> filterDescriptorsProviderCache;


public PickingSlotQuery createPickingSlotQuery(DocumentFilterList filters){
    final PickingSlotQueryBuilder queryBuilder = PickingSlotQuery.builder();
    final BPartnerId bpartnerId = PickingSlotsClearingViewFilters.getBPartnerId(filters);
    if (bpartnerId != null) {
        queryBuilder.assignedToBPartnerId(bpartnerId);
    }
    final String barcode = PickingSlotsClearingViewFilters.getPickingSlotBarcode(filters);
    if (!Check.isEmpty(barcode, true)) {
        queryBuilder.barcode(barcode);
    }
    return queryBuilder.build();
}


public List<RelatedProcessDescriptor> createAdditionalRelatedProcessDescriptors(){
    // TODO: cache it
    return ImmutableList.of(createProcessDescriptor(WEBUI_PickingSlotsClearingView_TakeOutHU.class), createProcessDescriptor(WEBUI_PickingSlotsClearingView_TakeOutCUsAndAddToTU.class), createProcessDescriptor(WEBUI_PickingSlotsClearingView_TakeOutTUAndAddToLU.class), createProcessDescriptor(WEBUI_PickingSlotsClearingView_TakeOutHUAndAddToNewHU.class), createProcessDescriptor(WEBUI_PickingSlotsClearingView_TakeOutMultiHUsAndAddToNewHU.class), createProcessDescriptor(de.metas.ui.web.process.adprocess.WEBUI_TestParentChildViewParams.class));
}


@Override
public PickingSlotsClearingView createView(CreateViewRequest request){
    request.assertNoParentViewOrRow();
    final DocumentFilterDescriptorsProvider filterDescriptors = getFilterDescriptorsProvider();
    final DocumentFilterList filters = request.getFiltersUnwrapped(filterDescriptors);
    final ViewId viewId = ViewId.random(PickingSlotsClearingViewFactory.WINDOW_ID);
    final PickingSlotQuery query = createPickingSlotQuery(filters);
    return PickingSlotsClearingView.builder().viewId(viewId).rows(() -> pickingSlotRepo.retrievePickingSlotsRows(query)).additionalRelatedProcessDescriptors(createAdditionalRelatedProcessDescriptors()).filterDescriptors(filterDescriptors).filters(filters).build();
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    // TODO: cache it
    return ViewLayout.builder().setWindowId(WINDOW_ID).setCaption(Services.get(IADWindowDAO.class).retrieveWindowName(WINDOW_ID.toAdWindowId())).addElementsFromViewRowClass(PickingSlotRow.class, viewDataType).setHasTreeSupport(true).setFilters(getFilterDescriptorsProvider().getAll()).setIncludedViewLayout(IncludedViewLayout.builder().openOnSelect(true).blurWhenOpen(false).build()).build();
}


public DocumentFilterDescriptorsProvider getFilterDescriptorsProvider(){
    return filterDescriptorsProviderCache.getOrLoad(0, () -> PickingSlotsClearingViewFilters.createFilterDescriptorsProvider());
}


public RelatedProcessDescriptor createProcessDescriptor(Class<?> processClass){
    return RelatedProcessDescriptor.builder().processId(adProcessDAO.retrieveProcessIdByClass(processClass)).anyTable().anyWindow().displayPlace(DisplayPlace.ViewQuickActions).build();
}


}