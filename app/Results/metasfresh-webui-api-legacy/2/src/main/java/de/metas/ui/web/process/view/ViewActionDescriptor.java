package de.metas.ui.web.process.view;
 import java.lang.reflect.Method;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.process.ProcessInstanceResult;
import de.metas.ui.web.process.ViewAsPreconditionsContext;
import de.metas.ui.web.process.descriptor.InternalName;
import de.metas.ui.web.process.descriptor.ProcessDescriptor;
import de.metas.ui.web.process.descriptor.ProcessDescriptor.ProcessDescriptorType;
import de.metas.ui.web.process.descriptor.ProcessLayout;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.process.view.ViewAction.Precondition;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.model.Document;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
import javax.annotation.Nullable;
@ToString
@Builder
public class ViewActionDescriptor {

@NonNull
 private  String actionId;

@NonNull
 private  Method viewActionMethod;

@NonNull
 private  ITranslatableString caption;

@NonNull
 private  ITranslatableString description;

 private  boolean defaultAction;

 private  Class<? extends Precondition> preconditionClass;

 private  Precondition preconditionSharedInstance;

@NonNull
 private  PanelLayoutType layoutType;

@Singular
 private  ImmutableList<ViewActionParamDescriptor> viewActionParamDescriptors;

@NonNull
 private  ViewActionMethodReturnTypeConverter viewActionReturnTypeConverter;


public WebuiRelatedProcessDescriptor toWebuiRelatedProcessDescriptor(ViewAsPreconditionsContext viewContext){
    final IView view = viewContext.getView();
    final DocumentIdsSelection selectedDocumentIds = viewContext.getSelectedRowIds();
    return WebuiRelatedProcessDescriptor.builder().processId(ViewProcessInstancesRepository.buildProcessId(view.getViewId(), actionId)).processCaption(caption).processDescription(description).displayPlace(DisplayPlace.ViewQuickActions).defaultQuickAction(defaultAction).preconditionsResolutionSupplier(() -> checkPreconditions(view, selectedDocumentIds)).build();
}


public ProcessPreconditionsResolution checkPreconditions(IView view,DocumentIdsSelection selectedDocumentIds){
    try {
        return getPreconditionsInstance().matches(view, selectedDocumentIds);
    } catch (final InstantiationException | IllegalAccessException ex) {
        throw AdempiereException.wrapIfNeeded(ex);
    }
}


public Object extractArgument(IView view,Document processParameters,DocumentIdsSelection selectedDocumentIds)


public String getActionId(){
    return actionId;
}


@Nullable
public DocumentEntityDescriptor createParametersEntityDescriptor(ProcessId processId){
    final DocumentEntityDescriptor.Builder parametersDescriptor = DocumentEntityDescriptor.builder().setDocumentType(DocumentType.Process, processId.toDocumentId()).disableDefaultTableCallouts();
    viewActionParamDescriptors.stream().filter(ViewActionParamDescriptor::isUserParameter).map(ViewActionParamDescriptor::createParameterFieldDescriptor).forEach(parametersDescriptor::addField);
    if (parametersDescriptor.getFieldsCount() == 0) {
        return null;
    }
    return parametersDescriptor.build();
}


@NonNull
public Object[] extractMethodArguments(IView view,Document processParameters,DocumentIdsSelection selectedDocumentIds){
    return viewActionParamDescriptors.stream().map(paramDesc -> paramDesc.extractArgument(view, processParameters, selectedDocumentIds)).toArray();
}


@NonNull
public Method getViewActionMethod(){
    return viewActionMethod;
}


public Precondition getPreconditionsInstance(){
    if (preconditionSharedInstance != null) {
        return preconditionSharedInstance;
    }
    return preconditionClass.newInstance();
}


public ProcessInstanceResult.ResultAction convertReturnType(Object returnValue){
    return viewActionReturnTypeConverter.convert(returnValue);
}


public ProcessInstanceResult.ResultAction convert(Object returnValue)


public ProcessDescriptor getProcessDescriptor(ProcessId processId){
    final DocumentEntityDescriptor parametersDescriptor = createParametersEntityDescriptor(processId);
    final ProcessLayout processLayout = ProcessLayout.builder().setProcessId(processId).setLayoutType(layoutType).setCaption(caption).setDescription(description).addElements(parametersDescriptor).build();
    return ProcessDescriptor.builder().setProcessId(processId).setInternalName(InternalName.ofString(actionId)).setType(ProcessDescriptorType.Process).setLayout(processLayout).build();
}


}