package de.metas.ui.web.handlingunits.report;
 import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.IAutoCloseable;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.report.HUReportExecutor;
import de.metas.handlingunits.report.HUReportExecutorResult;
import de.metas.handlingunits.report.HUReportService;
import de.metas.handlingunits.report.HUToReport;
import de.metas.process.AdProcessId;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.process.IProcessInstanceController;
import de.metas.ui.web.process.IProcessInstanceParameter;
import de.metas.ui.web.process.ProcessExecutionContext;
import de.metas.ui.web.process.ProcessInstanceResult;
import de.metas.ui.web.process.adprocess.ADProcessPostProcessRequest;
import de.metas.ui.web.process.adprocess.ADProcessPostProcessService;
import de.metas.ui.web.process.adprocess.DocumentFieldAsProcessInstanceParameter;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRowIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.ui.web.window.model.NullDocumentChangesCollector;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class HUReportProcessInstance implements IProcessInstanceController{

 public  String PARAM_Copies;

 private  DocumentId instanceId;

 private  ViewRowIdsSelection viewRowIdsSelection;

 private  AdProcessId reportAdProcessId;

 private  Document parameters;

@Getter
 private  boolean startProcessDirectly;

 private  ProcessInstanceResult lastExecutionResult;

 private  ReentrantReadWriteLock readwriteLock;


public IAutoCloseable lockForReading(){
    final ReadLock readLock = readwriteLock.readLock();
    readLock.lock();
    return readLock::unlock;
}


@Override
public DocumentId getInstanceId(){
    return instanceId;
}


@Override
public ProcessInstanceResult startProcess(ProcessExecutionContext context){
    final int numberOfCopies = getCopies();
    if (numberOfCopies <= 0) {
        throw new AdempiereException("@" + PARAM_Copies + "@ > 0");
    }
    final IViewsRepository viewsRepo = context.getViewsRepo();
    final DocumentCollection documentsCollection = context.getDocumentsCollection();
    final ViewId viewId = viewRowIdsSelection.getViewId();
    final HUEditorView view = HUEditorView.cast(viewsRepo.getView(viewId));
    final HUReportExecutorResult reportExecutorResult = HUReportExecutor.newInstance(context.getCtx()).numberOfCopies(numberOfCopies).printPreview(true).executeNow(reportAdProcessId, extractHUsToReport(view));
    final ADProcessPostProcessService postProcessService = ADProcessPostProcessService.builder().viewsRepo(viewsRepo).documentsCollection(documentsCollection).build();
    final ProcessInstanceResult result = postProcessService.postProcess(ADProcessPostProcessRequest.builder().viewId(viewId).processInfo(reportExecutorResult.getProcessInfo()).processExecutionResult(reportExecutorResult.getProcessExecutionResult()).instanceIdOverride(instanceId).build());
    return lastExecutionResult = result;
}


@Override
public void processParameterValueChanges(List<JSONDocumentChangedEvent> events,ReasonSupplier reason){
    parameters.processValueChanges(events, reason);
}


@Override
public LookupValuesList getParameterLookupValuesForQuery(String parameterName,String query){
    return parameters.getFieldLookupValuesForQuery(parameterName, query);
}


public IAutoCloseable lockForWriting(){
    final WriteLock writeLock = readwriteLock.writeLock();
    writeLock.lock();
    return writeLock::unlock;
}


public HUReportProcessInstance copyReadWrite(IDocumentChangesCollector changesCollector){
    return new HUReportProcessInstance(this, CopyMode.CheckOutWritable, changesCollector);
}


public HUReportProcessInstance copyReadonly(){
    return new HUReportProcessInstance(this, CopyMode.CheckInReadonly, NullDocumentChangesCollector.instance);
}


@Override
public ProcessInstanceResult getExecutionResult(){
    return lastExecutionResult;
}


@Override
public LookupValuesList getParameterLookupValues(String parameterName){
    return parameters.getFieldLookupValues(parameterName);
}


@Override
public Collection<IProcessInstanceParameter> getParameters(){
    return parameters.getFieldViews().stream().map(DocumentFieldAsProcessInstanceParameter::of).collect(ImmutableList.toImmutableList());
}


public void setCopies(int copies){
    parameters.processValueChange(PARAM_Copies, copies, ReasonSupplier.NONE);
}


public List<HUToReport> extractHUsToReport(HUEditorView view){
    final Set<HUToReport> husToCheck = view.streamByIds(viewRowIdsSelection.getRowIds()).map(HUEditorRow::getAsHUToReportOrNull).filter(Objects::nonNull).collect(ImmutableSet.toImmutableSet());
    return HUReportService.get().getHUsToProcess(husToCheck);
}


public int getCopies(){
    return parameters.getFieldView(PARAM_Copies).getValueAsInt(0);
}


}