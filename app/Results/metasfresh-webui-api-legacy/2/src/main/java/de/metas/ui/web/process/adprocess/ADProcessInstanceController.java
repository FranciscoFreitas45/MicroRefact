package de.metas.ui.web.process.adprocess;
 import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import javax.annotation.Nullable;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.model;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.api.IRangeAwareParams;
import org.adempiere.util.lang.IAutoCloseable;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.logging.LogManager;
import de.metas.process.JavaProcess;
import de.metas.process.PInstanceId;
import de.metas.process.ProcessExecutor;
import de.metas.process.ProcessInfo;
import de.metas.report.server.OutputType;
import de.metas.ui.web.process.IProcessInstanceController;
import de.metas.ui.web.process.IProcessInstanceParameter;
import de.metas.ui.web.process.ProcessExecutionContext;
import de.metas.ui.web.process.ProcessInstanceResult;
import de.metas.ui.web.process.exceptions.ProcessExecutionException;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import de.metas.report.server.ReportConstants.REPORT_PARAM_REPORT_FORMAT;
public class ADProcessInstanceController implements IProcessInstanceController{

 private  Logger logger;

@Getter
 private  DocumentId instanceId;

 private  ITranslatableString caption;

 private  Document parameters;

 private  Object processClassInstance;

@Getter
 private  boolean startProcessDirectly;

@Getter
 private  ViewId viewId;

 private  DocumentPath contextSingleDocumentPath;

 private  boolean executed;

 private  ProcessInstanceResult executionResult;

