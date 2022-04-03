package de.metas.ui.web.order.sales.purchasePlanning.view;
 import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.process.AdProcessId;
import de.metas.process.IADProcessDAO;
import de.metas.process.JavaProcess;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.purchasecandidate.PurchaseDemand;
import de.metas.purchasecandidate.PurchaseDemandWithCandidates;
import de.metas.purchasecandidate.PurchaseDemandWithCandidatesService;
import de.metas.purchasecandidate.availability.AvailabilityCheckService;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.IViewsIndexStorage;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Services;
import lombok.NonNull;
public class PurchaseViewFactoryTemplate implements IViewFactory,IViewsIndexStorage{

 private  PurchaseDemandWithCandidatesService purchaseDemandWithCandidatesService;

 private  AvailabilityCheckService availabilityCheckService;

 private  PurchaseRowFactory purchaseRowFactory;

 private  PurchaseViewLayoutFactory viewLayoutFactory;

 private  IADProcessDAO adProcessRepo;

 private  WindowId windowId;

 private  Cache<ViewId,PurchaseView> views;


@Override
public PurchaseView getByIdOrNull(ViewId viewId){
    return views.getIfPresent(viewId);
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    return viewLayoutFactory.getViewLayout(windowId, viewDataType);
}


@Override
public void put(IView view){
    views.put(view.getViewId(), PurchaseView.cast(view));
}


public RelatedProcessDescriptor createProcessDescriptor(Class<?> processClass){
    final AdProcessId processId = adProcessRepo.retrieveProcessIdByClassIfUnique(processClass);
    Preconditions.checkArgument(processId != null, "No AD_Process_ID found for %s", processClass);
    return RelatedProcessDescriptor.builder().processId(processId).displayPlace(DisplayPlace.ViewQuickActions).build();
}


public List<PurchaseDemand> getDemands(CreateViewRequest request)


public List<RelatedProcessDescriptor> getAdditionalProcessDescriptors(){
    return ImmutableList.of();
}


public PurchaseView getById(ViewId viewId){
    final PurchaseView view = getByIdOrNull(viewId);
    if (view == null) {
        throw new EntityNotFoundException("View " + viewId + " was not found");
    }
    return view;
}


@Override
public void invalidateView(ViewId viewId){
    final IView view = getByIdOrNull(viewId);
    if (view == null) {
        return;
    }
    view.invalidateAll();
}


public PurchaseRowsSupplier createRowsSupplier(ViewId viewId,List<PurchaseDemandWithCandidates> purchaseDemandWithCandidatesList){
    final PurchaseRowsSupplier rowsSupplier = PurchaseRowsLoader.builder().purchaseDemandWithCandidatesList(purchaseDemandWithCandidatesList).viewSupplier(// needed for async stuff
    () -> getByIdOrNull(viewId)).purchaseRowFactory(purchaseRowFactory).availabilityCheckService(availabilityCheckService).build().createPurchaseRowsSupplier();
    return rowsSupplier;
}


@Override
public void setViewsRepository(IViewsRepository viewsRepository){
}


public ViewId newViewId(){
    return ViewId.random(getWindowId());
}


@Override
public PurchaseView createView(CreateViewRequest request){
    final ViewId viewId = newViewId();
    final List<PurchaseDemand> demands = getDemands(request);
    final List<PurchaseDemandWithCandidates> purchaseDemandWithCandidatesList = purchaseDemandWithCandidatesService.getOrCreatePurchaseCandidatesGroups(demands);
    final PurchaseRowsSupplier rowsSupplier = createRowsSupplier(viewId, purchaseDemandWithCandidatesList);
    final PurchaseView view = PurchaseView.builder().viewId(viewId).rowsSupplier(rowsSupplier).additionalRelatedProcessDescriptors(getAdditionalProcessDescriptors()).build();
    return view;
}


public void onViewClosedByUser(PurchaseView purchaseView)


@Override
public Stream<IView> streamAllViews(){
    return Stream.empty();
}


@Override
public WindowId getWindowId(){
    return windowId;
}


@Override
public void closeById(ViewId viewId,ViewCloseAction closeAction){
    final PurchaseView view = views.getIfPresent(viewId);
    if (view == null || !view.isAllowClosingPerUserRequest()) {
        return;
    }
    if (closeAction.isDone()) {
        onViewClosedByUser(view);
    }
    views.invalidate(viewId);
    // also cleanup to prevent views cache to grow.
    views.cleanUp();
}


}