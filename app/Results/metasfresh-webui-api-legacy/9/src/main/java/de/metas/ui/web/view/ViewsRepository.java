package de.metas.ui.web.view;
 import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Streams;
import de.metas.logging.LogManager;
import de.metas.ui.web.base.model.I_T_WEBUI_ViewSelection;
import de.metas.ui.web.base.model.I_T_WEBUI_ViewSelectionLine;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.menu.MenuNode;
import de.metas.ui.web.menu.MenuTreeRepository;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.event.ViewChangesCollector;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.controller.DocumentPermissionsHelper;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.service.ISysConfigBL;
import org.adempiere.util.lang.IAutoCloseable;
import org.adempiere.util.lang.MutableInt;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.Adempiere;
import org.compiere.util.DB;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
@Repository
public class ViewsRepository implements IViewsRepository{

 private  Logger logger;

 private  ImmutableMap<ViewFactoryKey,IViewFactory> factories;

 private  SqlViewFactory defaultFactory;

 private  MenuTreeRepository menuTreeRepo;

@Value("${metasfresh.webui.view.truncateOnStartUp:true}")
 private  boolean truncateSelectionOnStartUp;

 private  ImmutableMap<WindowId,IViewsIndexStorage> viewsIndexStorages;

 private  IViewsIndexStorage defaultViewsIndexStorage;

 private WindowId windowId;

