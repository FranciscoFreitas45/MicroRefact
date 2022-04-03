package Interface;
public interface DocumentCollection {

   public boolean isValidDocumentPath(DocumentPath documentPath);
   public R forDocumentReadonly(DocumentPath documentPath,Function<Document,R> documentProcessor);
}