package Interface;
public interface DocumentDescriptorFactory {

   public DocumentEntityDescriptor getDocumentEntityDescriptor(DocumentPath documentPath);
   public String getTableNameOrNull(int AD_Window_ID,DetailId detailId);
}