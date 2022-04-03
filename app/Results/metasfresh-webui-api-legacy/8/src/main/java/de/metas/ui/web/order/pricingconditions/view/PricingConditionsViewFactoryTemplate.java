package de.metas.ui.web.order.pricingconditions.view;
 import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.metas.cache.CCache;
import de.metas.i18n.ITranslatableString;
import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.order.pricingconditions.process.PricingConditionsView_CopyRowToEditable;
import de.metas.ui.web.order.pricingconditions.process.PricingConditionsView_SaveEditableRow;
import de.metas.ui.web.order.pricingconditions.process.WEBUI_SalesOrder_PricingConditionsView_Launcher;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRowsLoader.PricingConditionsRowsLoaderBuilder;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.IViewsIndexStorage;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Services;
import lombok.NonNull;
public class PricingConditionsViewFactoryTemplate implements IViewFactory,IViewsIndexStorage{

 private  WindowId windowId;

 private  CCache<ViewLayoutKey,ViewLayout> viewLayoutCache;

 private  Cache<ViewId,PricingConditionsView> views;

 private  PricingConditionsRowLookups lookups;

 private  PricingConditionsViewFilters filtersFactory;

 private WindowId windowId;

 private JSONViewDataType viewDataType;


public DocumentFilterList extractFilters(CreateViewRequest request){
    return filtersFactory.extractFilters(request);
}


@Override
public PricingConditionsView getByIdOrNull(ViewId viewId){
    return views.getIfPresent(viewId);
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    final ViewLayoutKey key = ViewLayoutKey.of(windowId, viewDataType);
    return viewLayoutCache.getOrLoad(key, this::createViewLayout);
}


@Override
public void put(IView view){
    views.put(view.getViewId(), PricingConditionsView.cast(view));
}


public PricingConditionsRowsLoaderBuilder preparePricingConditionsRowData(){
    return PricingConditionsRowsLoader.builder().lookups(lookups);
}


public RelatedProcessDescriptor createProcessDescriptor(Class<?> processClass){
    final IADProcessDAO adProcessDAO = Services.get(IADProcessDAO.class);
    return RelatedProcessDescriptor.builder().processId(adProcessDAO.retrieveProcessIdByClass(processClass)).anyTable().anyWindow().displayPlace(DisplayPlace.ViewQuickActions).build();
}


public PricingConditionsRowData createPricingConditionsRowData(CreateViewRequest request)


@Override
public PricingConditionsView filterView(IView view,JSONFilterViewRequest filterViewRequest,Supplier<IViewsRepository> viewsRepo_IGNORED){
    return PricingConditionsView.cast(view).filter(filtersFactory.extractFilters(filterViewRequest));
}


public PricingConditionsView getById(ViewId viewId){
    final PricingConditionsView view = getById(viewId);
    if (view == null) {
        throw new EntityNotFoundException("View not found: " + viewId.toJson());
    }
    return view;
}


@Override
public void invalidateView(ViewId viewId){
    final PricingConditionsView view = getByIdOrNull(viewId);
    if (view == null) {
        return;
    }
    view.invalidateAll();
}


@Override
public void setViewsRepository(IViewsRepository viewsRepository){
}


public ViewLayout createViewLayout(ViewLayoutKey key){
    final ITranslatableString caption = Services.get(IADProcessDAO.class).retrieveProcessNameByClassIfUnique(WEBUI_SalesOrder_PricingConditionsView_Launcher.class).orElse(null);
    return ViewLayout.builder().setWindowId(key.getWindowId()).setCaption(caption).setFilters(filtersFactory.getFilterDescriptors()).addElementsFromViewRowClass(PricingConditionsRow.class, key.getViewDataType()).build();
}


public PricingConditionsView createView(PricingConditionsRowData rowsData){
    return PricingConditionsView.builder().viewId(ViewId.random(windowId)).rowsData(rowsData).relatedProcessDescriptor(createProcessDescriptor(PricingConditionsView_CopyRowToEditable.class)).relatedProcessDescriptor(createProcessDescriptor(PricingConditionsView_SaveEditableRow.class)).filterDescriptors(filtersFactory.getFilterDescriptorsProvider()).build();
}


public void onViewClosedByUser(PricingConditionsView view){
// nothing on this level
}


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
    final PricingConditionsView view = views.getIfPresent(viewId);
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