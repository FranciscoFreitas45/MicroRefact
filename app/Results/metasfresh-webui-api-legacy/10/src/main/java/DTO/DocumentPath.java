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


public DocumentPath withDocumentId(DocumentId id){
    if (isRootDocument()) {
        final DocumentId rootDocumentIdNew = id;
        if (Objects.equals(documentId, rootDocumentIdNew)) {
            return this;
        } else {
            return new DocumentPath(documentType, documentTypeId, rootDocumentIdNew);
        }
    } else {
        final DocumentId rowIdNew = id;
        final DocumentId rowId = getSingleRowId();
        if (Objects.equals(rowId, rowIdNew)) {
            return this;
        } else {
            return new DocumentPath(documentType, documentTypeId, documentId, detailId, rowIdNew);
        }
    }
}


public DocumentIdsSelection getRowIds(){
    return rowIds;
}


public DocumentType getDocumentType(){
    return documentType;
}


public Builder allowNewDocumentId(){
    documentId_allowNew = true;
    return this;
}


public Builder setDocumentId(DocumentId documentId){
    this.documentId = documentId;
    return this;
}


public Builder setRowIdsList(String rowIdsListStr){
    return setRowIds(DocumentIdsSelection.ofCommaSeparatedString(rowIdsListStr));
}


public Builder allowNewRowId(){
    rowId_allowNew = true;
    return this;
}


public DocumentPath includedDocumentPath(WindowId windowId,DocumentId documentId,DetailId detailId){
    return builder().setDocumentType(windowId).setDocumentId(documentId).setDetailId(detailId).allowNullRowId().build();
}


public boolean isComposedKey(){
    if (detailId == null) {
        return documentId != null && documentId.isComposedKey();
    } else {
        return rowIds != null && rowIds.isSingleDocumentId() && rowIds.getSingleDocumentId().isComposedKey();
    }
}


public boolean isRootDocument(){
    return detailId == null && rowIds.isEmpty();
}


public DocumentId getSingleRowId(){
    return rowIds.getSingleDocumentId();
}


@Override
public int hashCode(){
    if (_hashcode == null) {
        _hashcode = Objects.hash(documentType, documentTypeId, documentId, detailId, rowIds);
    }
    return _hashcode;
}


public Builder builder(){
    return new Builder();
}


public Builder setDetailId(DetailId detailId){
    this.detailId = detailId;
    return this;
}


public Builder setDocumentType(DocumentType documentType,DocumentId documentTypeId){
    this.documentType = documentType;
    this.documentTypeId = documentTypeId;
    return this;
}


public DetailId getDetailId(){
    return detailId;
}


public boolean isSingleIncludedDocument(){
    if (detailId == null) {
        return false;
    }
    if (!rowIds.isSingleDocumentId()) {
        return false;
    }
    final DocumentId singleRowId = rowIds.getSingleDocumentId();
    if (singleRowId.isNew()) {
        return false;
    }
    return true;
}


public DocumentId getDocumentId(){
    return documentId;
}


public DocumentPath singleWindowDocumentPath(WindowId windowId,DocumentId id,DetailId detailId,DocumentId rowId){
    return builder().setDocumentType(windowId).setDocumentId(id).setDetailId(detailId).setRowId(rowId).build();
}


public boolean hasIncludedDocuments(){
    if (detailId == null) {
        return false;
    }
    return !rowIds.isEmpty();
}


public boolean isNewDocument(){
    return documentId != null && documentId.isNew();
}


