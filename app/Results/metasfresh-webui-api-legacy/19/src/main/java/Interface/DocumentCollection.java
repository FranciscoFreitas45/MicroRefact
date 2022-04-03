package Interface;
public interface DocumentCollection {

   public BoilerPlateContext createBoilerPlateContext(DocumentPath documentPath);
   public TableRecordReference getTableRecordReference(DocumentPath documentPath);
}