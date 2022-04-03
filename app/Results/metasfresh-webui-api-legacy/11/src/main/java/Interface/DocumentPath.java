package Interface;
public interface DocumentPath {

   public DocumentPath includedDocumentPath(WindowId windowId,DocumentId documentId,DetailId detailId);
   public DocumentPath rootDocumentPath(DocumentType documentType,DocumentId documentTypeId,DocumentId documentId);
}