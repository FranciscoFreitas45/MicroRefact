package Interface;
public interface DocumentDescriptorFactory {

   public DocumentDescriptor getDocumentDescriptor(WindowId windowId);
   public DocumentEntityDescriptor getDocumentEntityDescriptor(DocumentPath documentPath);
}