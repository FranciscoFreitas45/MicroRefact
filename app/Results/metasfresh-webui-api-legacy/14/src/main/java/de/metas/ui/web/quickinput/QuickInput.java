package de.metas.ui.web.quickinput;
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
public class QuickInput {

 private  Logger logger;

 private  String VERSION_DEFAULT;

 private  QuickInputDescriptor descriptor;

 private  DocumentPath rootDocumentPath;

 private  DetailId targetDetailId;

 private  Document quickInputDocument;

 private  Document rootDocument;

 private  ReentrantReadWriteLock readwriteLock;

 private  boolean completed;

 private  String DYNATTR_QuickInput;

 private  AtomicInteger nextQuickInputDocumentId;

 private  DocumentPath _rootDocumentPath;

 private  QuickInputDescriptor _quickInputDescriptor;


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


public Document getQuickInputDocument(){
    return quickInputDocument;
}


public JSONLookupValuesList getFieldTypeaheadValues(String fieldName,String query,String adLanguage){
    return getQuickInputDocument().getFieldLookupValuesForQuery(fieldName, query).transform(lookupValuesList -> JSONLookupValuesList.ofLookupValuesList(lookupValuesList, adLanguage));
}


public DetailId getTargetDetailId(){
    final DetailId targetDetailId = getQuickInputDescriptor().getDetailId();
    Check.assumeNotNull(targetDetailId, "Parameter targetDetailId is not null");
    return targetDetailId;
}


public Document buildQuickInputDocument(){
    return Document.builder(getQuickInputDescriptor().getEntityDescriptor()).initializeAsNewDocument(nextQuickInputDocumentId::getAndIncrement, VERSION_DEFAULT);
}


public QuickInput bindRootDocument(Document rootDocument){
    this.rootDocument = rootDocument;
    quickInputDocument.setShadowParentDocumentEvaluatee(rootDocument.asEvaluatee());
    return this;
}


public DocumentId getId(){
    return quickInputDocument.getDocumentId();
}


public Builder setQuickInputDescriptor(QuickInputDescriptor quickInputDescriptor){
    _quickInputDescriptor = quickInputDescriptor;
    return this;
}


public Builder setRootDocumentPath(DocumentPath rootDocumentPath){
    _rootDocumentPath = Preconditions.checkNotNull(rootDocumentPath, "rootDocumentPath");
    return this;
}


public Builder builder(){
    return new Builder();
}


public Document getRootDocument(){
    if (rootDocument == null) {
        throw new IllegalStateException("root document not set for " + this);
    }
    return rootDocument;
}


public QuickInput copy(CopyMode copyMode,IDocumentChangesCollector changesCollector){
    return new QuickInput(this, copyMode, changesCollector);
}


public DetailId getDetailId(){
    return targetDetailId;
}


public boolean isCompleted(){
    return completed;
}


public String getTargetTableName(){
    return quickInputDocument.getEntityDescriptor().getTableName();
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


public DocumentPath getRootDocumentPath(){
    Check.assumeNotNull(_rootDocumentPath, "Parameter rootDocumentPath is not null");
    return _rootDocumentPath;
}


public QuickInput assertTargetWritable(){
    getRootDocument().assertNewDocumentAllowed(targetDetailId);
    return this;
}


public QuickInput getQuickInputOrNull(ICalloutField calloutField){
    final Object documentObj = calloutField.getModel(Object.class);
    final QuickInput quickInput = InterfaceWrapperHelper.getDynAttribute(documentObj, DYNATTR_QuickInput);
    return quickInput;
}


public void processValueChanges(List<JSONDocumentChangedEvent> events){
    quickInputDocument.processValueChanges(events, () -> "direct update from rest API");
}


public QuickInputDescriptor getQuickInputDescriptor(){
    Check.assumeNotNull(_quickInputDescriptor, "Parameter quickInputDescriptor is not null");
    return _quickInputDescriptor;
}


public T getRootDocumentAs(Class<T> modelClass){
    return InterfaceWrapperHelper.create(getRootDocument(), modelClass);
}


public JSONLookupValuesList getFieldDropdownValues(String fieldName,String adLanguage){
    return getQuickInputDocument().getFieldLookupValues(fieldName).transform(lookupValuesList -> JSONLookupValuesList.ofLookupValuesList(lookupValuesList, adLanguage));
}


public QuickInput build(){
    return new QuickInput(this);
}


public DocumentPath getDocumentPath(){
    return quickInputDocument.getDocumentPath();
}


public T getQuickInputDocumentAs(Class<T> modelClass){
    return InterfaceWrapperHelper.create(getQuickInputDocument(), modelClass);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("rootDocumentPath", rootDocumentPath).add("targetDetailId", targetDetailId).add("completed", completed).add("quickInputDocument", quickInputDocument).toString();
}


public List<Document> complete(){
    final ITrxManager trxManager = Services.get(ITrxManager.class);
    trxManager.assertThreadInheritedTrxExists();
    final IQuickInputProcessor processor = descriptor.createProcessor();
    final Set<DocumentId> documentLineIds = processor.process(this);
    final Document rootDocument = getRootDocument();
    final List<Document> includedDocumentsJustCreated = documentLineIds.stream().map(documentLineId -> rootDocument.getIncludedDocument(targetDetailId, documentLineId)).collect(ImmutableList.toImmutableList());
    this.completed = true;
    return includedDocumentsJustCreated;
}


public boolean hasField(String fieldName){
    return getQuickInputDocument().hasField(fieldName);
}


}