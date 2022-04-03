package de.metas.ui.web.process;
 import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.compiere.model.I_AD_PInstance;
import org.compiere.model.I_AD_Process;
import org.compiere.util.Env;
import org.slf4j.Logger;
import org.slf4j.MDC.MDCCloseable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.logging.LogManager;
import de.metas.logging.TableRecordMDC;
import de.metas.process.PInstanceId;
import de.metas.process.ProcessClassInfo;
import de.metas.ui.web.cache.ETagResponseEntityBuilder;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.process.ProcessInstanceResult.OpenReportAction;
import de.metas.ui.web.process.adprocess.WebuiProcessClassInfo;
import de.metas.ui.web.process.descriptor.ProcessDescriptor;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.process.json.JSONCreateProcessInstanceRequest;
import de.metas.ui.web.process.json.JSONProcessInstance;
import de.metas.ui.web.process.json.JSONProcessInstanceResult;
import de.metas.ui.web.process.json.JSONProcessLayout;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRowIdsSelection;
import de.metas.ui.web.window.controller.Execution;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocument;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.ui.web.window.datatypes.json.JSONDocumentOptions;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.ui.web.window.model.NullDocumentChangesCollector;
import de.metas.util.Check;
import de.metas.util.lang.CoalesceUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
@Api
@RestController
@RequestMapping(ProcessRestController.ENDPOINT)
public class ProcessRestController {

 public  String ENDPOINT;

 private  Logger logger;

 private  ImmutableMap<String,IProcessInstancesRepository> pinstancesRepositoriesByHandlerType;

 private  UserSession userSession;

 private  IViewsRepository viewsRepo;

 private  DocumentCollection documentsCollection;

