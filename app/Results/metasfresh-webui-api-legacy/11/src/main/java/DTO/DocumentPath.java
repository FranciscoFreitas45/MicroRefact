package DTO;
 import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.adempiere.ad.element.api.AdWindowId;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.exceptions.InvalidDocumentPathException;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
public class DocumentPath {

 private  DocumentType documentType;

 private  DocumentId documentTypeId;

 private  DocumentId documentId;

 private  DetailId detailId;

 private  DocumentIdsSelection rowIds;

 private  Integer _hashcode;

 private  String _toString;

 private  DocumentType documentType;

 private  DocumentId documentTypeId;

 private  DocumentId documentId;

 private  boolean documentId_allowNew;

 private  DetailId detailId;

 private  Set<DocumentId> rowIds;

 private  boolean rowId_allowNull;

 private  boolean rowId_allowNew;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public DocumentIdsSelection getRowIds(){
    return rowIds;
}


public DocumentType getDocumentType(){
    return documentType;
}


public DocumentId getSingleRowId(){
    return rowIds.getSingleDocumentId();
}


public DetailId getDetailId(){
    return detailId;
}


public DocumentId getDocumentId(){
    return documentId;
}


public WindowId getWindowIdOrNull(){
    return documentType == DocumentType.Window ? WindowId.of(documentTypeId) : null;
}


public DocumentPath getRootDocumentPath(){
    if (isRootDocument()) {
        return this;
    }
    return rootDocumentPath(documentType, documentTypeId, documentId);
}


public AdWindowId getAdWindowIdOrNull(){
    if (documentType == DocumentType.Window) {
        return AdWindowId.ofRepoIdOrNull(documentTypeId.toIntOr(-1));
    } else {
        return null;
    }
}


public DocumentId getDocumentTypeId(){
    return documentTypeId;
}


public WindowId getWindowId(){
    Check.assume(documentType == DocumentType.Window, "Expect documentType={} for {}", DocumentType.Window, this);
    return WindowId.of(documentTypeId);
}


public DocumentPath includedDocumentPath(WindowId windowId,DocumentId documentId,DetailId detailId){
    return builder().setDocumentType(windowId).setDocumentId(documentId).setDetailId(detailId).allowNullRowId().build();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/includedDocumentPath"))

.queryParam("windowId",windowId);
.queryParam("documentId",documentId);
.queryParam("detailId",detailId);
DocumentPath aux = restTemplate.getForObject(builder.toUriString(),DocumentPath.class);
return aux;
}


public DocumentPath rootDocumentPath(DocumentType documentType,DocumentId documentTypeId,DocumentId documentId){
    if (documentId == null || documentId.isNew()) {
        throw new IllegalArgumentException("new or null documentId is not accepted: " + documentId);
    }
    return new DocumentPath(documentType, documentTypeId, documentId);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/rootDocumentPath"))

.queryParam("documentType",documentType);
.queryParam("documentTypeId",documentTypeId);
.queryParam("documentId",documentId);
DocumentPath aux = restTemplate.getForObject(builder.toUriString(),DocumentPath.class);
return aux;
}


}