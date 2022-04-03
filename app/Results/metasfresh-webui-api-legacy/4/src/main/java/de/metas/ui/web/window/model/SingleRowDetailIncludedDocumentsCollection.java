package de.metas.ui.web.window.model;
 import org.adempiere.ad.expression.api.LogicExpressionResult;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.exceptions.InvalidDocumentStateException;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.Document.OnValidStatusChanged;
import lombok.NonNull;
public class SingleRowDetailIncludedDocumentsCollection implements IIncludedDocumentsCollection{

 private  Logger logger;

 private  Document parentDocument;

 private  DocumentEntityDescriptor entityDescriptor;

 private  Document singleDocument;

 private  boolean staled;

 private  DocumentPath parentDocumentPath;

 private  LogicExpressionResult RESULT_TabSingleRowDetail;


@Override
public void markStaleAll(){
    staled = true;
    parentDocument.getChangesCollector().collectStaleDetailId(parentDocumentPath, getDetailId());
}


@Override
public int getNextLineNo(){
    return 0;
}


public void forgetSingleDocument(){
    singleDocument = null;
}


@Override
public void assertNewDocumentAllowed(){
    throw new InvalidDocumentStateException(parentDocument, RESULT_TabSingleRowDetail.getName());
}


@Override
public OrderedDocumentsList getDocuments(DocumentQueryOrderByList orderBys){
    final Document document = DocumentQuery.builder(entityDescriptor).setParentDocument(parentDocument).setChangesCollector(NullDocumentChangesCollector.instance).setOrderBys(orderBys).retriveDocumentOrNull();
    setSingleDocument(document);
    return OrderedDocumentsList.of(ImmutableList.of(document), orderBys);
}


@Override
public DocumentValidStatus checkAndGetValidStatus(OnValidStatusChanged onValidStatusChanged){
    if (singleDocument != null) {
        final DocumentValidStatus validState = singleDocument.checkAndGetValidStatus(onValidStatusChanged);
        if (!validState.isValid()) {
            logger.trace("Considering included document as invalid for saving because it is not valid; validState={}; document={}", validState, singleDocument);
            return validState;
        }
    }
    return DocumentValidStatus.documentValid();
}


public Document getSingleDocumentOrNull(){
    return singleDocument;
}


@Override
public OrderedDocumentsList getDocumentsByIds(DocumentIdsSelection documentIds){
    final ImmutableMap<DocumentId, Document> loadedDocuments = getDocuments(DocumentQueryOrderByList.EMPTY).toImmutableMap();
    final OrderedDocumentsList result = OrderedDocumentsList.newEmpty();
    for (final DocumentId documentId : documentIds.toSet()) {
        final Document loadedDocument = loadedDocuments.get(documentId);
        if (loadedDocument != null) {
            result.addDocument(loadedDocument);
        } else {
        // No document found for documentId. Ignore it.
        }
    }
    return result;
}


@Override
public void updateStatusFromParent(){
// nothing
}


@Override
public LogicExpressionResult getAllowCreateNewDocument(){
    return RESULT_TabSingleRowDetail;
}


@Override
public LogicExpressionResult getAllowDeleteDocument(){
    return RESULT_TabSingleRowDetail;
}


@Override
public void deleteDocuments(DocumentIdsSelection documentIds){
    throw new InvalidDocumentStateException(parentDocument, RESULT_TabSingleRowDetail.getName());
}


@Override
public boolean hasChangesRecursivelly(){
    if (singleDocument == null) {
        return false;
    }
    return singleDocument.hasChangesRecursivelly();
}


@Override
public void markStale(DocumentIdsSelection rowIds){
    markStaleAll();
}


@Override
public Document getDocumentById(DocumentId documentId){
    // Try documents which are new and/or have changes in progress, but are not yet saved
    final Document singleDocument = getSingleDocumentOrNull();
    if (singleDocument != null) {
        return singleDocument;
    }
    final Document document = DocumentQuery.builder(entityDescriptor).setParentDocument(parentDocument).retriveDocumentOrNull();
    setSingleDocument(document);
    return document;
}


@Override
public IIncludedDocumentsCollection copy(Document parentDocumentCopy,CopyMode copyMode_IGNORED){
    return new SingleRowDetailIncludedDocumentsCollection(this, parentDocumentCopy);
}


@Override
public Document createNewDocument(){
    throw new InvalidDocumentStateException(parentDocument, RESULT_TabSingleRowDetail.getName());
}


public void setSingleDocument(Document document){
    singleDocument = document;
}


@Override
public boolean isStale(){
    return staled;
}


@Override
public DetailId getDetailId(){
    return entityDescriptor.getDetailId();
}


@Override
public void saveIfHasChanges(){
    if (singleDocument != null) {
        final DocumentSaveStatus saveStatus = singleDocument.saveIfHasChanges();
        if (saveStatus.isSaved()) {
            forgetSingleDocument();
        } else if (saveStatus.isDeleted()) {
            singleDocument.markAsDeleted();
            forgetSingleDocument();
        }
    }
}


}