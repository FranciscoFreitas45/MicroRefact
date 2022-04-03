package de.metas.ui.web.process.view.ViewProcessInstancesRepository;
 import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;
import org.adempiere.util.lang.IPair;
import org.adempiere.util.lang.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.metas.cache.CCache;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.process.CreateProcessInstanceRequest;
import de.metas.ui.web.process.IProcessInstanceController;
import de.metas.ui.web.process.IProcessInstancesRepository;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.process.ViewAsPreconditionsContext;
import de.metas.ui.web.process.WebuiPreconditionsContext;
import de.metas.ui.web.process.descriptor.ProcessDescriptor;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class ViewActionInstancesList {

 private  String viewId;

 private  AtomicInteger nextIdSupplier;

 private  ConcurrentHashMap<DocumentId,ViewActionInstance> instances;


public void add(ViewActionInstance viewActionInstance){
    instances.put(viewActionInstance.getInstanceId(), viewActionInstance);
}


public DocumentId nextPInstanceId(){
    final int nextId = nextIdSupplier.incrementAndGet();
    return DocumentId.ofString(viewId + "_" + nextId);
}


public String extractViewId(DocumentId pinstanceId){
    final String pinstanceIdStr = pinstanceId.toJson();
    final int idx = pinstanceIdStr.indexOf("_");
    final String viewId = pinstanceIdStr.substring(0, idx);
    return viewId;
}


public ViewActionInstance getByInstanceId(DocumentId pinstanceId){
    final ViewActionInstance actionInstance = instances.get(pinstanceId);
    if (actionInstance == null) {
        throw new EntityNotFoundException("No view action instance found for " + pinstanceId);
    }
    return actionInstance;
}


}