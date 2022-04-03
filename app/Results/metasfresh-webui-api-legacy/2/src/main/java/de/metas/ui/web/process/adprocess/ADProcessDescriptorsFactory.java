package de.metas.ui.web.process.adprocess;
 import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.element.api.AdTabId;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.element.api.IADElementDAO;
import org.adempiere.ad.expression.api.ConstantLogicExpression;
import org.adempiere.ad.expression.api.IExpression;
import org.adempiere.ad.expression.api.IExpressionFactory;
import org.adempiere.ad.expression.api.ILogicExpression;
import org.adempiere.ad.table.api.IADTableDAO;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.api.IRangeAwareParams;
import org.compiere.model.I_AD_Element;
import org.compiere.model.I_AD_Process;
import org.compiere.model.I_AD_Process_Para;
import org.compiere.model.X_AD_Process;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import de.metas.cache.CCache;
import de.metas.i18n.IModelTranslationMap;
import de.metas.logging.LogManager;
import de.metas.process.BarcodeScannerType;
import de.metas.process.IADProcessDAO;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.JavaProcess;
import de.metas.process.ProcessParams;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.security.IUserRolePermissions;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.process.WebuiPreconditionsContext;
import de.metas.ui.web.process.descriptor.InternalName;
import de.metas.ui.web.process.descriptor.ProcessDescriptor;
import de.metas.ui.web.process.descriptor.ProcessDescriptor.ProcessDescriptorType;
import de.metas.ui.web.process.descriptor.ProcessLayout;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.window.datatypes.DateRangeValue;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor.DocumentEntityDataBindingDescriptorBuilder;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider;
import de.metas.ui.web.window.descriptor.factory.standard.DefaultValueExpressionsFactory;
import de.metas.ui.web.window.descriptor.factory.standard.DescriptorsFactoryHelper;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import de.metas.util.lang.CoalesceUtil;
import lombok.Builder;
import lombok.NonNull;
public class ADProcessDescriptorsFactory {

 private  Logger logger;

 private  IExpressionFactory expressionFactory;

 private  DefaultValueExpressionsFactory defaultValueExpressions;

 private  IADTableDAO adTableDAO;

 private  IADProcessDAO adProcessDAO;

 private  CCache<ProcessId,ProcessDescriptor> processDescriptorsByProcessId;

 private  IProcessPreconditionsContext preconditionsContext;

 private  ProcessDescriptor processDescriptor;

 public  ProcessParametersDataBindingDescriptorBuilder instance;

