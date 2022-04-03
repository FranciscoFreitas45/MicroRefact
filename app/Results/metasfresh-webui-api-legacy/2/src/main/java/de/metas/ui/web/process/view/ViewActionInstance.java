package de.metas.ui.web.process.view;
 import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.process.IProcessInstanceController;
import de.metas.ui.web.process.IProcessInstanceParameter;
import de.metas.ui.web.process.ProcessExecutionContext;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.process.ProcessInstanceResult;
import de.metas.ui.web.process.ProcessInstanceResult.CreateAndOpenIncludedViewAction;
import de.metas.ui.web.process.ProcessInstanceResult.OpenIncludedViewAction;
import de.metas.ui.web.process.ProcessInstanceResult.ResultAction;
import de.metas.ui.web.process.adprocess.DocumentFieldAsProcessInstanceParameter;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.DocumentValidStatus;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class ViewActionInstance implements IProcessInstanceController{

 private  String VERSION_DEFAULT;

 private  ProcessId processId;

 private  DocumentId pinstanceId;

 private  WeakReference<IView> viewRef;

 private  ViewActionDescriptor viewActionDescriptor;

 private  DocumentIdsSelection selectedDocumentIds;

 private  ProcessInstanceResult result;

@Nullable
 private  Document parametersDocument;

@Getter
 private  boolean startProcessDirectly;


@Override
public ProcessInstanceResult getExecutionResult(){
    Check.assumeNotNull(result, "action was already executed");
    return result;
}


@Override
public DocumentId getInstanceId(){
    return pinstanceId;
}


@Override
public LookupValuesList getParameterLookupValues(String parameterName){
    return parametersDocument.getFieldLookupValues(parameterName);
}


@Override
public ProcessInstanceResult startProcess(ProcessExecutionContext context){
    assertNotExecuted();
    // 
    // Validate parameters, if any
    if (parametersDocument != null) {
        final DocumentValidStatus validStatus = parametersDocument.checkAndGetValidStatus();
        if (!validStatus.isValid()) {
            throw new AdempiereException(validStatus.getReason());
        }
    }
    // 
    // Execute view action's method
    final IView view = getView();
    final Method viewActionMethod = viewActionDescriptor.getViewActionMethod();
    final Object[] viewActionParams = viewActionDescriptor.extractMethodArguments(view, parametersDocument, selectedDocumentIds);
    try {
        final Object targetObject = Modifier.isStatic(viewActionMethod.getModifiers()) ? null : view;
        final Object resultActionObj = viewActionMethod.invoke(targetObject, viewActionParams);
        final ResultAction resultAction = viewActionDescriptor.convertReturnType(resultActionObj);
        final ResultAction resultActionProcessed = processResultAction(resultAction, context.getViewsRepo());
        final ProcessInstanceResult result = ProcessInstanceResult.builder(pinstanceId).action(resultActionProcessed).build();
        this.result = result;
        return result;
    } catch (final Throwable ex) {
        throw AdempiereException.wrapIfNeeded(ex);
    }
}


@Override
public void processParameterValueChanges(List<JSONDocumentChangedEvent> events,ReasonSupplier reason){
    parametersDocument.processValueChanges(events, reason);
}


@Override
public LookupValuesList getParameterLookupValuesForQuery(String parameterName,String query){
    return parametersDocument.getFieldLookupValuesForQuery(parameterName, query);
}


public ProcessId getProcessId(){
    return processId;
}


@Override
public Collection<IProcessInstanceParameter> getParameters(){
    if (parametersDocument == null) {
        return ImmutableList.of();
    }
    return parametersDocument.getFieldViews().stream().map(DocumentFieldAsProcessInstanceParameter::of).collect(ImmutableList.toImmutableList());
}


public void assertNotExecuted(){
    Check.assumeNull(result, "view action instance not already executed");
}


public IView getView(){
    final IView view = viewRef.get();
    if (view == null) {
        throw new AdempiereException("view is no longer available for " + this);
    }
    return view;
}


public ResultAction processResultAction(ResultAction resultAction,IViewsRepository viewRepos){
    if (resultAction == null) {
        return null;
    }
    if (resultAction instanceof CreateAndOpenIncludedViewAction) {
        final IView view = viewRepos.createView(((CreateAndOpenIncludedViewAction) resultAction).getCreateViewRequest());
        return OpenIncludedViewAction.builder().viewId(view.getViewId()).build();
    }
    return resultAction;
}


}