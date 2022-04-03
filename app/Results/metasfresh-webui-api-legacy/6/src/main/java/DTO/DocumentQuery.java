package DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public Integer getParentLinkIdAsInt(){
    return parentDocument == null ? null : parentDocument.getDocumentIdAsInt();
}


public DocumentsRepository getDocumentsRepository(){
    return entityDescriptor.getDataBinding().getDocumentsRepository();
}


public int getFirstRow(){
    return firstRow;
}


public ImmutableSet<DocumentId> getRecordIds(){
    return recordIds;
}


public DocumentFilterList getFilters(){
    return filters;
}


public DocumentEntityDescriptor getEntityDescriptor(){
    return entityDescriptor;
}


public Function<DocumentId,Document> getExistingDocumentsSupplier(){
    return existingDocumentsSupplier;
}


public DocumentQueryOrderByList getOrderBysEffective(){
    return _noSorting ? DocumentQueryOrderByList.EMPTY : DocumentQueryOrderByList.ofList(_orderBys);
}


public DocumentQueryOrderByList getOrderBys(){
    return orderBys;
}


public Document getParentDocument(){
    return parentDocument;
}


public int getPageLength(){
    return pageLength;
}


public boolean isNoSorting(){
    return _noSorting;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isNoSorting"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}