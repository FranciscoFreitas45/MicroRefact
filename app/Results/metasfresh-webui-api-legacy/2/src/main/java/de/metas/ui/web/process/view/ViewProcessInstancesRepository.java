package de.metas.ui.web.process.view;
 import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;
import org.adempiere.util.lang.IPair;
import org.adempiere.util.lang.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.metas.cache.CCache;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.process.CreateProcessInstanceRequest;
import de.metas.ui.web.process.IProcessInstanceController;
import de.metas.ui.web.process.IProcessInstancesRepository;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.process.ViewAsPreconditionsContext;
import de.metas.ui.web.process.WebuiPreconditionsContext;
import de.metas.ui.web.process.descriptor.ProcessDescriptor;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import lombok.NonNull;
import lombok.ToString;
@Component
public class ViewProcessInstancesRepository implements IProcessInstancesRepository{

@Autowired
 private  IViewsRepository viewsRepository;

 private  String PROCESS_HANDLER_TYPE;

 private  CCache<String,ViewActionInstancesList> viewActionInstancesByViewId;

 private  String viewId;

 private  AtomicInteger nextIdSupplier;

 private  ConcurrentHashMap<DocumentId,ViewActionInstance> instances;


@Override
public Stream<WebuiRelatedProcessDescriptor> streamDocumentRelatedProcesses(WebuiPreconditionsContext preconditionsContext){
    final ViewAsPreconditionsContext viewContext = ViewAsPreconditionsContext.castOrNull(preconditionsContext);
    if (viewContext == null) {
        return Stream.empty();
    }
    final IView view = viewContext.getView();
    return getViewActionDescriptors(view).streamDocumentRelatedProcesses(viewContext);
}


public void add(ViewActionInstance viewActionInstance){
    instances.put(viewActionInstance.getInstanceId(), viewActionInstance);
}


@Override
public String getProcessHandlerType(){
    return PROCESS_HANDLER_TYPE;
}


public ViewActionInstance getActionInstance(DocumentId pinstanceId){
    final String viewId = ViewActionInstancesList.extractViewId(pinstanceId);
    final ViewActionInstancesList viewActionInstancesList = viewActionInstancesByViewId.get(viewId);
    if (viewActionInstancesList == null) {
        throw new EntityNotFoundException("No view action instance found for " + pinstanceId);
    }
    return viewActionInstancesList.getByInstanceId(pinstanceId);
}


public ViewActionDescriptor getViewActionDescriptor(ProcessId processId){
    final IPair<String, String> viewIdAndActionId = extractViewIdAndActionId(processId);
    final String viewId = viewIdAndActionId.getLeft();
    final String actionId = viewIdAndActionId.getRight();
    final IView view = viewsRepository.getView(viewId);
    return getViewActionDescriptors(view).getAction(actionId);
}


@Override
public void cacheReset(){
    viewActionInstancesByViewId.reset();
}


public DocumentId nextPInstanceId(){
    final int nextId = nextIdSupplier.incrementAndGet();
    return DocumentId.ofString(viewId + "_" + nextId);
}


public IPair<String,String> extractViewIdAndActionId(ProcessId processId){
    final String processIdStr = processId.getProcessId();
    final int idx = processIdStr.indexOf("_");
    if (idx <= 0) {
        throw new IllegalArgumentException("Invalid view action ID: " + processId);
    }
    final String viewId = processIdStr.substring(0, idx);
    final String actionId = processIdStr.substring(idx + 1);
    return ImmutablePair.of(viewId, actionId);
}


@Override
public R forProcessInstanceReadonly(DocumentId pinstanceId,Function<IProcessInstanceController,R> processor){
    final ViewActionInstance actionInstance = getActionInstance(pinstanceId);
    return processor.apply(actionInstance);
}


public String extractViewId(DocumentId pinstanceId){
    final String pinstanceIdStr = pinstanceId.toJson();
    final int idx = pinstanceIdStr.indexOf("_");
    final String viewId = pinstanceIdStr.substring(0, idx);
    return viewId;
}


@Override
public IProcessInstanceController createNewProcessInstance(CreateProcessInstanceRequest request){
    // 
    // Get the view and and the viewActionDescriptor
    final IPair<String, String> viewIdAndActionId = extractViewIdAndActionId(request.getProcessId());
    final String viewId = viewIdAndActionId.getLeft();
    final String actionId = viewIdAndActionId.getRight();
    final IView view = viewsRepository.getView(viewId);
    final ViewActionDescriptor viewActionDescriptor = getViewActionDescriptors(view).getAction(actionId);
    // 
    // Create the view action instance
    // and add it to our internal list of current view action instances
    final ViewActionInstancesList viewActionInstancesList = viewActionInstancesByViewId.getOrLoad(viewId, () -> new ViewActionInstancesList(viewId));
    final DocumentId pinstanceId = viewActionInstancesList.nextPInstanceId();
    final ViewActionInstance viewActionInstance = ViewActionInstance.builder().pinstanceId(pinstanceId).view(view).viewActionDescriptor(viewActionDescriptor).selectedDocumentIds(request.getViewRowIdsSelection().getRowIds()).build();
    request.assertProcessIdEquals(viewActionInstance.getProcessId());
    viewActionInstancesList.add(viewActionInstance);
    // 
    // Return the newly created instance
    return viewActionInstance;
}


public ViewActionDescriptorsList getViewActionDescriptors(IView view){
    final ViewActionDescriptorsList viewClassActions = ViewActionDescriptorsFactory.instance.getFromClass(view.getClass());
    final ViewActionDescriptorsList viewActions = view.getActions();
    return viewClassActions.mergeWith(viewActions);
}


@Override
public ProcessDescriptor getProcessDescriptor(ProcessId processId){
    return getViewActionDescriptor(processId).getProcessDescriptor(processId);
}


public ProcessId buildProcessId(ViewId viewId,String viewActionId){
    return ProcessId.of(PROCESS_HANDLER_TYPE, viewId.getViewId() + "_" + viewActionId);
}


@Override
public R forProcessInstanceWritable(DocumentId pinstanceId,IDocumentChangesCollector changesCollector,Function<IProcessInstanceController,R> processor){
    final ViewActionInstance actionInstance = getActionInstance(pinstanceId);
    // Make sure the process was not already executed.
    // If it was executed we are not allowed to change it.
    actionInstance.assertNotExecuted();
    return processor.apply(actionInstance);
}


public ViewActionInstance getByInstanceId(DocumentId pinstanceId){
    final ViewActionInstance actionInstance = instances.get(pinstanceId);
    if (actionInstance == null) {
        throw new EntityNotFoundException("No view action instance found for " + pinstanceId);
    }
    return actionInstance;
}


}