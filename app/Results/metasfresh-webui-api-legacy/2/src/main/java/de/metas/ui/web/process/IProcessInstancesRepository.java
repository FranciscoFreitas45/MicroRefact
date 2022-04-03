package de.metas.ui.web.process;
 import java.util.function.Function;
import java.util.stream.Stream;
import de.metas.ui.web.process.descriptor.ProcessDescriptor;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
public interface IProcessInstancesRepository {


public Stream<WebuiRelatedProcessDescriptor> streamDocumentRelatedProcesses(WebuiPreconditionsContext preconditionsContext)
;

public void cacheReset()
;

public R forProcessInstanceReadonly(DocumentId pinstanceId,Function<IProcessInstanceController,R> processor)
;

public String getProcessHandlerType()
;

public IProcessInstanceController createNewProcessInstance(CreateProcessInstanceRequest request)
;

public ProcessDescriptor getProcessDescriptor(ProcessId processId)
;

public R forProcessInstanceWritable(DocumentId pinstanceId,IDocumentChangesCollector changesCollector,Function<IProcessInstanceController,R> processor)
;

}