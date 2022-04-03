package de.metas.ui.web.quickinput.QuickInput;
 import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.IAutoCloseable;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.util.Check;
import de.metas.util.Services;
public class Builder {

 private  AtomicInteger nextQuickInputDocumentId;

 private  DocumentPath _rootDocumentPath;

 private  QuickInputDescriptor _quickInputDescriptor;


public QuickInputDescriptor getQuickInputDescriptor(){
    Check.assumeNotNull(_quickInputDescriptor, "Parameter quickInputDescriptor is not null");
    return _quickInputDescriptor;
}


public Builder setRootDocumentPath(DocumentPath rootDocumentPath){
    _rootDocumentPath = Preconditions.checkNotNull(rootDocumentPath, "rootDocumentPath");
    return this;
}


public DetailId getTargetDetailId(){
    final DetailId targetDetailId = getQuickInputDescriptor().getDetailId();
    Check.assumeNotNull(targetDetailId, "Parameter targetDetailId is not null");
    return targetDetailId;
}


public Document buildQuickInputDocument(){
    return Document.builder(getQuickInputDescriptor().getEntityDescriptor()).initializeAsNewDocument(nextQuickInputDocumentId::getAndIncrement, VERSION_DEFAULT);
}


public QuickInput build(){
    return new QuickInput(this);
}


public Builder setQuickInputDescriptor(QuickInputDescriptor quickInputDescriptor){
    _quickInputDescriptor = quickInputDescriptor;
    return this;
}


public DocumentPath getRootDocumentPath(){
    Check.assumeNotNull(_rootDocumentPath, "Parameter rootDocumentPath is not null");
    return _rootDocumentPath;
}


}