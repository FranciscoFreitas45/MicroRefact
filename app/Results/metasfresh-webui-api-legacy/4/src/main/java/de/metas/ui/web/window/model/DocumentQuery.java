package de.metas.ui.web.window.model;
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
public class DocumentQuery {

 private  DocumentEntityDescriptor entityDescriptor;

 private  ImmutableSet<DocumentId> recordIds;

 private  Document parentDocument;

 private  DocumentFilterList filters;

 private  boolean noSorting;

 private  DocumentQueryOrderByList orderBys;

 private  int firstRow;

 private  int pageLength;

 private  Function<DocumentId,Document> existingDocumentsSupplier;

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


public Builder setPageLength(int pageLength){
    this.pageLength = pageLength;
    return this;
}


public Integer getParentLinkIdAsInt(){
    return parentDocument == null ? null : parentDocument.getDocumentIdAsInt();
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


public DocumentsRepository getDocumentsRepository(){
    return entityDescriptor.getDataBinding().getDocumentsRepository();
}


public Document retriveDocumentOrNull(){
    final DocumentQuery query = build();
    final DocumentsRepository documentsRepository = getDocumentsRepository();
    return documentsRepository.retrieveDocument(query, changesCollector);
}


public Builder ofRecordId(DocumentEntityDescriptor entityDescriptor,DocumentId recordId){
    return builder(entityDescriptor).setRecordId(recordId).noSorting();
}


public Builder setChangesCollector(IDocumentChangesCollector changesCollector){
    this.changesCollector = changesCollector;
    return this;
}


public int getFirstRow(){
    return firstRow;
}


public ImmutableSet<DocumentId> getRecordIds(){
    return recordIds;
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


public Builder builder(DocumentEntityDescriptor entityDescriptor){
    return new Builder(entityDescriptor);
}


public DocumentFilterList getFilters(){
    return filters;
}


public Builder setRecordIds(Collection<DocumentId> documentIds){
    recordIds = documentIds != null ? ImmutableSet.copyOf(documentIds) : ImmutableSet.of();
    return this;
}


public DocumentEntityDescriptor getEntityDescriptor(){
    return entityDescriptor;
}


public Builder setRecordId(DocumentId documentId){
    recordIds = documentId != null ? ImmutableSet.of(documentId) : ImmutableSet.of();
    return this;
}


public Function<DocumentId,Document> getExistingDocumentsSupplier(){
    return existingDocumentsSupplier;
}


public DocumentQueryOrderByList getOrderBysEffective(){
    return _noSorting ? DocumentQueryOrderByList.EMPTY : DocumentQueryOrderByList.ofList(_orderBys);
}


public boolean isNoSorting(){
    return _noSorting;
}


public Builder addOrderBy(DocumentQueryOrderBy orderBy){
    Check.assume(!_noSorting, "sorting not disabled for {}", this);
    if (_orderBys == null) {
        _orderBys = new ArrayList<>();
    }
    _orderBys.add(orderBy);
    return this;
}


public DocumentQueryOrderByList getOrderBys(){
    return orderBys;
}


public Builder setOrderBys(DocumentQueryOrderByList orderBys){
    if (orderBys == null || orderBys.isEmpty()) {
        _orderBys = null;
    } else {
        _orderBys = new ArrayList<>(orderBys.toList());
    }
    return this;
}


public boolean isParentLinkIdSet(){
    return parentDocument != null;
}


public DocumentQuery build(){
    return new DocumentQuery(this);
}


public Document getParentDocument(){
    return parentDocument;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("tableName", entityDescriptor.getTableNameOrNull()).add("recordIds", recordIds.isEmpty() ? null : recordIds).add("parentDocument", parentDocument).add("filters", filters.isEmpty() ? null : filters).add("firstRow", firstRow > 0 ? firstRow : null).add("pageLength", pageLength > 0 ? pageLength : null).add("noSorting", noSorting ? Boolean.TRUE : null).toString();
}


public int getPageLength(){
    return pageLength;
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