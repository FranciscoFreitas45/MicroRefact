package Interface;
public interface DocumentCollection {

   public void invalidateDocumentByRecordId(String tableName,int recordId);
   public String cacheReset(boolean forgetNotSavedDocuments);
}