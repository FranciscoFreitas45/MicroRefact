package de.metas.ui.web.pickingslotsClearing;
 import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.bpartner.BPartnerId;
import de.metas.handlingunits.IHUQueryBuilder;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.process.IADProcessDAO;
import de.metas.process.JavaProcess;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.handlingunits.DefaultHUEditorViewFactory;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.handlingunits.HUIdsFilterHelper;
import de.metas.ui.web.handlingunits.process.WEBUI_M_HU_Transform;
import de.metas.ui.web.pickingslotsClearing.process.WEBUI_PackingHUsView_AddHUsToShipperTransportation;
import de.metas.ui.web.pickingslotsClearing.process.WEBUI_PackingHUsView_AddHUsToShipperTransportationShipAndInvoice;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.IViewsIndexStorage;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Services;
import lombok.NonNull;
@ViewFactory(windowId = PackingHUsViewFactory.WINDOW_ID_STRING)
public class PackingHUsViewFactory implements IViewFactory,IViewsIndexStorage{

 static  String WINDOW_ID_STRING;

 static  WindowId WINDOW_ID;

 private  IADProcessDAO adProcessDAO;

@Autowired
 private  DefaultHUEditorViewFactory huEditorViewFactory;

 private  IViewsRepository viewsRepo;


@Override
public IView getByIdOrNull(ViewId packingHUsViewId){
    return getOrCreatePackingHUsView(packingHUsViewId);
}


public HUEditorView getOrCreatePackingHUsView(ViewId packingHUsViewId){
    final PickingSlotsClearingView pickingSlotsClearingView = getPickingSlotsClearingView(packingHUsViewId);
    return pickingSlotsClearingView.computePackingHUsViewIfAbsent(packingHUsViewId, this::createPackingHUsView);
}


public HUEditorView createPackingHUsView(PackingHUsViewKey key){
    final IHUQueryBuilder huQuery = createHUQuery(key);
    final ViewId packingHUsViewId = key.getPackingHUsViewId();
    final ViewId pickingSlotsClearingViewId = key.getPickingSlotsClearingViewId();
    final CreateViewRequest request = CreateViewRequest.builder(packingHUsViewId, JSONViewDataType.includedView).setParentViewId(pickingSlotsClearingViewId).addStickyFilters(HUIdsFilterHelper.createFilter(huQuery)).addAdditionalRelatedProcessDescriptor(createProcessDescriptor(WEBUI_PackingHUsView_AddHUsToShipperTransportation.class)).addAdditionalRelatedProcessDescriptor(createProcessDescriptor(WEBUI_PackingHUsView_AddHUsToShipperTransportationShipAndInvoice.class)).setParameter(WEBUI_M_HU_Transform.PARAM_CheckExistingHUsInsideView, true).build();
    return huEditorViewFactory.createView(request);
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    return huEditorViewFactory.getViewLayout(windowId, viewDataType, profileId);
}


@Override
public void put(IView view){
    throw new UnsupportedOperationException();
}


public RelatedProcessDescriptor createProcessDescriptor(Class<? extends JavaProcess> processClass){
    return RelatedProcessDescriptor.builder().processId(adProcessDAO.retrieveProcessIdByClass(processClass)).anyTable().anyWindow().displayPlace(DisplayPlace.ViewQuickActions).build();
}


@Override
public void invalidateView(ViewId packingHUsViewId){
    getPackingHUsViewIfExists(packingHUsViewId).ifPresent(packingHUsView -> packingHUsView.invalidateAll());
}


public IHUQueryBuilder createHUQuery(PackingHUsViewKey key){
    final IHUQueryBuilder huQuery = Services.get(IHandlingUnitsDAO.class).createHUQueryBuilder().setIncludeAfterPickingLocator(true).setExcludeHUsOnPickingSlot(true).onlyNotLocked();
    if (key.getBpartnerId() > 0) {
        huQuery.addOnlyInBPartnerId(BPartnerId.ofRepoId(key.getBpartnerId()));
    }
    if (key.getBpartnerLocationId() > 0) {
        huQuery.addOnlyWithBPartnerLocationId(key.getBpartnerLocationId());
    }
    return huQuery;
}


public Optional<HUEditorView> getPackingHUsViewIfExists(ViewId packingHUsViewId){
    final PickingSlotsClearingView pickingSlotsClearingView = getPickingSlotsClearingView(packingHUsViewId);
    return pickingSlotsClearingView.getPackingHUsViewIfExists(packingHUsViewId);
}


@Override
public void setViewsRepository(IViewsRepository viewsRepository){
    this.viewsRepo = viewsRepository;
}


@Override
// shall not be called directly
@Deprecated
public HUEditorView createView(CreateViewRequest request){
    throw new UnsupportedOperationException();
}


@Override
public Stream<IView> streamAllViews(){
    return Stream.empty();
}


@Override
public WindowId getWindowId(){
    return WINDOW_ID;
}


@Override
public void closeById(ViewId packingHUsViewId,ViewCloseAction closeAction){
    final PickingSlotsClearingView pickingSlotsClearingView = getPickingSlotsClearingView(packingHUsViewId);
    pickingSlotsClearingView.closePackingHUsView(packingHUsViewId, closeAction);
}


public PickingSlotsClearingView getPickingSlotsClearingView(ViewId packingHUsViewId){
    final ViewId pickingSlotsClearingViewId = PackingHUsViewKey.extractPickingSlotClearingViewId(packingHUsViewId);
    final PickingSlotsClearingView pickingSlotsClearingView = viewsRepo.getView(pickingSlotsClearingViewId, PickingSlotsClearingView.class);
    return pickingSlotsClearingView;
}


}