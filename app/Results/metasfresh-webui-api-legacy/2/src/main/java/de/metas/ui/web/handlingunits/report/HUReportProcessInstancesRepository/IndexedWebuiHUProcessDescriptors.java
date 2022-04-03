package de.metas.ui.web.handlingunits.report.HUReportProcessInstancesRepository;
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
public class IndexedWebuiHUProcessDescriptors {

 private  ImmutableMap<ProcessId,WebuiHUProcessDescriptor> descriptorsByProcessId;


public Collection<WebuiHUProcessDescriptor> getAll(){
    return descriptorsByProcessId.values();
}


public WebuiHUProcessDescriptor getByProcessId(ProcessId processId){
    final WebuiHUProcessDescriptor descriptor = descriptorsByProcessId.get(processId);
    if (descriptor == null) {
        throw new EntityNotFoundException("No HU process descriptor found for " + processId);
    }
    return descriptor;
}


}