 private  ReentrantReadWriteLock readwriteLock;


public boolean saveIfValidAndHasChanges(boolean throwEx){
    final Document parametersDocument = getParametersDocument();
    final DocumentSaveStatus parametersSaveStatus = parametersDocument.saveIfValidAndHasChanges();
    final boolean saved = parametersSaveStatus.isSaved();
    if (!saved && throwEx) {
        throw new ProcessExecutionException(parametersSaveStatus.getReason());
    }
    return saved;
}


public IAutoCloseable lockForReading(){
    final ReadLock readLock = readwriteLock.readLock();
    logger.debug("Acquiring read lock for {}: {}", this, readLock);
    readLock.lock();
    logger.debug("Acquired read lock for {}: {}", this, readLock);
    return () -> {
        readLock.unlock();
        logger.debug("Released read lock for {}: {}", this, readLock);
    };
}


public boolean isExecuted(){
    return executed;
}


@Override
public ProcessInstanceResult startProcess(ProcessExecutionContext context){
    assertNotExecuted();
    // 
    // Make sure it's saved in database
    if (!saveIfValidAndHasChanges(true)) {
        // shall not happen because the method throws the exception in case of failure
        throw new ProcessExecutionException("Instance could not be saved because it's not valid");
    }
    // 
    executionResult = executeADProcess(context);
    if (executionResult.isSuccess()) {
        executed = false;
    }
    logger.debug("executionResult.success={} executionResult.summary={}", executionResult.isSuccess(), executionResult.getSummary());
    return executionResult;
}


public ProcessInstanceResult executeADProcess(ProcessExecutionContext context){
    // 
    // Create the process info and execute the process synchronously
    final ProcessExecutor processExecutor = ProcessInfo.builder().setCtx(context.getCtx()).setCreateTemporaryCtx().setPInstanceId(PInstanceId.ofRepoId(getInstanceId().toInt())).setTitle(caption.translate(context.getAdLanguage())).setPrintPreview(true).setJRDesiredOutputType(getTargetOutputType()).buildAndPrepareExecution().onErrorThrowException().executeSync();
    final ADProcessPostProcessService postProcessService = ADProcessPostProcessService.builder().viewsRepo(context.getViewsRepo()).documentsCollection(context.getDocumentsCollection()).build();
    return postProcessService.postProcess(ADProcessPostProcessRequest.builder().viewId(getViewId()).processInfo(processExecutor.getProcessInfo()).processExecutionResult(processExecutor.getResult()).build());
}


@Override
public void processParameterValueChanges(List<JSONDocumentChangedEvent> events,ReasonSupplier reason){
    assertNotExecuted();
    parameters.processValueChanges(events, reason);
    updateParametersDocumentFromJavaProcessAnnotatedFields();
}


@Override
public LookupValuesList getParameterLookupValuesForQuery(String parameterName,String query){
    return parameters.getFieldLookupValuesForQuery(parameterName, query);
}


public IAutoCloseable lockForWriting(){
    final WriteLock writeLock = readwriteLock.writeLock();
    logger.debug("Acquiring write lock for {}: {}", this, writeLock);
    writeLock.lock();
    logger.debug("Acquired write lock for {}: {}", this, writeLock);
    return () -> {
        writeLock.unlock();
        logger.debug("Released write lock for {}: {}", this, writeLock);
    };
}


public OutputType getTargetOutputType(){
    final IDocumentFieldView formatField = parameters.getFieldViewOrNull(REPORT_PARAM_REPORT_FORMAT);
    if (formatField != null) {
        final LookupValue outputTypeParamValue = formatField.getValueAs(LookupValue.class);
        if (outputTypeParamValue != null) {
            final Optional<OutputType> targetOutputType = OutputType.getOutputTypeByFileExtension(String.valueOf(formatField.getValueAs(LookupValue.class).getId()));
            if (targetOutputType.isPresent()) {
                return targetOutputType.get();
            }
        }
    }
    return OutputType.getDefault();
}


public void assertNotExecuted(){
    if (isExecuted()) {
        throw new AdempiereException("Process already executed");
    }
}


public ADProcessInstanceController copyReadWrite(IDocumentChangesCollector changesCollector){
    return new ADProcessInstanceController(this, CopyMode.CheckOutWritable, changesCollector);
}


public ADProcessInstanceController copyReadonly(){
    return new ADProcessInstanceController(this, CopyMode.CheckInReadonly, NullDocumentChangesCollector.instance);
}


public JSONDocumentChangedEvent createJSONDocumentChangedEventFromFieldValue(IRangeAwareParams parameterFieldValues,String parameterName){
    final Object fieldValue = parameterFieldValues.getParameterAsObject(parameterName);
    if (fieldValue == null) {
        return JSONDocumentChangedEvent.replace(parameterName, null);
    } else if (InterfaceWrapperHelper.isModelInterface(fieldValue.getClass())) {
        int id = InterfaceWrapperHelper.getId(fieldValue);
        return JSONDocumentChangedEvent.replace(parameterName, id);
    } else {
        return JSONDocumentChangedEvent.replace(parameterName, fieldValue);
    }
}


@Override
public ProcessInstanceResult getExecutionResult(){
    final ProcessInstanceResult executionResult = this.executionResult;
    if (executionResult == null) {
        throw new AdempiereException("Process instance does not have an execution result yet: " + this);
    }
    return executionResult;
}


public ADProcessInstanceController bindContextSingleDocumentIfPossible(DocumentCollection documentsCollection){
    if (contextSingleDocumentPath == null) {
        return this;
    }
    if (!documentsCollection.isWindowIdSupported(contextSingleDocumentPath.getWindowIdOrNull())) {
        return this;
    }
    final Document contextSingleDocument = documentsCollection.getDocumentReadonly(contextSingleDocumentPath);
    getParametersDocument().setShadowParentDocumentEvaluatee(contextSingleDocument.asEvaluatee());
    return this;
}


@Override
public LookupValuesList getParameterLookupValues(String parameterName){
    return parameters.getFieldLookupValues(parameterName);
}


@Override
public Collection<IProcessInstanceParameter> getParameters(){
    return parameters.getFieldViews().stream().map(DocumentFieldAsProcessInstanceParameter::of).collect(ImmutableList.toImmutableList());
}


public IAutoCloseable activate(){
    return JavaProcess.temporaryChangeCurrentInstance(processClassInstance);
}


public void updateParametersDocumentFromJavaProcessAnnotatedFields(){
    final JavaProcess currentProcessInstance = JavaProcess.currentInstance();
    // If the process has no callouts,
    // there is no point to update the parameters Document from process's annotated fields values,
    // because nobody will change those process's annotated fields values directly.
    if (!currentProcessInstance.hasParametersCallout()) {
        return;
    }
    final IRangeAwareParams parameterFieldValues = currentProcessInstance.getParametersFromAnnotatedFields();
    final List<JSONDocumentChangedEvent> events = parameterFieldValues.getParameterNames().stream().filter(parameters::hasField).map(parameterName -> createJSONDocumentChangedEventFromFieldValue(parameterFieldValues, parameterName)).collect(ImmutableList.toImmutableList());
    if (events.isEmpty()) {
        return;
    }
    parameters.processValueChanges(events, () -> "update from java process annotated fields");
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("AD_PInstance_ID", instanceId).add("executed", "executed").add("executionResult", executionResult).add("caption", caption).toString();
}


public Document getParametersDocument(){
    return parameters;
}


}