package de.metas.ui.web.window.datatypes.DocumentPath;
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
public class Builder {

 private  DocumentType documentType;

 private  DocumentId documentTypeId;

 private  DocumentId documentId;

 private  boolean documentId_allowNew;

 private  DetailId detailId;

 private  Set<DocumentId> rowIds;

 private  boolean rowId_allowNull;

 private  boolean rowId_allowNew;


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


public Builder allowNewDocumentId(){
    documentId_allowNew = true;
    return this;
}


public Builder setRowIds(DocumentIdsSelection rowIds){
    this.rowIds.clear();
    this.rowIds.addAll(rowIds.toSet());
    return this;
}


public Builder setDetailId(DetailId detailId){
    this.detailId = detailId;
    return this;
}


public Builder setDocumentId(DocumentId documentId){
    this.documentId = documentId;
    return this;
}


public Builder setDocumentType(DocumentType documentType,DocumentId documentTypeId){
    this.documentType = documentType;
    this.documentTypeId = documentTypeId;
    return this;
}


public Builder setRowIdsList(String rowIdsListStr){
    return setRowIds(DocumentIdsSelection.ofCommaSeparatedString(rowIdsListStr));
}


public Builder allowNewRowId(){
    rowId_allowNew = true;
    return this;
}


}