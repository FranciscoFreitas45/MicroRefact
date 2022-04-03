package de.metas.ui.web.pattribute;
 import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import org.adempiere.mm.attributes.api.IAttributeDAO;
import org.adempiere.util.lang.IAutoCloseable;
import org.compiere.model.I_M_Attribute;
import org.compiere.model.I_M_AttributeInstance;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import com.google.common.collect.ImmutableMap;
import de.metas.cache.CCache;
import de.metas.logging.LogManager;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.pattribute.ASIDescriptorFactory.ASIAttributeFieldBinding;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.NullDocumentChangesCollector;
import de.metas.util.Services;
import lombok.NonNull;
@Repository
public class ASIRepository {

 private  Logger logger;

 private  ITrxManager trxManager;

 private  IAttributeDAO attributesRepo;

 private  ASIDescriptorFactory descriptorsFactory;

 private  Supplier<DocumentId> nextASIDocId;

 private  CCache<DocumentId,ASIDocument> id2asiDoc;


public ASIDocument createNewFrom(WebuiASIEditingInfo info){
    // 
    // Get the ASI descriptor
    final ASIDescriptor asiDescriptor = descriptorsFactory.getASIDescriptor(info);
    // 
    // Create the new ASI document
    final AttributeSetInstanceId templateAsiId = info.getAttributeSetInstanceId();
    final Document asiDocData = Document.builder(asiDescriptor.getEntityDescriptor()).initializeAsNewDocument(createASIDocumentValuesSupplier(templateAsiId, asiDescriptor.getEntityDescriptor()));
    // 
    // Validate, log and add the new ASI document to our index
    asiDocData.checkAndGetValidStatus();
    logger.trace("Created from ASI={}: {}", templateAsiId, asiDocData);
    final ASIDocument asiDoc = new ASIDocument(asiDescriptor, asiDocData);
    commit(asiDoc);
    return asiDoc;
}


public ASIDocument loadReadonly(AttributeSetInstanceId attributeSetInstanceId){
    if (!attributeSetInstanceId.isRegular()) {
        throw new EntityNotFoundException("ASI " + attributeSetInstanceId);
    }
    // 
    // Get the ASI descriptor
    final WebuiASIEditingInfo info = WebuiASIEditingInfo.readonlyASI(attributeSetInstanceId);
    final ASIDescriptor asiDescriptor = descriptorsFactory.getASIDescriptor(info);
    // 
    // Create the new ASI document
    final Document asiDocData = Document.builder(asiDescriptor.getEntityDescriptor()).initializeAsNewDocument(createASIDocumentValuesSupplier(attributeSetInstanceId, asiDescriptor.getEntityDescriptor()));
    // 
    // Validate, log and add the new ASI document to our index
    asiDocData.checkAndGetValidStatus();
    logger.trace("Created from ASI={}: {}", attributeSetInstanceId, asiDocData);
    final ASIDocument asiDoc = new ASIDocument(asiDescriptor, asiDocData);
    return asiDoc.copy(CopyMode.CheckInReadonly, NullDocumentChangesCollector.instance);
}


public R forASIDocumentWritable(DocumentId asiDocId,IDocumentChangesCollector changesCollector,DocumentCollection documentsCollection,Function<ASIDocument,R> processor){
    try (final IAutoCloseable readLock = getASIDocumentNoLock(asiDocId).lockForWriting()) {
        final ASIDocument asiDoc = getASIDocumentNoLock(asiDocId).copy(CopyMode.CheckOutWritable, changesCollector).bindContextDocumentIfPossible(documentsCollection);
        final R result = processor.apply(asiDoc);
        trxManager.runAfterCommit(() -> commit(asiDoc));
        return result;
    }
}


public Map<String,Object> retrieveASIValuesMap(AttributeSetInstanceId asiId,DocumentEntityDescriptor descriptor){
    if (asiId.isNone()) {
        return ImmutableMap.of();
    }
    final HashMap<String, Object> valuesMap = new HashMap<>();
    for (final I_M_AttributeInstance fromAI : attributesRepo.retrieveAttributeInstances(asiId)) {
        final I_M_Attribute attribute = attributesRepo.getAttributeById(fromAI.getM_Attribute_ID());
        final String fieldName = attribute.getValue();
        final DocumentFieldDescriptor fieldDescriptor = descriptor.getFieldOrNull(fieldName);
        // Skip loading the attribute instance if it's no longer exist.
        // This can happen if we are trying to load an old ASI but in meantime the AttributeSet was changed and the attribute was removed or deactivated.
        if (fieldDescriptor == null) {
            logger.warn("Attribute {} no longer exist in {}", fieldName, descriptor);
            continue;
        }
        final Object value = fieldDescriptor.getDataBindingNotNull(ASIAttributeFieldBinding.class).readValue(fromAI);
        valuesMap.put(fieldName, value);
    }
    return valuesMap;
}


public void commit(ASIDocument asiDoc){
    final DocumentId asiDocId = asiDoc.getDocumentId();
    if (asiDoc.isCompleted()) {
        final ASIDocument asiDocRemoved = id2asiDoc.remove(asiDocId);
        logger.trace("Removed from repository by ID={}: {}", asiDocId, asiDocRemoved);
    } else {
        final ASIDocument asiDocReadonly = asiDoc.copy(CopyMode.CheckInReadonly, NullDocumentChangesCollector.instance);
        id2asiDoc.put(asiDocId, asiDocReadonly);
        logger.trace("Added to repository: {}", asiDocReadonly);
    }
}


public ASIDocument getASIDocumentNoLock(DocumentId asiDocId){
    final ASIDocument asiDoc = id2asiDoc.get(asiDocId);
    if (asiDoc == null) {
        throw new EntityNotFoundException("No product attributes found for asiId=" + asiDocId);
    }
    return asiDoc;
}


public ASIDocumentValuesSupplier createASIDocumentValuesSupplier(AttributeSetInstanceId asiId,DocumentEntityDescriptor descriptor){
    return ASIDocumentValuesSupplier.builder().documentIdSupplier(nextASIDocId).values(retrieveASIValuesMap(asiId, descriptor)).build();
}


public R forASIDocumentReadonly(DocumentId asiDocId,DocumentCollection documentsCollection,Function<ASIDocument,R> processor){
    try (final IAutoCloseable readLock = getASIDocumentNoLock(asiDocId).lockForReading()) {
        final ASIDocument asiDoc = getASIDocumentNoLock(asiDocId).copy(CopyMode.CheckInReadonly, NullDocumentChangesCollector.instance).bindContextDocumentIfPossible(documentsCollection);
        return processor.apply(asiDoc);
    }
}


}