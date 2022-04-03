package de.metas.ui.web.window.model;
 import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import lombok.NonNull;
import javax.annotation.Nullable;
public interface DocumentsRepository {


public Document retrieveDocument(DocumentQuery query,IDocumentChangesCollector changesCollector)
;

public void assertThisRepository(DocumentEntityDescriptor entityDescriptor){
    final DocumentsRepository documentsRepository = entityDescriptor.getDataBinding().getDocumentsRepository();
    if (documentsRepository != this) {
        // shall not happen
        throw new IllegalArgumentException("Entity descriptor's repository is invalid: " + entityDescriptor + "\n Expected: " + this + "\n But it was: " + documentsRepository);
    }
}
;

public OrderedDocumentsList retrieveDocuments(DocumentQuery query,IDocumentChangesCollector changesCollector)
;

public DocumentId retrieveParentDocumentId(DocumentEntityDescriptor parentEntityDescriptor,DocumentQuery childDocumentQuery)
;

public Document retrieveDocumentById(DocumentEntityDescriptor entityDescriptor,DocumentId recordId,IDocumentChangesCollector changesCollector){
    return retrieveDocument(DocumentQuery.ofRecordId(entityDescriptor, recordId).setChangesCollector(changesCollector).build(), changesCollector);
}
;

public int retrieveLastLineNo(DocumentQuery query)
;

public SaveResult save(Document document)
;

public void refresh(Document document)
;

public String retrieveVersion(DocumentEntityDescriptor entityDescriptor,int documentIdAsInt)
;

public Document createNewDocument(DocumentEntityDescriptor entityDescriptor,Document parentDocument,IDocumentChangesCollector changesCollector)
;

public void delete(Document document)
;

}