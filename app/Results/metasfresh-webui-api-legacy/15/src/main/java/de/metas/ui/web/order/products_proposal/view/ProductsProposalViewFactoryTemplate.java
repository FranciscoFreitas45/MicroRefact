package de.metas.ui.web.order.products_proposal.view;
 import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.metas.cache.CCache;
import de.metas.i18n.ITranslatableString;
import de.metas.process.AdProcessId;
import de.metas.process.IADProcessDAO;
import de.metas.process.JavaProcess;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowsData;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowsLoader;
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
public class ProductsProposalViewFactoryTemplate implements IViewFactory,IViewsIndexStorage{

 private  String PARAM_RecordRef;

 private  CCache<ViewLayoutKey,ViewLayout> viewLayoutCache;

 private  Cache<ViewId,ProductsProposalView> views;

 private  WindowId windowId;

 private WindowId windowId;

 private JSONViewDataType viewDataType;


@Override
public ProductsProposalView getByIdOrNull(ViewId viewId){
    return views.getIfPresent(viewId);
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    final ViewLayoutKey key = ViewLayoutKey.of(windowId, viewDataType);
    return viewLayoutCache.getOrLoad(key, this::createViewLayout);
}


public ProductsProposalRowsData loadRowsData(CreateViewRequest request){
    final TableRecordReference recordRef = getRecordReference(request);
    return createRowsLoaderFromRecord(recordRef).load();
}


@Override
public void put(IView view){
    views.put(view.getViewId(), ProductsProposalView.cast(view));
}


public RelatedProcessDescriptor createProcessDescriptor(Class<?> processClass){
    final IADProcessDAO adProcessDAO = Services.get(IADProcessDAO.class);
    final AdProcessId processId = adProcessDAO.retrieveProcessIdByClass(processClass);
    if (processId == null) {
        throw new AdempiereException("No processId found for " + processClass);
    }
    return RelatedProcessDescriptor.builder().processId(processId).anyTable().anyWindow().displayPlace(DisplayPlace.ViewQuickActions).build();
}


public ProductsProposalRowsLoader createRowsLoaderFromRecord(TableRecordReference recordRef)


public CreateViewRequest createViewRequest(TableRecordReference recordRef){
    return CreateViewRequest.builder(getWindowId()).setParameter(PARAM_RecordRef, recordRef).build();
}


public ProductsProposalView getById(ViewId viewId){
    final ProductsProposalView view = getByIdOrNull(viewId);
    if (view == null) {
        throw new EntityNotFoundException("View not found: " + viewId.toJson());
    }
    return view;
}


@Override
public void invalidateView(ViewId viewId){
    final ProductsProposalView view = getById(viewId);
    view.invalidateAll();
}


public List<RelatedProcessDescriptor> getRelatedProcessDescriptors()


public void beforeViewClose(ViewId viewId,ViewCloseAction closeAction){
// nothing on this level
}


public Optional<ITranslatableString> getProcessCaption(Class<T> processClass){
    return Services.get(IADProcessDAO.class).retrieveProcessNameByClassIfUnique(processClass);
}


@Override
public void setViewsRepository(IViewsRepository viewsRepository){
}


public TableRecordReference getRecordReference(CreateViewRequest request){
    final TableRecordReference recordRef = request.getParameterAs(PARAM_RecordRef, TableRecordReference.class);
    if (recordRef == null) {
        throw new AdempiereException("Invalid request, parameter " + PARAM_RecordRef + " is not set: " + request);
    }
    return recordRef;
}


public ViewLayout createViewLayout(ViewLayoutKey key)


@Override
public ProductsProposalView createView(CreateViewRequest request){
    final ProductsProposalRowsData rowsData = loadRowsData(request);
    return ProductsProposalView.builder().windowId(getWindowId()).rowsData(rowsData).processes(getRelatedProcessDescriptors()).build();
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
    beforeViewClose(viewId, closeAction);
    views.invalidate(viewId);
    views.cleanUp();
}


}