 private  DocumentEntityDataBindingDescriptor dataBinding;


public Stream<WebuiRelatedProcessDescriptor> streamDocumentRelatedProcesses(WebuiPreconditionsContext preconditionsContext,IUserRolePermissions userRolePermissions){
    final String tableName = preconditionsContext.getTableName();
    final int adTableId = !Check.isEmpty(tableName) ? adTableDAO.retrieveTableId(tableName) : -1;
    final AdWindowId adWindowId = preconditionsContext.getAdWindowId();
    final AdTabId adTabId = preconditionsContext.getAdTabId();
    final Stream<RelatedProcessDescriptor> relatedProcessDescriptors;
    {
        final Stream<RelatedProcessDescriptor> tableRelatedProcessDescriptors = adProcessDAO.retrieveRelatedProcessDescriptors(adTableId, adWindowId, adTabId).stream();
        final Stream<RelatedProcessDescriptor> additionalRelatedProcessDescriptors = preconditionsContext.getAdditionalRelatedProcessDescriptors().stream();
        relatedProcessDescriptors = Stream.concat(additionalRelatedProcessDescriptors, tableRelatedProcessDescriptors).collect(GuavaCollectors.distinctBy(RelatedProcessDescriptor::getProcessId));
    }
    return relatedProcessDescriptors.filter(relatedProcess -> isEligible(relatedProcess, preconditionsContext, userRolePermissions)).map(relatedProcess -> toWebuiRelatedProcessDescriptor(relatedProcess, preconditionsContext));
}


public WebuiRelatedProcessDescriptor toWebuiRelatedProcessDescriptor(RelatedProcessDescriptor relatedProcessDescriptor,IProcessPreconditionsContext preconditionsContext){
    final ProcessId processId = ProcessId.ofAD_Process_ID(relatedProcessDescriptor.getProcessId());
    final ProcessDescriptor processDescriptor = getProcessDescriptor(processId);
    final ProcessPreconditionsResolutionSupplier preconditionsResolutionSupplier = ProcessPreconditionsResolutionSupplier.builder().preconditionsContext(preconditionsContext).processDescriptor(processDescriptor).build();
    return WebuiRelatedProcessDescriptor.builder().processId(processDescriptor.getProcessId()).internalName(processDescriptor.getInternalName()).processCaption(processDescriptor.getCaption()).processDescription(processDescriptor.getDescription()).debugProcessClassname(processDescriptor.getProcessClassname()).displayPlaces(relatedProcessDescriptor.getDisplayPlaces()).defaultQuickAction(relatedProcessDescriptor.isWebuiDefaultQuickAction()).shortcut(relatedProcessDescriptor.getWebuiShortcut()).preconditionsResolutionSupplier(preconditionsResolutionSupplier).sortNo(relatedProcessDescriptor.getSortNo()).build();
}


public void extractAndSetTranslatableValues(I_AD_Process_Para adProcessParamRecord,DocumentFieldDescriptor.Builder paramDescriptorBuilder){
    if (adProcessParamRecord.getAD_Element_ID() <= 0) {
        final I_AD_Process_Para processParamTrl = InterfaceWrapperHelper.translate(adProcessParamRecord, I_AD_Process_Para.class);
        paramDescriptorBuilder.setCaption(processParamTrl.getName()).setDescription(processParamTrl.getDescription());
    } else {
        final I_AD_Element element = Services.get(IADElementDAO.class).getById(adProcessParamRecord.getAD_Element_ID());
        final I_AD_Element elementTrl = InterfaceWrapperHelper.translate(element, I_AD_Element.class);
        paramDescriptorBuilder.setCaption(elementTrl.getName()).setDescription(elementTrl.getDescription());
    }
}


public BarcodeScannerType extractBarcodeScannerTypeOrNull(I_AD_Process_Para adProcessParamRecord,WebuiProcessClassInfo webuiProcesClassInfo){
    final String parameterName = adProcessParamRecord.getColumnName();
    BarcodeScannerType barcodeScannerType = webuiProcesClassInfo.getBarcodeScannerTypeOrNull(parameterName);
    if (barcodeScannerType != null) {
        return barcodeScannerType;
    }
    final String barcodeScannerTypeCode = adProcessParamRecord.getBarcodeScannerType();
    return !Check.isEmpty(barcodeScannerTypeCode, true) ? BarcodeScannerType.ofCode(barcodeScannerTypeCode) : null;
}


public DocumentFieldWidgetType extractWidgetType(String parameterName,int adReferenceId,Optional<LookupDescriptor> lookupDescriptor,boolean isRange){
    final DocumentFieldWidgetType widgetType = DescriptorsFactoryHelper.extractWidgetType(parameterName, adReferenceId, lookupDescriptor);
    // Date range:
    if (isRange && widgetType == DocumentFieldWidgetType.LocalDate) {
        return DocumentFieldWidgetType.DateRange;
    } else // Others
    {
        return widgetType;
    }
}


public void forwardValueToCurrentProcessInstance(ICalloutField calloutField){
    final JavaProcess processInstance = JavaProcess.currentInstance();
    final String parameterName = calloutField.getColumnName();
    final IRangeAwareParams source = createSource(calloutField);
    // Ask the instance to load the parameter
    processInstance.loadParameterValueNoFail(parameterName, source);
}


public boolean computeIsStartProcessDirectly(String showHelpParam,boolean hasProcessParameters){
    final String showHelp = CoalesceUtil.coalesce(showHelpParam, X_AD_Process.SHOWHELP_DonTShowHelp);
    if (X_AD_Process.SHOWHELP_ShowHelp.equals(showHelp)) {
        return false;
    } else if (X_AD_Process.SHOWHELP_DonTShowHelp.equals(showHelp)) {
        return !hasProcessParameters;
    } else if (X_AD_Process.SHOWHELP_RunSilently_TakeDefaults.equals(showHelp)) {
        return true;
    } else {
        return false;
    }
}


public IRangeAwareParams createSource(ICalloutField calloutField){
    final String parameterName = calloutField.getColumnName();
    final Object fieldValue = calloutField.getValue();
    if (fieldValue instanceof LookupValue) {
        final Object idObj = ((LookupValue) fieldValue).getId();
        return ProcessParams.ofValueObject(parameterName, idObj);
    } else if (fieldValue instanceof DateRangeValue) {
        final DateRangeValue dateRange = (DateRangeValue) fieldValue;
        return ProcessParams.of(parameterName, TimeUtil.asDate(dateRange.getFrom()), TimeUtil.asDate(dateRange.getTo()));
    } else {
        return ProcessParams.ofValueObject(parameterName, fieldValue);
    }
}


public ProcessDescriptor retrieveProcessDescriptor(ProcessId processId){
    final IADProcessDAO adProcessesRepo = Services.get(IADProcessDAO.class);
    final I_AD_Process adProcess = adProcessesRepo.getById(processId.toAdProcessId());
    if (adProcess == null) {
        throw new EntityNotFoundException("@NotFound@ @AD_Process_ID@ (" + processId + ")");
    }
    final WebuiProcessClassInfo webuiProcesClassInfo = WebuiProcessClassInfo.of(adProcess.getClassname());
    final IModelTranslationMap adProcessTrlsMap = InterfaceWrapperHelper.getModelTranslationMap(adProcess);
    // 
    // Parameters document descriptor
    final DocumentEntityDescriptor parametersDescriptor;
    {
        final DocumentEntityDescriptor.Builder parametersDescriptorBuilder = DocumentEntityDescriptor.builder().setDocumentType(DocumentType.Process, processId.toDocumentId()).setCaption(adProcessTrlsMap.getColumnTrl(I_AD_Process.COLUMNNAME_Name, adProcess.getName())).setDescription(adProcessTrlsMap.getColumnTrl(I_AD_Process.COLUMNNAME_Description, adProcess.getDescription())).setDataBinding(ProcessParametersDataBindingDescriptorBuilder.instance).disableDefaultTableCallouts();
        // Get AD_Process_Para(s) and populate the entity descriptor
        adProcessDAO.retrieveProcessParameters(adProcess).stream().map(adProcessParam -> createProcessParaDescriptor(webuiProcesClassInfo, adProcessParam)).forEach(processParaDescriptor -> parametersDescriptorBuilder.addField(processParaDescriptor));
        parametersDescriptor = parametersDescriptorBuilder.build();
    }
    // 
    // Parameters layout
    final ProcessLayout.Builder layout = ProcessLayout.builder().setProcessId(processId).setLayoutType(webuiProcesClassInfo.getLayoutType()).setCaption(parametersDescriptor.getCaption()).setDescription(parametersDescriptor.getDescription()).addElements(parametersDescriptor);
    final boolean startProcessDirectly = computeIsStartProcessDirectly(adProcess.getShowHelp(), // hasProcessParameters
    !parametersDescriptor.getFields().isEmpty());
    // 
    // Process descriptor
    return ProcessDescriptor.builder().setProcessId(processId).setInternalName(InternalName.ofString(adProcess.getValue())).setType(extractType(adProcess)).setProcessClassname(extractClassnameOrNull(adProcess)).setParametersDescriptor(parametersDescriptor).setStartProcessDirectly(startProcessDirectly).setLayout(layout.build()).build();
}


public DocumentFieldDescriptor.Builder createProcessParaDescriptor(WebuiProcessClassInfo webuiProcesClassInfo,I_AD_Process_Para adProcessParam){
    final String parameterName = adProcessParam.getColumnName();
    // 
    // Ask the provider if it has some custom lookup descriptor
    LookupDescriptorProvider lookupDescriptorProvider = webuiProcesClassInfo.getLookupDescriptorProviderOrNull(parameterName);
    // Fallback: create an SQL lookup descriptor based on adProcessParam
    if (lookupDescriptorProvider == null) {
        lookupDescriptorProvider = SqlLookupDescriptor.builder().setCtxTableName(null).setCtxColumnName(parameterName).setDisplayType(adProcessParam.getAD_Reference_ID()).setAD_Reference_Value_ID(adProcessParam.getAD_Reference_Value_ID()).setAD_Val_Rule_ID(adProcessParam.getAD_Val_Rule_ID()).setReadOnlyAccess().buildProvider();
    }
    // 
    final Optional<LookupDescriptor> lookupDescriptor = lookupDescriptorProvider.provide();
    final DocumentFieldWidgetType widgetType = extractWidgetType(parameterName, adProcessParam.getAD_Reference_ID(), lookupDescriptor, adProcessParam.isRange());
    final Class<?> valueClass = DescriptorsFactoryHelper.getValueClass(widgetType, lookupDescriptor);
    // process parameters shall always allow displaying the password
    final boolean allowShowPassword = widgetType == DocumentFieldWidgetType.Password ? true : false;
    final BarcodeScannerType barcodeScannerType = extractBarcodeScannerTypeOrNull(adProcessParam, webuiProcesClassInfo);
    final ILogicExpression readonlyLogic = expressionFactory.compileOrDefault(adProcessParam.getReadOnlyLogic(), ConstantLogicExpression.FALSE, ILogicExpression.class);
    final ILogicExpression displayLogic = expressionFactory.compileOrDefault(adProcessParam.getDisplayLogic(), ConstantLogicExpression.TRUE, ILogicExpression.class);
    final ILogicExpression mandatoryLogic = adProcessParam.isMandatory() ? displayLogic : ConstantLogicExpression.FALSE;
    final Optional<IExpression<?>> defaultValueExpr = defaultValueExpressions.extractDefaultValueExpression(adProcessParam.getDefaultValue(), parameterName, widgetType, valueClass, mandatoryLogic.isConstantTrue(), // don't allow using auto sequence
    false);
    final DocumentFieldDescriptor.Builder paramDescriptorBuilder = DocumentFieldDescriptor.builder(parameterName);
    extractAndSetTranslatableValues(adProcessParam, paramDescriptorBuilder);
    final DocumentFieldDescriptor.Builder paramDescriptor = paramDescriptorBuilder.setValueClass(valueClass).setWidgetType(widgetType).setAllowShowPassword(allowShowPassword).barcodeScannerType(barcodeScannerType).setLookupDescriptorProvider(lookupDescriptorProvider).setDefaultValueExpression(defaultValueExpr).setReadonlyLogic(readonlyLogic).setDisplayLogic(displayLogic).setMandatoryLogic(mandatoryLogic).addCharacteristic(Characteristic.PublicField);
    // Add a callout to forward process parameter value (UI) to current process instance
    if (webuiProcesClassInfo.isForwardValueToJavaProcessInstance(parameterName)) {
        paramDescriptor.addCallout(ProcessParametersCallout::forwardValueToCurrentProcessInstance);
    }
    return paramDescriptor;
}


@Override
public ProcessPreconditionsResolution get(){
    return processDescriptor.checkPreconditionsApplicable(preconditionsContext);
}


@Override
public DocumentEntityDataBindingDescriptor getOrBuild(){
    return dataBinding;
}


@Nullable
public String extractClassnameOrNull(I_AD_Process adProcess){
    if (!Check.isEmpty(adProcess.getClassname(), true)) {
        return adProcess.getClassname();
    }
    return null;
}


public boolean isEligible(RelatedProcessDescriptor relatedProcess,WebuiPreconditionsContext preconditionsContext,IUserRolePermissions userRolePermissions){
    final DisplayPlace displayPlace = preconditionsContext.getDisplayPlace();
    if (displayPlace != null && !relatedProcess.isDisplayedOn(displayPlace)) {
        logger.trace("Process not eligible because displayPlace not matching: {}, {}", relatedProcess, displayPlace);
        return false;
    }
    if (!relatedProcess.isExecutionGranted(userRolePermissions)) {
        logger.trace("Process not eligible because execution not granted: {}, {}", relatedProcess, userRolePermissions);
        return false;
    }
    return true;
}


public ProcessDescriptor getProcessDescriptor(ProcessId processId){
    return processDescriptorsByProcessId.getOrLoad(processId, () -> retrieveProcessDescriptor(processId));
}


public ProcessDescriptorType extractType(I_AD_Process adProcess){
    if (adProcess.getAD_Form_ID() > 0) {
        return ProcessDescriptorType.Form;
    } else if (adProcess.getAD_Workflow_ID() > 0) {
        return ProcessDescriptorType.Workflow;
    } else if (adProcess.isReport()) {
        return ProcessDescriptorType.Report;
    } else {
        return ProcessDescriptorType.Process;
    }
}


}