 private  ReasonSupplier REASON_Value_DirectSetFromCommitAPI;


public Stream<WebuiRelatedProcessDescriptor> streamDocumentRelatedProcesses(WebuiPreconditionsContext preconditionsContext){
    return getAllRepositories().stream().flatMap(repo -> repo.streamDocumentRelatedProcesses(preconditionsContext));
}


@PostMapping("/{processId}")
public JSONProcessInstance createInstanceFromRequest(String processIdStr,JSONCreateProcessInstanceRequest jsonRequest){
    final ProcessId processId = ProcessId.fromJson(processIdStr);
    try (final MDCCloseable processMDC = TableRecordMDC.putTableRecordReference(I_AD_Process.Table_Name, processId.toAdProcessId())) {
        userSession.assertLoggedIn();
        final ViewRowIdsSelection viewRowIdsSelection = jsonRequest.getViewRowIdsSelection();
        final ViewId viewId = viewRowIdsSelection != null ? viewRowIdsSelection.getViewId() : null;
        final DocumentIdsSelection viewSelectedRowIds = viewRowIdsSelection != null ? viewRowIdsSelection.getRowIds() : DocumentIdsSelection.EMPTY;
        // Get the effective singleDocumentPath, i.e.
        // * if provided, use it
        // * if not provided and we have a single selected row in the view, ask the view's row to provide the effective document path
        DocumentPath singleDocumentPath = jsonRequest.getSingleDocumentPath();
        if (singleDocumentPath == null && viewSelectedRowIds.isSingleDocumentId()) {
            final IView view = viewsRepo.getView(viewId);
            singleDocumentPath = view.getById(viewSelectedRowIds.getSingleDocumentId()).getDocumentPath();
        }
        final CreateProcessInstanceRequest request = CreateProcessInstanceRequest.builder().processId(processId).singleDocumentPath(singleDocumentPath).selectedIncludedDocumentPaths(jsonRequest.getSelectedIncludedDocumentPaths()).viewRowIdsSelection(viewRowIdsSelection).parentViewRowIdsSelection(jsonRequest.getParentViewRowIdsSelection()).childViewRowIdsSelection(jsonRequest.getChildViewRowIdsSelection()).build();
        // Validate request's AD_Process_ID
        // (we are not using it, but just for consistency)
        request.assertProcessIdEquals(jsonRequest.getProcessId());
        final IProcessInstancesRepository instancesRepository = getRepository(request.getProcessId());
        return Execution.callInNewExecution("pinstance.create", () -> {
            final IProcessInstanceController processInstance = instancesRepository.createNewProcessInstance(request);
            return JSONProcessInstance.of(processInstance, newJsonOptions());
        });
    }
}


@GetMapping(value = "/{processId}/{pinstanceId}/start")
public JSONProcessInstanceResult startProcess(String processIdStr,String pinstanceIdStr){
    final ProcessId processId = ProcessId.fromJson(processIdStr);
    final DocumentId pinstanceId = DocumentId.of(pinstanceIdStr);
    try (final MDCCloseable pinstanceMDC = TableRecordMDC.putTableRecordReference(I_AD_PInstance.Table_Name, PInstanceId.ofRepoIdOrNull(pinstanceId.toIntOr(-1)));
        final MDCCloseable processMDC = TableRecordMDC.putTableRecordReference(I_AD_Process.Table_Name, processId.toAdProcessId())) {
        userSession.assertLoggedIn();
        final IProcessInstancesRepository instancesRepository = getRepository(processId);
        return Execution.prepareNewExecution().outOfTransaction().execute(() -> {
            return instancesRepository.forProcessInstanceWritable(pinstanceId, NullDocumentChangesCollector.instance, processInstance -> {
                final ProcessInstanceResult result = processInstance.startProcess(ProcessExecutionContext.builder().ctx(Env.getCtx()).adLanguage(userSession.getAD_Language()).viewsRepo(viewsRepo).documentsCollection(documentsCollection).build());
                return JSONProcessInstanceResult.of(result);
            });
        });
    }
}


@GetMapping("/{processId}/{pinstanceId}/field/{parameterName}/typeahead")
public JSONLookupValuesList getParameterTypeahead(String processIdStr,String pinstanceIdStr,String parameterName,String query){
    final ProcessId processId = ProcessId.fromJson(processIdStr);
    final DocumentId pinstanceId = DocumentId.of(pinstanceIdStr);
    try (final MDCCloseable pinstanceMDC = TableRecordMDC.putTableRecordReference(I_AD_PInstance.Table_Name, PInstanceId.ofRepoIdOrNull(pinstanceId.toIntOr(-1)));
        final MDCCloseable processMDC = TableRecordMDC.putTableRecordReference(I_AD_Process.Table_Name, processId.toAdProcessId())) {
        userSession.assertLoggedIn();
        final IProcessInstancesRepository instancesRepository = getRepository(processId);
        return instancesRepository.forProcessInstanceReadonly(pinstanceId, processInstance -> processInstance.getParameterLookupValuesForQuery(parameterName, query)).transform(this::toJSONLookupValuesList);
    }
}


public JSONLookupValuesList toJSONLookupValuesList(LookupValuesList lookupValuesList){
    return JSONLookupValuesList.ofLookupValuesList(lookupValuesList, userSession.getAD_Language());
}


public JSONDocumentOptions newJsonDocumentOptions(){
    return JSONDocumentOptions.of(userSession);
}


public IProcessInstancesRepository getRepository(ProcessId processId){
    final String processHandlerType = processId.getProcessHandlerType();
    final IProcessInstancesRepository processInstanceRepo = pinstancesRepositoriesByHandlerType.get(processHandlerType);
    if (processInstanceRepo == null) {
        throw new EntityNotFoundException("No pinstances repository found for processHandlerType=" + processHandlerType);
    }
    return processInstanceRepo;
}


@ApiOperation("Retrieves and serves a report that was previously created by a reporting process.")
@GetMapping("/{processId}/{pinstanceId}/print/{filename:.*}")
public ResponseEntity<byte[]> getReport(String processIdStr,String pinstanceIdStr,String filename){
    final ProcessId processId = ProcessId.fromJson(processIdStr);
    final DocumentId pinstanceId = DocumentId.of(pinstanceIdStr);
    try (final MDCCloseable pinstanceMDC = TableRecordMDC.putTableRecordReference(I_AD_PInstance.Table_Name, PInstanceId.ofRepoIdOrNull(pinstanceId.toIntOr(-1)));
        final MDCCloseable processMDC = TableRecordMDC.putTableRecordReference(I_AD_Process.Table_Name, processId.toAdProcessId())) {
        userSession.assertLoggedIn();
        final IProcessInstancesRepository instancesRepository = getRepository(processId);
        final ProcessInstanceResult executionResult = instancesRepository.forProcessInstanceReadonly(pinstanceId, processInstance -> processInstance.getExecutionResult());
        final OpenReportAction action = executionResult.getAction(OpenReportAction.class);
        final String reportFilename = action.getFilename();
        final String reportContentType = action.getContentType();
        final byte[] reportData = action.getReportData();
        final String reportFilenameEffective = CoalesceUtil.coalesce(filename, reportFilename, "");
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(reportContentType));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + reportFilenameEffective + "\"");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        final ResponseEntity<byte[]> response = new ResponseEntity<>(reportData, headers, HttpStatus.OK);
        return response;
    }
}


public JSONOptions newJsonOptions(){
    return JSONOptions.of(userSession);
}


