package Interface;
public interface DocumentCollection {

   public String cacheReset(boolean forgetNotSavedDocuments);
   public DocumentDescriptorFactory getDocumentDescriptorFactory();
}