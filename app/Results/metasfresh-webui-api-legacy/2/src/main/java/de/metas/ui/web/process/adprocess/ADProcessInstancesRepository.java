package de.metas.ui.web.process.adprocess;
 import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.api.IRangeAwareParams;
import org.adempiere.util.lang.IAutoCloseable;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.I_AD_Process;
import org.compiere.util.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Strings;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableSet;
import de.metas.printing.esb.base.util.Check;
import de.metas.process.IADPInstanceDAO;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.JavaProcess;
import de.metas.process.PInstanceId;
import de.metas.process.ProcessDefaultParametersUpdater;
import de.metas.process.ProcessInfo;
import de.metas.process.ProcessInfo.ProcessInfoBuilder;
import de.metas.security.IUserRolePermissions;
import de.metas.ui.web.process.CreateProcessInstanceRequest;
import de.metas.ui.web.process.IProcessInstanceController;
import de.metas.ui.web.process.IProcessInstancesRepository;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.process.WebuiPreconditionsContext;
import de.metas.ui.web.process.descriptor.ProcessDescriptor;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRowIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.IDocumentEvaluatee;
import de.metas.ui.web.window.model.NullDocumentChangesCollector;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Services;
import lombok.NonNull;
@Component
public class ADProcessInstancesRepository implements IProcessInstancesRepository{

@Autowired
 private  UserSession userSession;

@Autowired
 private  DocumentDescriptorFactory documentDescriptorFactory;

@Autowired
 private  IViewsRepository viewsRepo;

@Autowired
 private  DocumentCollection documentsCollection;

 private  ADProcessDescriptorsFactory processDescriptorFactory;

