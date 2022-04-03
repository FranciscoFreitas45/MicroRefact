package de.metas.ui.web.handlingunits.report;
 import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Stream;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.IAutoCloseable;
import org.compiere.model.I_AD_Process;
import org.springframework.stereotype.Component;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.cache.CCache;
import de.metas.handlingunits.model.I_M_HU_Process;
import de.metas.handlingunits.process.api.HUProcessDescriptor;
import de.metas.handlingunits.process.api.IMHUProcessDAO;
import de.metas.i18n.IModelTranslationMap;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.process.AdProcessId;
import de.metas.process.IADProcessDAO;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.process.CreateProcessInstanceRequest;
import de.metas.ui.web.process.IProcessInstanceController;
import de.metas.ui.web.process.IProcessInstancesRepository;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.process.ViewAsPreconditionsContext;
import de.metas.ui.web.process.WebuiPreconditionsContext;
import de.metas.ui.web.process.descriptor.InternalName;
import de.metas.ui.web.process.descriptor.ProcessDescriptor;
import de.metas.ui.web.process.descriptor.ProcessDescriptor.ProcessDescriptorType;
import de.metas.ui.web.process.descriptor.ProcessLayout;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.util.Services;
import lombok.NonNull;
@Component
public class HUReportProcessInstancesRepository implements IProcessInstancesRepository{

 private  String PROCESS_HANDLER_TYPE;

 private  CCache<Integer,IndexedWebuiHUProcessDescriptors> processDescriptors;

 private  Cache<DocumentId,HUReportProcessInstance> instances;

