package Interface;
public interface DocumentCollection {

   public TableRecordReference getTableRecordReference(DocumentPath documentPath);
   public Object getModel(Object Object);
   public void invalidateDocumentByRecordId(String tableName,int recordId);
}