public Builder setRowIds(DocumentIdsSelection rowIds){
    this.rowIds.clear();
    this.rowIds.addAll(rowIds.toSet());
    return this;
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


public List<DocumentPath> rootDocumentPathsList(WindowId windowId,String documentIdsListStr){
    if (documentIdsListStr == null || documentIdsListStr.isEmpty()) {
        return ImmutableList.of();
    }
    return DocumentIdsSelection.ofCommaSeparatedString(documentIdsListStr).stream().map(documentId -> rootDocumentPath(windowId, documentId)).collect(GuavaCollectors.toImmutableList());
}


public boolean isAnyIncludedDocument(){
    if (detailId == null) {
        return false;
    }
    return rowIds.isEmpty();
}


public Builder allowNullRowId(){
    rowId_allowNull = true;
    return this;
}


public DocumentPath build(){
    if (documentType == null) {
        throw new InvalidDocumentPathException("Invalid document type: " + documentType);
    }
    // 
    // Validate documentTypeId
    if (documentTypeId == null) {
        throw new InvalidDocumentPathException("Invalid document type ID: " + documentTypeId);
    }
    // 
    // Validate documentId
    if (documentId == null) {
        throw new InvalidDocumentPathException("id cannot be null");
    }
    if (!documentId_allowNew && documentId != null && documentId.isNew()) {
        throw new InvalidDocumentPathException("id cannot be NEW");
    }
    // 
    // Validate rowId
    if (!rowId_allowNull && detailId != null && rowIds.isEmpty()) {
        throw new InvalidDocumentPathException("rowId cannot be null when detailId=" + detailId);
    }
    if (!rowId_allowNew) {
        for (final DocumentId rowId : rowIds) {
            if (rowId.isNew()) {
                throw new InvalidDocumentPathException("rowId cannot be NEW");
            }
        }
    }
    // 
    // Validate detailId
    if (detailId == null && !rowIds.isEmpty()) {
        throw new InvalidDocumentPathException("detailId cannot be null when we have rowIds: " + rowIds);
    }
    // 
    // Create & return the document path
    return new DocumentPath(documentType, documentTypeId, documentId, detailId, DocumentIdsSelection.of(rowIds));
}


public Builder setRowId(DocumentId rowId){
    rowIds.clear();
    if (rowId != null) {
        rowIds.add(rowId);
    }
    return this;
}


public DocumentId getDocumentTypeId(){
    return documentTypeId;
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (!(obj instanceof DocumentPath)) {
        return false;
    }
    final DocumentPath other = (DocumentPath) obj;
    return Objects.equals(documentType, other.documentType) && Objects.equals(documentTypeId, other.documentTypeId) && Objects.equals(documentId, other.documentId) && Objects.equals(detailId, other.detailId) && Objects.equals(rowIds, other.rowIds);
}


public DocumentPath createChildPath(DetailId detailId,DocumentId rowId){
    if (detailId == null) {
        throw new IllegalArgumentException("detailId must be not empty");
    }
    if (rowId == null || rowId.isNew()) {
        throw new IllegalArgumentException("new or null rowId is not accepted: " + rowId);
    }
    return new DocumentPath(documentType, documentTypeId, documentId, detailId, rowId);
}


public DocumentPath rootDocumentPath(DocumentType documentType,DocumentId documentTypeId,DocumentId documentId){
    if (documentId == null || documentId.isNew()) {
        throw new IllegalArgumentException("new or null documentId is not accepted: " + documentId);
    }
    return new DocumentPath(documentType, documentTypeId, documentId);
}


@Override
public String toString(){
    if (_toString == null) {
        final StringBuilder sb = new StringBuilder();
        sb.append("/").append(documentType.getSymbol()).append(documentTypeId).append("/").append(documentId);
        if (detailId != null) {
            sb.append("/T").append(detailId);
        }
        if (rowIds.isSingleDocumentId()) {
            sb.append("/R").append(rowIds.getSingleDocumentId());
        } else if (!rowIds.isEmpty()) {
            sb.append("/R").append(rowIds.toCommaSeparatedString());
        }
        _toString = sb.toString();
    }
    return _toString;
}


public boolean isSingleNewIncludedDocument(){
    if (detailId == null) {
        return false;
    }
    if (!rowIds.isSingleDocumentId()) {
        return false;
    }
    final DocumentId singleRowId = rowIds.getSingleDocumentId();
    if (!singleRowId.isNew()) {
        return false;
    }
    return true;
}


public WindowId getWindowId(){
    Check.assume(documentType == DocumentType.Window, "Expect documentType={} for {}", DocumentType.Window, this);
    return WindowId.of(documentTypeId);
}


}