 private  ImmutableMap<ProcessId,WebuiHUProcessDescriptor> descriptorsByProcessId;


@Override
public Stream<WebuiRelatedProcessDescriptor> streamDocumentRelatedProcesses(WebuiPreconditionsContext preconditionsContext){
    final ViewAsPreconditionsContext viewContext = ViewAsPreconditionsContext.castOrNull(preconditionsContext);
    if (viewContext == null) {
        return Stream.empty();
    }
    if (viewContext.isNoSelection()) {
        return Stream.empty();
    }
    if (!(viewContext.getView() instanceof HUEditorView)) {
        return Stream.empty();
    }
    return getIndexedWebuiHUProcessDescriptors().getAll().stream().filter(descriptor -> checkApplies(descriptor, viewContext)).map(descriptor -> descriptor.toWebuiRelatedProcessDescriptor());
}


public IndexedWebuiHUProcessDescriptors getIndexedWebuiHUProcessDescriptors(){
    return processDescriptors.getOrLoad(0, this::retrieveIndexedWebuiHUProcessDescriptors);
}


public Collection<WebuiHUProcessDescriptor> getAll(){
    return descriptorsByProcessId.values();
}


@Override
public String getProcessHandlerType(){
    return PROCESS_HANDLER_TYPE;
}


public WebuiHUProcessDescriptor getWebuiHUProcessDescriptor(ProcessId processId){
    return getIndexedWebuiHUProcessDescriptors().getByProcessId(processId);
}


public IndexedWebuiHUProcessDescriptors retrieveIndexedWebuiHUProcessDescriptors(){
    final IMHUProcessDAO huProcessDescriptorsRepo = Services.get(IMHUProcessDAO.class);
    return new IndexedWebuiHUProcessDescriptors(huProcessDescriptorsRepo.getHUProcessDescriptors().stream().filter(HUProcessDescriptor::isProvideAsUserAction).map(this::toWebuiHUProcessDescriptor).collect(ImmutableList.toImmutableList()));
}


@Override
public void cacheReset(){
    processDescriptors.reset();
    instances.cleanUp();
}


public DocumentId nextPInstanceId(){
    return DocumentId.ofString(UUID.randomUUID().toString());
}


public void putInstance(HUReportProcessInstance instance){
    instances.cleanUp();
    instances.put(instance.getInstanceId(), instance.copyReadonly());
}


@Override
public R forProcessInstanceReadonly(DocumentId pinstanceId,Function<IProcessInstanceController,R> processor){
    try (final IAutoCloseable readLock = getInstance(pinstanceId).lockForReading()) {
        final HUReportProcessInstance processInstance = getInstance(pinstanceId).copyReadonly();
        return processor.apply(processInstance);
    }
}


public WebuiHUProcessDescriptor getByProcessId(ProcessId processId){
    final WebuiHUProcessDescriptor descriptor = descriptorsByProcessId.get(processId);
    if (descriptor == null) {
        throw new EntityNotFoundException("No HU process descriptor found for " + processId);
    }
    return descriptor;
}


public WebuiHUProcessDescriptor toWebuiHUProcessDescriptor(HUProcessDescriptor huProcessDescriptor){
    final AdProcessId reportADProcessId = huProcessDescriptor.getProcessId();
    final ProcessId processId = ProcessId.of(PROCESS_HANDLER_TYPE, reportADProcessId.getRepoId());
    final I_AD_Process adProcess = Services.get(IADProcessDAO.class).getById(reportADProcessId);
    final IModelTranslationMap adProcessTrl = InterfaceWrapperHelper.getModelTranslationMap(adProcess);
    final ITranslatableString caption = adProcessTrl.getColumnTrl(I_AD_Process.COLUMNNAME_Name, adProcess.getName());
    final ITranslatableString description = adProcessTrl.getColumnTrl(I_AD_Process.COLUMNNAME_Description, adProcess.getDescription());
    final DocumentEntityDescriptor parametersDescriptor = DocumentEntityDescriptor.builder().setDocumentType(DocumentType.Process, processId.toDocumentId()).setCaption(caption).setDescription(description).disableDefaultTableCallouts().addField(DocumentFieldDescriptor.builder(HUReportProcessInstance.PARAM_Copies).setCaption(Services.get(IMsgBL.class).translatable(HUReportProcessInstance.PARAM_Copies)).setWidgetType(DocumentFieldWidgetType.Integer)).build();
    return WebuiHUProcessDescriptor.builder().huProcessDescriptor(huProcessDescriptor).processDescriptor(ProcessDescriptor.builder().setProcessId(processId).setInternalName(InternalName.ofString(huProcessDescriptor.getInternalName())).setType(ProcessDescriptorType.Report).setParametersDescriptor(parametersDescriptor).setLayout(ProcessLayout.builder().setProcessId(processId).setCaption(caption).setDescription(description).addElements(parametersDescriptor).build()).build()).build();
}


@Override
public HUReportProcessInstance createNewProcessInstance(CreateProcessInstanceRequest request){
    final WebuiHUProcessDescriptor descriptor = getWebuiHUProcessDescriptor(request.getProcessId());
    final DocumentId instanceId = nextPInstanceId();
    final Document parameters = Document.builder(descriptor.getParametersDescriptor()).initializeAsNewDocument(instanceId, /* version */
    "0");
    final HUReportProcessInstance instance = HUReportProcessInstance.builder().instanceId(instanceId).viewRowIdsSelection(request.getViewRowIdsSelection()).reportAdProcessId(descriptor.getReportAdProcessId()).parameters(parameters).build();
    instance.setCopies(1);
    putInstance(instance);
    return instance;
}


public HUReportProcessInstance getInstance(DocumentId instanceId){
    final HUReportProcessInstance instance = instances.getIfPresent(instanceId);
    if (instance == null) {
        throw new EntityNotFoundException("No HU Report instance found for " + instanceId);
    }
    return instance;
}


@Override
public ProcessDescriptor getProcessDescriptor(ProcessId processId){
    return getWebuiHUProcessDescriptor(processId).getProcessDescriptor();
}


public boolean checkApplies(HUEditorRow row,WebuiHUProcessDescriptor descriptor){
    final String huUnitType = row.getType().toHUUnitTypeOrNull();
    return huUnitType != null && descriptor.appliesToHUUnitType(huUnitType);
}


@Override
public R forProcessInstanceWritable(DocumentId pinstanceId,IDocumentChangesCollector changesCollector,Function<IProcessInstanceController,R> processor){
    try (final IAutoCloseable readLock = getInstance(pinstanceId).lockForWriting()) {
        final HUReportProcessInstance processInstance = getInstance(pinstanceId).copyReadWrite(changesCollector);
        final R result = processor.apply(processInstance);
        putInstance(processInstance);
        return result;
    }
}


}