 private JSONViewDataType viewType;


@Override
public void closeView(ViewId viewId,ViewCloseAction closeAction){
    getViewsStorageFor(viewId).closeById(viewId, closeAction);
    logger.trace("Closed/Removed view {} using close action {}", viewId, closeAction);
}


@Override
@Async
public void notifyRecordsChanged(TableRecordReferenceSet recordRefs){
    if (recordRefs.isEmpty()) {
        logger.trace("No changed records provided. Skip notifying views.");
        return;
    }
    try (final IAutoCloseable ignored = ViewChangesCollector.currentOrNewThreadLocalCollector()) {
        final MutableInt notifiedCount = MutableInt.zero();
        streamAllViews().forEach(view -> {
            try {
                view.notifyRecordsChanged(recordRefs);
                notifiedCount.incrementAndGet();
            } catch (final Exception ex) {
                logger.warn("Failed calling notifyRecordsChanged for view={} with recordRefs={}. Ignored.", view, recordRefs, ex);
            }
        });
        logger.debug("Notified {} views about changed records: {}", notifiedCount, recordRefs);
    }
}


public IViewFactory getFactory(WindowId windowId,JSONViewDataType viewType){
    IViewFactory factory = factories.get(ViewFactoryKey.of(windowId, viewType));
    if (factory != null) {
        return factory;
    }
    factory = factories.get(ViewFactoryKey.of(windowId, null));
    if (factory != null) {
        return factory;
    }
    return defaultFactory;
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    // N/A
    final String viewId = null;
    DocumentPermissionsHelper.assertViewAccess(windowId, viewId, UserSession.getCurrentPermissions());
    final IViewFactory factory = getFactory(windowId, viewDataType);
    return factory.getViewLayout(windowId, viewDataType, profileId).withAllowNewRecordIfPresent(menuTreeRepo.getUserSessionMenuTree().getNewRecordNodeForWindowId(windowId).map(MenuNode::getCaption));
}


@Override
public IView filterView(ViewId viewId,JSONFilterViewRequest jsonRequest){
    logger.trace("Creating filtered view from {} using {}", viewId, jsonRequest);
    // Get current view
    final IView view = getView(viewId);
    // 
    // Create the new view
    final IViewFactory factory = getFactory(view.getViewId().getWindowId(), view.getViewType());
    final IView newView = factory.filterView(view, jsonRequest, () -> this);
    if (newView == null) {
        throw new AdempiereException("Failed filtering view").setParameter("viewId", viewId).setParameter("request", jsonRequest).setParameter("factory", factory.toString());
    }
    // 
    // Add the new view to our internal map
    // NOTE: avoid adding if the factory returned the same view.
    if (view != newView) {
        getViewsStorageFor(newView.getViewId()).put(newView);
        logger.trace("Created filtered view {}", newView);
    } else {
        logger.trace("Filtered view is the same as the ordiginal. Returning the original {}", view);
    }
    // Return the newly created view
    return newView;
}


@Override
public IView getViewIfExists(ViewId viewId){
    final IView view = getViewsStorageFor(viewId).getByIdOrNull(viewId);
    if (view == null) {
        throw new EntityNotFoundException("View not found: " + viewId.toJson());
    }
    DocumentPermissionsHelper.assertViewAccess(viewId.getWindowId(), viewId.getViewId(), UserSession.getCurrentPermissions());
    return view;
}


@Override
public List<IView> getViews(){
    return streamAllViews().collect(ImmutableList.toImmutableList());
}


@Override
public IView deleteStickyFilter(ViewId viewId,String filterId){
    logger.trace("Deleting sticky filter {} from {}", filterId, viewId);
    // Get current view
    final IView view = getView(viewId);
    // 
    // Create the new view
    final IViewFactory factory = getFactory(view.getViewId().getWindowId(), view.getViewType());
    final IView newView = factory.deleteStickyFilter(view, filterId);
    if (newView == null) {
        throw new AdempiereException("Failed deleting sticky/static filter").setParameter("viewId", viewId).setParameter("filterId", filterId).setParameter("factory", factory.toString());
    }
    // 
    // Add the new view to our internal map
    // NOTE: avoid adding if the factory returned the same view.
    if (view != newView) {
        getViewsStorageFor(newView.getViewId()).put(newView);
        logger.trace("Sticky filter deleted. Returning new view {}", newView);
    } else {
        logger.trace("Sticky filter NOT deleted. Returning current view {}", view);
    }
    // Return the newly created view
    return newView;
}


@PostConstruct
public void truncateTempTablesIfAllowed(){
    if (truncateSelectionOnStartUp) {
        truncateTable(I_T_WEBUI_ViewSelection.Table_Name);
        truncateTable(I_T_WEBUI_ViewSelectionLine.Table_Name);
    } else {
        logger.info("Skip truncating selection tables on startup because not configured");
    }
}


@Override
public void invalidateView(IView view){
    invalidateView(view.getViewId());
}


public void truncateTable(String tableName){
    final Stopwatch stopwatch = Stopwatch.createStarted();
    try {
        final int no = DB.executeUpdateEx("TRUNCATE TABLE " + tableName, ITrx.TRXNAME_NoneNotNull);
        logger.info("Deleted {} records(all) from table {} (Took: {})", no, tableName, stopwatch);
    } catch (final Exception ex) {
        logger.warn("Failed deleting all from {} (Took: {})", tableName, stopwatch, ex);
    }
}


@Override
public List<ViewProfile> getAvailableProfiles(WindowId windowId,JSONViewDataType viewDataType){
    final IViewFactory factory = getFactory(windowId, viewDataType);
    return factory.getAvailableProfiles(windowId);
}


public ImmutableMap<WindowId,IViewsIndexStorage> createViewIndexStoragesMap(List<IViewsIndexStorage> viewsIndexStorages){
    final ImmutableMap.Builder<WindowId, IViewsIndexStorage> map = ImmutableMap.builder();
    for (final IViewsIndexStorage viewsIndexStorage : viewsIndexStorages) {
        if (viewsIndexStorage instanceof DefaultViewsRepositoryStorage) {
            logger.warn("Skipping {} because it shall not be in spring context", viewsIndexStorage);
            continue;
        }
        final WindowId windowId = viewsIndexStorage.getWindowId();
        Check.assumeNotNull(windowId, "{} shall not have windowId null", viewsIndexStorage);
        map.put(windowId, viewsIndexStorage);
    }
    return map.build();
}


@Override
public IView createView(CreateViewRequest request){
    logger.trace("Creating new view from {}", request);
    final WindowId windowId = request.getViewId().getWindowId();
    final JSONViewDataType viewType = request.getViewType();
    final IViewFactory factory = getFactory(windowId, viewType);
    final IView view = factory.createView(request);
    if (view == null) {
        throw new AdempiereException("Failed creating view").setParameter("request", request).setParameter("factory", factory.toString());
    }
    getViewsStorageFor(view.getViewId()).put(view);
    logger.trace("Created view {}", view);
    return view;
}


@Override
public IView getView(String viewIdStr){
    final ViewId viewId = ViewId.ofViewIdString(viewIdStr);
    final IView view = getViewIfExists(viewId);
    if (view == null) {
        throw new EntityNotFoundException("No view found for viewId=" + viewId);
    }
    return view;
}


public ImmutableMap<ViewFactoryKey,IViewFactory> createFactoriesMap(Collection<IViewFactory> viewFactories){
    final Map<ViewFactoryKey, IViewFactory> factories = new HashMap<>();
    for (final IViewFactory factory : viewFactories) {
        final ViewFactory annotation = factory.getClass().getAnnotation(ViewFactory.class);
        if (annotation == null) {
            // Don't need to warn about this one. It's a known case.
            if (!SqlViewFactory.class.equals(factory.getClass())) {
                // this might be a development bug
                logger.warn("Skip {} because it's not annotated with {}", factory, ViewFactory.class);
            }
            continue;
        }
        final WindowId windowId = WindowId.fromJson(annotation.windowId());
        JSONViewDataType[] viewTypes = annotation.viewTypes();
        if (viewTypes.length == 0) {
            viewTypes = JSONViewDataType.values();
        }
        for (final JSONViewDataType viewType : viewTypes) {
            factories.put(ViewFactoryKey.of(windowId, viewType), factory);
        }
    }
    return ImmutableMap.copyOf(factories);
}


public Stream<IView> streamAllViews(){
    return Streams.concat(viewsIndexStorages.values().stream(), Stream.of(defaultViewsIndexStorage)).flatMap(IViewsIndexStorage::streamAllViews);
}


@Override
public IViewsIndexStorage getViewsStorageFor(ViewId viewId){
    final IViewsIndexStorage viewIndexStorage = viewsIndexStorages.get(viewId.getWindowId());
    if (viewIndexStorage != null) {
        return viewIndexStorage;
    }
    return defaultViewsIndexStorage;
}


}