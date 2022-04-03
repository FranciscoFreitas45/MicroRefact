package Interface;
public interface DocumentCollection {

   public BoilerPlateContext createBoilerPlateContext(DocumentPath documentPath);
   public DocumentPrint createDocumentPrint(DocumentPath documentPath);
}