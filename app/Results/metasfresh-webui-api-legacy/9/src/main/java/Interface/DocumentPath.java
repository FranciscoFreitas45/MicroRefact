package Interface;
public interface DocumentPath {

   public DocumentId getDocumentId();
   public DocumentPath rootDocumentPath(DocumentType documentType,DocumentId documentTypeId,DocumentId documentId);
}