 private  Cache<DocumentId,ADProcessInstanceController> processInstances;


@Override
public void cacheReset(){
    processInstances.invalidateAll();
    processInstances.cleanUp();
}


@Override
public Stream<WebuiRelatedProcessDescriptor> streamDocumentRelatedProcesses(WebuiPreconditionsContext preconditionsContext){
    final IUserRolePermissions userRolePermissions = userSession.getUserRolePermissions();
    return processDescriptorFactory.streamDocumentRelatedProcesses(preconditionsContext, userRolePermissions);
}


public IProcessInstanceController createNewProcessInstance0(CreateProcessInstanceRequest request,IDocumentEvaluatee evalCtx){
    // 
    // Save process info together with it's parameters and get the the newly created AD_PInstance_ID
    final ProcessInfo processInfo = createProcessInfo(request);
    Services.get(IADPInstanceDAO.class).saveProcessInfo(processInfo);
    final DocumentId adPInstanceId = DocumentId.of(processInfo.getPinstanceId());
    final Object processClassInstance = processInfo.newProcessClassInstanceOrNull();
    if (processClassInstance == null) {
        throw new AdempiereException("No process instance created for " + processInfo);
    }
    try (final IAutoCloseable c = JavaProcess.temporaryChangeCurrentInstance(processClassInstance)) {
        // 
        // Build the parameters document
        final ProcessDescriptor processDescriptor = getProcessDescriptor(request.getProcessId());
        final DocumentEntityDescriptor parametersDescriptor = processDescriptor.getParametersDescriptor();
        final Document parametersDoc = ADProcessParametersRepository.instance.createNewParametersDocument(parametersDescriptor, adPInstanceId, evalCtx);
        final int windowNo = parametersDoc.getWindowNo();
        // Set parameters's default values
        ProcessDefaultParametersUpdater.newInstance().addDefaultParametersProvider(processClassInstance instanceof IProcessDefaultParametersProvider ? (IProcessDefaultParametersProvider) processClassInstance : null).onDefaultValue((parameter, value) -> parametersDoc.processValueChange(parameter.getColumnName(), value, () -> "default parameter value")).updateDefaultValue(parametersDoc.getFieldViews(), field -> DocumentFieldAsProcessDefaultParameter.of(windowNo, field));
        // 
        // Create (webui) process instance and add it to our internal cache.
        final ADProcessInstanceController pinstance = ADProcessInstanceController.builder().caption(processDescriptor.getCaption()).instanceId(adPInstanceId).parameters(parametersDoc).processClassInstance(processClassInstance).startProcessDirectly(processDescriptor.isStartProcessDirectly()).contextSingleDocumentPath(request.getSingleDocumentPath()).viewId(request.getViewRowIdsSelection() != null ? request.getViewRowIdsSelection().getViewId() : null).build();
        processInstances.put(adPInstanceId, pinstance.copyReadonly());
        return pinstance;
    }
}


public ADProcessInstanceController getOrLoad(DocumentId pinstanceId){
    try {
        return processInstances.get(pinstanceId, () -> retrieveProcessInstance(pinstanceId));
    } catch (final ExecutionException e) {
        throw AdempiereException.wrapIfNeeded(e);
    }
}


@Override
public R forProcessInstanceReadonly(DocumentId pinstanceId,Function<IProcessInstanceController,R> processor){
    try (final IAutoCloseable readLock = getOrLoad(pinstanceId).lockForReading()) {
        final ADProcessInstanceController processInstance = getOrLoad(pinstanceId).copyReadonly().bindContextSingleDocumentIfPossible(documentsCollection);
        try (final IAutoCloseable c = processInstance.activate()) {
            return processor.apply(processInstance);
        }
    }
}


@Override
public String getProcessHandlerType(){
    return ProcessId.PROCESSHANDLERTYPE_AD_Process;
}


@VisibleForTesting
public void addViewInternalParameters(CreateProcessInstanceRequest request,ProcessInfoBuilder processInfoBuilder){
    if (request.getViewRowIdsSelection() != null) {
        final ViewRowIdsSelection viewRowIdsSelection = request.getViewRowIdsSelection();
        processInfoBuilder.setLoadParametersFromDB(// important: we need to load the existing parameters from database, besides the internal ones we are adding here
        true).addParameter(ViewBasedProcessTemplate.PARAM_ViewId, viewRowIdsSelection.getViewId().toJson()).addParameter(ViewBasedProcessTemplate.PARAM_ViewSelectedIds, viewRowIdsSelection.getRowIds().toCommaSeparatedString());
    }
    if (request.getParentViewRowIdsSelection() != null) {
        final ViewRowIdsSelection parentViewRowIdsSelection = request.getParentViewRowIdsSelection();
        processInfoBuilder.setLoadParametersFromDB(// important: we need to load the existing parameters from database, besides the internal ones we are adding here
        true).addParameter(ViewBasedProcessTemplate.PARAM_ParentViewId, parentViewRowIdsSelection.getViewId().toJson()).addParameter(ViewBasedProcessTemplate.PARAM_ParentViewSelectedIds, parentViewRowIdsSelection.getRowIds().toCommaSeparatedString());
    }
    if (request.getChildViewRowIdsSelection() != null) {
        final ViewRowIdsSelection childViewRowIdsSelection = request.getChildViewRowIdsSelection();
        processInfoBuilder.setLoadParametersFromDB(// important: we need to load the existing parameters from database, besides the internal ones we are adding here
        true).addParameter(ViewBasedProcessTemplate.PARAM_ChildViewId, childViewRowIdsSelection.getViewId().toJson()).addParameter(ViewBasedProcessTemplate.PARAM_ChildViewSelectedIds, childViewRowIdsSelection.getRowIds().toCommaSeparatedString());
    }
}


public ADProcessInstanceController retrieveProcessInstance(DocumentId adPInstanceDocumentId){
    Check.assumeNotNull(adPInstanceDocumentId, "Parameter adPInstanceDocumentId is not null");
    // 
    // Load process info
    final PInstanceId pinstanceId = PInstanceId.ofRepoId(adPInstanceDocumentId.toInt());
    final ProcessInfo processInfo = ProcessInfo.builder().setCtx(Env.getCtx()).setCreateTemporaryCtx().setPInstanceId(pinstanceId).build();
    final Object processClassInstance = processInfo.newProcessClassInstanceOrNull();
    try (final IAutoCloseable c = JavaProcess.temporaryChangeCurrentInstance(processClassInstance)) {
        // 
        // Build the parameters document
        final ProcessId processId = ProcessId.ofAD_Process_ID(processInfo.getAdProcessId());
        final ProcessDescriptor processDescriptor = getProcessDescriptor(processId);
        // 
        // Build the parameters (as document)
        final DocumentEntityDescriptor parametersDescriptor = processDescriptor.getParametersDescriptor();
        final Document parametersDoc = parametersDescriptor.getDataBinding().getDocumentsRepository().retrieveDocumentById(parametersDescriptor, adPInstanceDocumentId, NullDocumentChangesCollector.instance);
        // TODO: handle the case when the process was already executed
        // In that case we need to load the result and provide it to ProcessInstance constructor
        // 
        // View informations
        final IRangeAwareParams processInfoParams = processInfo.getParameterAsIParams();
        final String viewIdStr = processInfoParams.getParameterAsString(ViewBasedProcessTemplate.PARAM_ViewId);
        final ViewId viewId = Strings.isNullOrEmpty(viewIdStr) ? null : ViewId.ofViewIdString(viewIdStr);
        // 
        return ADProcessInstanceController.builder().caption(processDescriptor.getCaption()).instanceId(adPInstanceDocumentId).parameters(parametersDoc).processClassInstance(processClassInstance).startProcessDirectly(processDescriptor.isStartProcessDirectly()).viewId(viewId).build();
    }
}


@Override
public IProcessInstanceController createNewProcessInstance(CreateProcessInstanceRequest request){
    if (documentsCollection.isValidDocumentPath(request.getSingleDocumentPath())) {
        // In case we have a single document path, we shall fetch it as use it as evaluation context.
        // This will make sure that the parameter's default values will be correctly computed
        return documentsCollection.forDocumentReadonly(request.getSingleDocumentPath(), document -> createNewProcessInstance0(request, document.asEvaluatee()));
    } else {
        // N/A
        final IDocumentEvaluatee shadowParentDocumentEvaluatee = null;
        return createNewProcessInstance0(request, shadowParentDocumentEvaluatee);
    }
}


public ProcessInfo createProcessInfo(CreateProcessInstanceRequest request){
    final DocumentPath singleDocumentPath = request.getSingleDocumentPath();
    final String tableName;
    final int recordId;
    final String sqlWhereClause;
    final AdWindowId adWindowId;
    // 
    // View
    if (request.getViewRowIdsSelection() != null) {
        final ViewRowIdsSelection viewRowIdsSelection = request.getViewRowIdsSelection();
        final ViewId viewId = viewRowIdsSelection.getViewId();
        final IView view = viewsRepo.getView(viewId);
        final DocumentIdsSelection viewDocumentIds = viewRowIdsSelection.getRowIds();
        adWindowId = viewId.getWindowId().toAdWindowIdOrNull();
        if (viewDocumentIds.isSingleDocumentId()) {
            final DocumentId viewSingleDocumentId = viewDocumentIds.getSingleDocumentId();
            final TableRecordReference recordRef = view.getTableRecordReferenceOrNull(viewSingleDocumentId);
            if (recordRef != null) {
                tableName = recordRef.getTableName();
                recordId = recordRef.getRecord_ID();
            } else {
                tableName = view.getTableNameOrNull(viewSingleDocumentId);
                recordId = -1;
            }
        } else {
            tableName = view.getTableNameOrNull(null);
            recordId = -1;
        }
        final boolean emptyTableName = Check.isEmpty(tableName);
        if (viewDocumentIds.isEmpty() || emptyTableName) {
            // Note: in the case of material cockpit, there is no single tableName to be returned by view.getTableNameOrNull,
            // so we do have selected rows, but no table name, which is OK
            sqlWhereClause = null;
        } else {
            sqlWhereClause = view.getSqlWhereClause(viewDocumentIds, SqlOptions.usingTableName(tableName));
        }
    } else // 
    // Single document call
    if (singleDocumentPath != null) {
        final DocumentEntityDescriptor entityDescriptor = documentDescriptorFactory.getDocumentEntityDescriptor(singleDocumentPath);
        adWindowId = singleDocumentPath.getWindowId().toAdWindowIdOrNull();
        tableName = entityDescriptor.getTableNameOrNull();
        final DocumentId documentId;
        if (singleDocumentPath.isRootDocument()) {
            documentId = singleDocumentPath.getDocumentId();
        } else {
            documentId = singleDocumentPath.getSingleRowId();
        }
        if (documentId.isInt()) {
            recordId = documentId.toInt();
        } else {
            recordId = -1;
        }
        sqlWhereClause = entityDescriptor.getDataBinding(SqlDocumentEntityDataBindingDescriptor.class).getSqlWhereClauseById(documentId);
    } else // 
    // From menu
    {
        tableName = null;
        recordId = -1;
        sqlWhereClause = null;
        adWindowId = null;
    }
    // 
    final Set<TableRecordReference> selectedIncludedRecords = request.getSelectedIncludedDocumentPaths().stream().map(documentDescriptorFactory::getTableRecordReference).collect(ImmutableSet.toImmutableSet());
    final ProcessInfoBuilder processInfoBuilder = ProcessInfo.builder().setCtx(Env.getCtx()).setCreateTemporaryCtx().setAD_Process_ID(request.getProcessIdAsInt()).setAdWindowId(adWindowId).setRecord(tableName, recordId).setSelectedIncludedRecords(selectedIncludedRecords).setWhereClause(sqlWhereClause);
    // 
    // View related internal parameters
    addViewInternalParameters(request, processInfoBuilder);
    return processInfoBuilder.build();
}


@Override
public ProcessDescriptor getProcessDescriptor(ProcessId processId){
    return processDescriptorFactory.getProcessDescriptor(processId);
}


@Override
public R forProcessInstanceWritable(DocumentId pinstanceId,IDocumentChangesCollector changesCollector,Function<IProcessInstanceController,R> processor){
    try (final IAutoCloseable writeLock = getOrLoad(pinstanceId).lockForWriting()) {
        final ADProcessInstanceController processInstance = getOrLoad(pinstanceId).copyReadWrite(changesCollector).bindContextSingleDocumentIfPossible(documentsCollection);
        // Make sure the process was not already executed.
        // If it was executed we are not allowed to change it.
        processInstance.assertNotExecuted();
        try (final IAutoCloseable c = processInstance.activate()) {
            // Call the given processor to apply changes to this process instance.
            final R result = processor.apply(processInstance);
            // Actually put it back
            // throwEx=false
            processInstance.saveIfValidAndHasChanges(false);
            processInstances.put(pinstanceId, processInstance.copyReadonly());
            return result;
        }
    }
}


}