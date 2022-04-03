package de.metas.ui.web.pattribute;
 import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import org.adempiere.mm.attributes.AttributeSetId;
import org.adempiere.mm.attributes.api.IAttributeSetInstanceBL;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.IAutoCloseable;
import org.compiere.model.I_M_AttributeSetInstance;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import de.metas.logging.LogManager;
import de.metas.ui.web.pattribute.ASIDescriptorFactory.ASIAttributeFieldBinding;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocument;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentOptions;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.ui.web.window.model.IDocumentFieldView;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
public class ASIDocument {

 private  Logger logger;

 private  ASIDescriptor descriptor;

 private  Document data;

 private  ReentrantReadWriteLock _lock;

 private  boolean completed;


public IAutoCloseable lockForReading(){
    // assume _lock is not null
    final ReadLock readLock = _lock.readLock();
    logger.debug("Acquiring read lock for {}: {}", this, readLock);
    readLock.lock();
    logger.debug("Acquired read lock for {}: {}", this, readLock);
    return () -> {
        readLock.unlock();
        logger.debug("Released read lock for {}: {}", this, readLock);
    };
}


public DocumentId getDocumentId(){
    return data.getDocumentId();
}


public IAutoCloseable lockForWriting(){
    // assume _lock is not null
    final WriteLock writeLock = _lock.writeLock();
    logger.debug("Acquiring write lock for {}: {}", this, writeLock);
    writeLock.lock();
    logger.debug("Acquired write lock for {}: {}", this, writeLock);
    return () -> {
        writeLock.unlock();
        logger.debug("Released write lock for {}: {}", this, writeLock);
    };
}


public AttributeSetId getAttributeSetId(){
    return descriptor.getAttributeSetId();
}


public void assertNotCompleted(){
    if (completed) {
        throw new IllegalStateException("ASI document was completed");
    }
}


public LookupValuesList getFieldLookupValues(String attributeName){
    return data.getFieldLookupValues(attributeName);
}


public ASILayout getLayout(){
    return descriptor.getLayout();
}


public void processValueChanges(List<JSONDocumentChangedEvent> events,ReasonSupplier reason){
    assertNotCompleted();
    data.processValueChanges(events, reason);
}


public Collection<IDocumentFieldView> getFieldViews(){
    return data.getFieldViews();
}


public LookupValuesList getFieldLookupValuesForQuery(String attributeName,String query){
    return data.getFieldLookupValuesForQuery(attributeName, query);
}


public I_M_AttributeSetInstance createM_AttributeSetInstance(ASIDocument asiDoc){
    // 
    // Create M_AttributeSetInstance
    final AttributeSetId attributeSetId = asiDoc.getAttributeSetId();
    final I_M_AttributeSetInstance asiRecord = InterfaceWrapperHelper.newInstance(I_M_AttributeSetInstance.class);
    asiRecord.setM_AttributeSet_ID(attributeSetId.getRepoId());
    // TODO: set Lot, GuaranteeDate etc
    InterfaceWrapperHelper.save(asiRecord);
    // 
    // Create M_AttributeInstances
    asiDoc.getFieldViews().stream().forEach(asiField -> asiField.getDescriptor().getDataBindingNotNull(ASIAttributeFieldBinding.class).createAndSaveM_AttributeInstance(asiRecord, asiField));
    // 
    // Update the ASI description
    Services.get(IAttributeSetInstanceBL.class).setDescription(asiRecord);
    InterfaceWrapperHelper.save(asiRecord);
    return asiRecord;
}


public JSONDocument toJSONDocument(JSONDocumentOptions options){
    return JSONDocument.ofDocument(data, options);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("data", data).add("completed", completed).toString();
}


public ASIDocument copy(CopyMode copyMode,IDocumentChangesCollector changesCollector){
    return new ASIDocument(this, copyMode, changesCollector);
}


public IntegerLookupValue complete(){
    assertNotCompleted();
    final I_M_AttributeSetInstance asiRecord = createM_AttributeSetInstance(this);
    final IntegerLookupValue lookupValue = IntegerLookupValue.of(asiRecord.getM_AttributeSetInstance_ID(), asiRecord.getDescription());
    completed = true;
    return lookupValue;
}


public ASIDocument bindContextDocumentIfPossible(DocumentCollection documentsCollection){
    final DocumentPath contextDocumentPath = descriptor.getContextDocumentPath();
    if (contextDocumentPath == null) {
        return this;
    }
    if (!documentsCollection.isWindowIdSupported(contextDocumentPath.getWindowIdOrNull())) {
        return this;
    }
    final Document contextDocument = documentsCollection.getDocumentReadonly(contextDocumentPath);
    data.setShadowParentDocumentEvaluatee(contextDocument.asEvaluatee());
    return this;
}


public boolean isCompleted(){
    return completed;
}


}