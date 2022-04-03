package de.metas.ui.web.window.model;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import org.compiere.util.Evaluatee;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.exceptions.DocumentNotFoundException;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.Document.OnValidStatusChanged;
import lombok.AllArgsConstructor;
import lombok.NonNull;
public class HighVolumeReadWriteIncludedDocumentsCollection implements IIncludedDocumentsCollection{

 private  Logger logger;

 private  DocumentEntityDescriptor entityDescriptor;

 private  Document parentDocument;

 private  DetailId detailId;

 private  DocumentPath parentDocumentPath;

 private  LinkedHashMap<DocumentId,Document> _documentsWithChanges;

 private  IncludedDocumentsCollectionActions actions;

 private  ActionsContext actionsContext;

 private  DocumentReadonly parentReadonly;

 private  boolean staled;


public void addChangedDocument(Document document){
    final DocumentId documentId = document.getDocumentId();
    _documentsWithChanges.put(documentId, document);
}


public void setParentReadonlyAndCollect(DocumentReadonly parentReadonlyNew){
    // might be null
    final DocumentReadonly parentReadonlyOld = parentReadonly;
    parentReadonly = parentReadonlyNew;
    if (parentReadonlyOld == null || !parentReadonlyNew.equals(parentReadonlyOld)) {
        parentDocument.getChangesCollector().collectStaleDetailId(parentDocumentPath, detailId);
    }
    getChangedDocuments().forEach(changedDocument -> changedDocument.setParentReadonly(parentReadonlyNew));
}


@Override
public int getNextLineNo(){
    final int lastLineNo = DocumentQuery.builder(entityDescriptor).setParentDocument(parentDocument).setExistingDocumentsSupplier(this::getChangedDocumentOrNull).setChangesCollector(NullDocumentChangesCollector.instance).retrieveLastLineNo();
    final int nextLineNo = lastLineNo / 10 * 10 + 10;
    return nextLineNo;
}


@Override
public OrderedDocumentsList getDocumentsByIds(DocumentIdsSelection documentIds){
    if (documentIds.isAll()) {
        return getDocuments(DocumentQueryOrderByList.EMPTY);
    } else if (documentIds.isEmpty()) {
        return OrderedDocumentsList.newEmpty();
    } else {
        final Map<DocumentId, Document> documentsWithChanges = new LinkedHashMap<>(getInnerDocumentsWithChanges());
        final SetView<DocumentId> documentIdsToLoad = Sets.difference(documentIds.toSet(), documentsWithChanges.keySet());
        final ImmutableMap<DocumentId, Document> loadedDocuments;
        if (!documentIdsToLoad.isEmpty()) {
            loadedDocuments = DocumentQuery.builder(entityDescriptor).setParentDocument(parentDocument).setRecordIds(documentIdsToLoad).setChangesCollector(NullDocumentChangesCollector.instance).setOrderBys(DocumentQueryOrderByList.EMPTY).retriveDocuments().toImmutableMap();
        } else {
            loadedDocuments = ImmutableMap.of();
        }
        final OrderedDocumentsList result = OrderedDocumentsList.newEmpty();
        for (final DocumentId documentId : documentIds.toSet()) {
            final Document documentWithChanges = documentsWithChanges.get(documentId);
            if (documentWithChanges != null) {
                result.addDocument(documentWithChanges);
                continue;
            }
            final Document loadedDocument = loadedDocuments.get(documentId);
            if (loadedDocument != null) {
                result.addDocument(loadedDocument);
                continue;
            }
        // No document found for documentId. Ignore it.
        }
        return result;
    }
}


@Override
public LogicExpressionResult getAllowCreateNewDocument(){
    return actions.getAllowCreateNewDocument();
}


@Override
public void deleteDocuments(DocumentIdsSelection documentIds){
    if (documentIds.isEmpty()) {
        throw new IllegalArgumentException("At least one rowId shall be specified when deleting included documents");
    }
    assertWritable();
    final List<Document> deletedDocuments = new ArrayList<>();
    for (final DocumentId documentId : documentIds.toSet()) {
        final Document document = getDocumentById(documentId);
        // assertDeleteDocumentAllowed(document);
        // Delete it from underlying repository (if it's present there)
        if (!document.isNew()) {
            document.deleteFromRepository();
        }
        document.markAsDeleted();
        forgetChangedDocument(documentId);
        deletedDocuments.add(document);
    }
    // FIXME: workaround until https://github.com/metasfresh/metasfresh-webui-api/issues/19 is implemented
    // Case: an C_OrderLine is deleted and some other order lines are updated because of that.
    // We need to invalidate them...
    if (!deletedDocuments.isEmpty()) {
        markStaleAll();
    }
}


@Override
public boolean isParentDocumentNew(){
    return parentDocument.isNew();
}


public Document getChangedDocumentOrNull(DocumentId documentId){
    return _documentsWithChanges.get(documentId);
}


@Override
public boolean hasChangesRecursivelly(){
    return getChangedDocuments().stream().anyMatch(document -> document.hasChangesRecursivelly());
}


public Collection<Document> getChangedDocuments(){
    return _documentsWithChanges.values();
}


@Override
public void markStale(DocumentIdsSelection rowIds){
    // TODO: implement staling only given rowId
    markStaleAll();
}


@Override
public Document getDocumentById(DocumentId documentId){
    // Try documents which are new and/or have changes in progress, but are not yet saved
    final Document documentWithChanges = getChangedDocumentOrNull(documentId);
    if (documentWithChanges != null) {
        return documentWithChanges;
    }
    // Retrieve from repository
    final Document documentRetrieved = DocumentQuery.builder(entityDescriptor).setParentDocument(parentDocument).setRecordId(documentId).setChangesCollector(NullDocumentChangesCollector.instance).retriveDocumentOrNull();
    if (documentRetrieved == null) {
        final DocumentPath documentPath = parentDocumentPath.createChildPath(detailId, documentId);
        throw new DocumentNotFoundException(documentPath);
    }
    return documentRetrieved.copy(parentDocument, parentDocument.isWritable() ? CopyMode.CheckOutWritable : CopyMode.CheckInReadonly);
}


@Override
public IIncludedDocumentsCollection copy(Document parentDocumentCopy,CopyMode copyMode){
    return new HighVolumeReadWriteIncludedDocumentsCollection(this, parentDocumentCopy, copyMode);
}


@Override
public Document createNewDocument(){
    assertWritable();
    assertNewDocumentAllowed();
    final DocumentsRepository documentsRepository = entityDescriptor.getDataBinding().getDocumentsRepository();
    final IDocumentChangesCollector changesCollector = parentDocument.getChangesCollector();
    final Document document = documentsRepository.createNewDocument(entityDescriptor, parentDocument, changesCollector);
    // Collect all changes to make sure the whole document content will be provided to frontend
    changesCollector.collectFrom(document, () -> "new included document");
    addChangedDocument(document);
    return document;
}


public Map<DocumentId,Document> getInnerDocumentsWithChanges(){
    return _documentsWithChanges;
}


@Override
public DetailId getDetailId(){
    return detailId;
}


@Override
public void markStaleAll(){
    staled = true;
    parentDocument.getChangesCollector().collectStaleDetailId(parentDocumentPath, detailId);
}


@Override
public void onChildChanged(Document document){
    // NOTE: we assume the document has changes
    // if(document.hasChangesRecursivelly())
    addChangedDocument(document);
}


@Override
public void assertNewDocumentAllowed(){
    actions.updateAndAssertAlowCreateNew(actionsContext);
}


@Override
public boolean isParentDocumentInvalid(){
    return !parentDocument.getValidStatus().isValid();
}


@Override
public OrderedDocumentsList getDocuments(DocumentQueryOrderByList orderBys){
    final Map<DocumentId, Document> documentsWithChanges = new LinkedHashMap<>(getInnerDocumentsWithChanges());
    final OrderedDocumentsList documents = DocumentQuery.builder(entityDescriptor).setParentDocument(parentDocument).setExistingDocumentsSupplier(documentsWithChanges::remove).setChangesCollector(NullDocumentChangesCollector.instance).setOrderBys(orderBys).retriveDocuments();
    // Add the remaining documents with changes if any
    // i.e. those documents which are new and never saved in database.
    if (!documentsWithChanges.isEmpty()) {
        documents.addDocuments(documentsWithChanges.values());
    }
    staled = false;
    return documents;
}


public HighVolumeReadWriteIncludedDocumentsCollection newInstance(Document parentDocument,DocumentEntityDescriptor entityDescriptor){
    final HighVolumeReadWriteIncludedDocumentsCollection col = new HighVolumeReadWriteIncludedDocumentsCollection(parentDocument, entityDescriptor);
    col.updateStatusFromParent();
    return col;
}


@Override
public DocumentValidStatus checkAndGetValidStatus(OnValidStatusChanged onValidStatusChanged){
    for (final Document document : getChangedDocuments()) {
        final DocumentValidStatus validState = document.checkAndGetValidStatus(onValidStatusChanged);
        if (!validState.isValid()) {
            logger.trace("Considering included documents collection {} as invalid for saving because {} is not valid", this, document, validState);
            return validState;
        }
    }
    return DocumentValidStatus.documentValid();
}


public void assertWritable(){
    parentDocument.assertWritable();
}


@Override
public void updateStatusFromParent(){
    setParentReadonlyAndCollect(parentDocument.getReadonly());
    actions.updateAndGetAllowCreateNewDocument(actionsContext);
    actions.updateAndGetAllowDeleteDocument(actionsContext);
}


@Override
public Evaluatee toEvaluatee(){
    return parentDocument.asEvaluatee();
}


@Override
public void collectAllowNew(DocumentPath parentDocumentPath,DetailId detailId,LogicExpressionResult allowNew){
    parentDocument.getChangesCollector().collectAllowNew(parentDocumentPath, detailId, allowNew);
}


@Override
public LogicExpressionResult getAllowDeleteDocument(){
    return actions.getAllowDeleteDocument();
}


@Override
public boolean isParentDocumentActive(){
    return parentDocument.isActive();
}


@Override
public boolean isParentDocumentProcessed(){
    return parentDocument.isProcessed();
}


@Override
public String toString(){
    // NOTE: keep it short
    return MoreObjects.toStringHelper(this).add("parentDocumentPath", parentDocumentPath).add("detailId", detailId).toString();
}


@Override
public void onChildSaved(Document document){
    if (!document.hasChangesRecursivelly()) {
        forgetChangedDocument(document.getDocumentId());
    }
}


@Override
public boolean isStale(){
    return staled;
}


@Override
public void collectAllowDelete(DocumentPath parentDocumentPath,DetailId detailId,LogicExpressionResult allowDelete){
    parentDocument.getChangesCollector().collectAllowDelete(parentDocumentPath, detailId, allowDelete);
}


@Override
public void saveIfHasChanges(){
    final Set<DocumentId> savedOrDeletedDocumentIds = new HashSet<>();
    for (final Document document : getChangedDocuments()) {
        final DocumentSaveStatus saveStatus = document.saveIfHasChanges();
        if (saveStatus.isSaved()) {
            savedOrDeletedDocumentIds.add(document.getDocumentId());
        } else if (saveStatus.isDeleted()) {
            document.markAsDeleted();
            savedOrDeletedDocumentIds.add(document.getDocumentId());
        }
    }
    if (!savedOrDeletedDocumentIds.isEmpty()) {
        savedOrDeletedDocumentIds.forEach(this::forgetChangedDocument);
    }
}


public void forgetChangedDocument(DocumentId documentId){
    _documentsWithChanges.remove(documentId);
}


@Override
public Collection<Document> getIncludedDocuments(){
    return getChangedDocuments();
}


}