@PatchMapping("/{processId}/{pinstanceId}")
public List<JSONDocument> processParametersChangeEvents(String processIdStr,String pinstanceIdStr,List<JSONDocumentChangedEvent> events){
    final ProcessId processId = ProcessId.fromJson(processIdStr);
    final DocumentId pinstanceId = DocumentId.of(pinstanceIdStr);
    try (final MDCCloseable pinstanceMDC = TableRecordMDC.putTableRecordReference(I_AD_PInstance.Table_Name, PInstanceId.ofRepoIdOrNull(pinstanceId.toIntOr(-1)));
        final MDCCloseable processMDC = TableRecordMDC.putTableRecordReference(I_AD_Process.Table_Name, processId.toAdProcessId())) {
        userSession.assertLoggedIn();
        Check.assumeNotEmpty(events, "events is not empty");
        final IProcessInstancesRepository instancesRepository = getRepository(processId);
        return Execution.callInNewExecution("", () -> {
            // get our collector to fill with the changes that we will record
            final IDocumentChangesCollector changesCollector = Execution.getCurrentDocumentChangesCollectorOrNull();
            instancesRepository.forProcessInstanceWritable(pinstanceId, changesCollector, processInstance -> {
                processInstance.processParameterValueChanges(events, REASON_Value_DirectSetFromCommitAPI);
                // void
                return null;
            });
            return JSONDocument.ofEvents(changesCollector, newJsonDocumentOptions());
        });
    }
}


public void cacheReset(){
    ProcessClassInfo.resetCache();
    WebuiProcessClassInfo.resetCache();
    getAllRepositories().forEach(IProcessInstancesRepository::cacheReset);
}


@GetMapping("/{processId}/layout")
public ResponseEntity<JSONProcessLayout> getLayout(String adProcessIdStr,WebRequest request){
    final ProcessId processId = ProcessId.fromJson(adProcessIdStr);
    try (final MDCCloseable processMDC = TableRecordMDC.putTableRecordReference(I_AD_Process.Table_Name, processId.toAdProcessId())) {
        userSession.assertLoggedIn();
        final IProcessInstancesRepository instancesRepository = getRepository(processId);
        final ProcessDescriptor descriptor = instancesRepository.getProcessDescriptor(processId);
        return ETagResponseEntityBuilder.ofETagAware(request, descriptor).includeLanguageInETag().cacheMaxAge(userSession.getHttpCacheMaxAge()).map(ProcessDescriptor::getLayout).jsonLayoutOptions(this::newJsonLayoutOptions).toLayoutJson(JSONProcessLayout::of);
    }
}


@GetMapping("/{processId}/{pinstanceId}/field/{parameterName}/dropdown")
public JSONLookupValuesList getParameterDropdown(String processIdStr,String pinstanceIdStr,String parameterName){
    final ProcessId processId = ProcessId.fromJson(processIdStr);
    final DocumentId pinstanceId = DocumentId.of(pinstanceIdStr);
    try (final MDCCloseable pinstanceMDC = TableRecordMDC.putTableRecordReference(I_AD_PInstance.Table_Name, PInstanceId.ofRepoIdOrNull(pinstanceId.toIntOr(-1)));
        final MDCCloseable processMDC = TableRecordMDC.putTableRecordReference(I_AD_Process.Table_Name, processId.toAdProcessId())) {
        userSession.assertLoggedIn();
        final IProcessInstancesRepository instancesRepository = getRepository(processId);
        return instancesRepository.forProcessInstanceReadonly(pinstanceId, processInstance -> processInstance.getParameterLookupValues(parameterName)).transform(this::toJSONLookupValuesList);
    }
}


public Collection<IProcessInstancesRepository> getAllRepositories(){
    return pinstancesRepositoriesByHandlerType.values();
}


public JSONDocumentLayoutOptions newJsonLayoutOptions(){
    return JSONDocumentLayoutOptions.of(userSession);
}


@GetMapping("/{processId}/{pinstanceId}")
public JSONProcessInstance getInstance(String processIdStr,String pinstanceIdStr){
    final ProcessId processId = ProcessId.fromJson(processIdStr);
    final DocumentId pinstanceId = DocumentId.of(pinstanceIdStr);
    try (final MDCCloseable pinstanceMDC = TableRecordMDC.putTableRecordReference(I_AD_PInstance.Table_Name, PInstanceId.ofRepoIdOrNull(pinstanceId.toIntOr(-1)));
        final MDCCloseable processMDC = TableRecordMDC.putTableRecordReference(I_AD_Process.Table_Name, processId.toAdProcessId())) {
        userSession.assertLoggedIn();
        final IProcessInstancesRepository instancesRepository = getRepository(processId);
        return instancesRepository.forProcessInstanceReadonly(pinstanceId, processInstance -> JSONProcessInstance.of(processInstance, newJsonOptions()));
    }
}


}