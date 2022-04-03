package de.metas.ui.web.window.model;
 import org.adempiere.ad.expression.api.LogicExpressionResult;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.exceptions.DocumentNotFoundException;
import de.metas.ui.web.window.exceptions.InvalidDocumentStateException;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.Document.OnValidStatusChanged;
import lombok.NonNull;
public class HighVolumeReadonlyIncludedDocumentsCollection implements IIncludedDocumentsCollection{

 private  Document parentDocument;

 private  DocumentEntityDescriptor entityDescriptor;

 private  LogicExpressionResult RESULT_TabReadOnly;


@Override
public void markStaleAll(){
}


@Override
public int getNextLineNo(){
    throw new UnsupportedOperationException();
}


@Override
public void assertNewDocumentAllowed(){
    throw new InvalidDocumentStateException(parentDocument, RESULT_TabReadOnly.getName());
}


@Override
public OrderedDocumentsList getDocuments(DocumentQueryOrderByList orderBys){
    return DocumentQuery.builder(entityDescriptor).setParentDocument(parentDocument).setChangesCollector(NullDocumentChangesCollector.instance).setOrderBys(orderBys).retriveDocuments();
}


@Override
public DocumentValidStatus checkAndGetValidStatus(OnValidStatusChanged onValidStatusChanged){
    return DocumentValidStatus.documentValid();
}


@Override
public OrderedDocumentsList getDocumentsByIds(DocumentIdsSelection documentIds){
    if (documentIds.isAll()) {
        return getDocuments(DocumentQueryOrderByList.EMPTY);
    } else if (documentIds.isEmpty()) {
        return OrderedDocumentsList.newEmpty();
    } else {
        final ImmutableMap<DocumentId, Document> loadedDocuments = DocumentQuery.builder(entityDescriptor).setParentDocument(parentDocument).setRecordIds(documentIds.toSet()).setChangesCollector(NullDocumentChangesCollector.instance).setOrderBys(DocumentQueryOrderByList.EMPTY).retriveDocuments().toImmutableMap();
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
}


@Override
public void updateStatusFromParent(){
// nothing
}


@Override
public LogicExpressionResult getAllowCreateNewDocument(){
    return RESULT_TabReadOnly;
}


@Override
public LogicExpressionResult getAllowDeleteDocument(){
    return RESULT_TabReadOnly;
}


@Override
public void deleteDocuments(DocumentIdsSelection documentIds){
    throw new InvalidDocumentStateException(parentDocument, RESULT_TabReadOnly.getName());
}


@Override
public boolean hasChangesRecursivelly(){
    return false;
}


@Override
public void markStale(DocumentIdsSelection rowIds){
}


@Override
public Document getDocumentById(DocumentId documentId){
    final Document document = DocumentQuery.builder(entityDescriptor).setParentDocument(parentDocument).setRecordId(documentId).retriveDocumentOrNull();
    if (document == null) {
        final DocumentPath documentPath = parentDocument.getDocumentPath().createChildPath(entityDescriptor.getDetailId(), documentId);
        throw new DocumentNotFoundException(documentPath);
    }
    return document;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("detailId", entityDescriptor.getDetailId()).toString();
}


@Override
public IIncludedDocumentsCollection copy(Document parentDocumentCopy,CopyMode copyMode){
    return this;
}


@Override
public Document createNewDocument(){
    throw new InvalidDocumentStateException(parentDocument, RESULT_TabReadOnly.getName());
}


@Override
public boolean isStale(){
    return false;
}


@Override
public DetailId getDetailId(){
    return entityDescriptor.getDetailId();
}


@Override
public void saveIfHasChanges(){
}


}