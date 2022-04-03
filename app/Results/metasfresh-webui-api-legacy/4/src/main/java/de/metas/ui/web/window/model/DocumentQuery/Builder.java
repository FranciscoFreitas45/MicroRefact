package de.metas.ui.web.window.model.DocumentQuery;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import javax.annotation.Nullable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.window.controller.Execution;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.util.Check;
import lombok.NonNull;
public class Builder {

 private  DocumentEntityDescriptor entityDescriptor;

 private  Document parentDocument;

 private  Set<DocumentId> recordIds;

 public  ArrayList<DocumentFilter> filters;

 private  boolean _noSorting;

 public  ArrayList<DocumentQueryOrderBy> _orderBys;

 private  int firstRow;

 private  int pageLength;

 private  Function<DocumentId,Document> existingDocumentsSupplier;

 private  IDocumentChangesCollector changesCollector;


public Builder setRecordId(DocumentId documentId){
    recordIds = documentId != null ? ImmutableSet.of(documentId) : ImmutableSet.of();
    return this;
}


public Builder setPageLength(int pageLength){
    this.pageLength = pageLength;
    return this;
}


public DocumentId retrieveParentDocumentId(DocumentEntityDescriptor parentEntityDescriptor){
    final DocumentQuery query = build();
    final DocumentsRepository documentsRepository = getDocumentsRepository();
    return documentsRepository.retrieveParentDocumentId(parentEntityDescriptor, query);
}


public Builder setExistingDocumentsSupplier(Function<DocumentId,Document> existingDocumentsSupplier){
    this.existingDocumentsSupplier = existingDocumentsSupplier;
    return this;
}


public Builder setFirstRow(int firstRow){
    this.firstRow = firstRow;
    return this;
}


public DocumentQueryOrderByList getOrderBysEffective(){
    return _noSorting ? DocumentQueryOrderByList.EMPTY : DocumentQueryOrderByList.ofList(_orderBys);
}


public boolean isNoSorting(){
    return _noSorting;
}


public DocumentsRepository getDocumentsRepository(){
    return entityDescriptor.getDataBinding().getDocumentsRepository();
}


public Document retriveDocumentOrNull(){
    final DocumentQuery query = build();
    final DocumentsRepository documentsRepository = getDocumentsRepository();
    return documentsRepository.retrieveDocument(query, changesCollector);
}


public Builder addOrderBy(DocumentQueryOrderBy orderBy){
    Check.assume(!_noSorting, "sorting not disabled for {}", this);
    if (_orderBys == null) {
        _orderBys = new ArrayList<>();
    }
    _orderBys.add(orderBy);
    return this;
}


public Builder setChangesCollector(IDocumentChangesCollector changesCollector){
    this.changesCollector = changesCollector;
    return this;
}


public Builder setOrderBys(DocumentQueryOrderByList orderBys){
    if (orderBys == null || orderBys.isEmpty()) {
        _orderBys = null;
    } else {
        _orderBys = new ArrayList<>(orderBys.toList());
    }
    return this;
}


public DocumentQuery build(){
    return new DocumentQuery(this);
}


public Builder noSorting(){
    _noSorting = true;
    _orderBys = null;
    return this;
}


public int retrieveLastLineNo(){
    final DocumentQuery query = build();
    final DocumentsRepository documentsRepository = getDocumentsRepository();
    return documentsRepository.retrieveLastLineNo(query);
}


public Builder setRecordIds(Collection<DocumentId> documentIds){
    recordIds = documentIds != null ? ImmutableSet.copyOf(documentIds) : ImmutableSet.of();
    return this;
}


public Builder setParentDocument(Document parentDocument){
    this.parentDocument = parentDocument;
    return this;
}


public OrderedDocumentsList retriveDocuments(){
    final DocumentQuery query = build();
    final DocumentsRepository documentsRepository = getDocumentsRepository();
    return documentsRepository.retrieveDocuments(query, changesCollector);
}


}